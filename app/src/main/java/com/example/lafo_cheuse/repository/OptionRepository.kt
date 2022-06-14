package com.example.lafo_cheuse.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.database.dao.OptionDao
import com.example.lafo_cheuse.database.dao.OptionFieldDao
import com.example.lafo_cheuse.models.Option
import com.example.lafo_cheuse.models.OptionField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OptionRepository(application: Application) {
    private var optionDao: OptionDao?
    private var optionFieldDao: OptionFieldDao?
    private var allOptions: LiveData<List<Option>>?

    init {
        val database: LafoCheuseDatabase? = LafoCheuseDatabase.getInstance(application)
        optionDao = database?.optionDao()
        optionFieldDao = database?.optionFieldDao()
        allOptions = optionDao?.getOptions()
    }

    fun getOptions() : LiveData<List<Option>>? {
        return allOptions
    }

    fun getOption(optionName: String) : LiveData<Option>? {
        return optionDao?.getOption(optionName)
    }

    fun getOptionFields(option : Option) : LiveData<List<OptionField>>? {
        return optionFieldDao?.getOptionFields(option.optionDescription)
    }

    suspend fun updateOptionField(field : OptionField) = withContext(Dispatchers.IO) {
        optionFieldDao?.updateOptionField(field)
    }

    suspend fun getOptionFieldsSync(option : Option) : List<OptionField>? {
        return optionFieldDao?.getOptionFieldsSync(option.optionDescription)
    }
}