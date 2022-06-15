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

/**
 * ViewModel of the options and optionFields
 *
 * @property optionDao - An [OptionDao] instance which will make the queries
 * @property optionFieldDao - AN [OptionField] instance which will make the queries
 * @property allOptions - A [LiveData] list of [Option] in database
 *
 * @constructor It will initialize all the properties with the [LiveData] asked from the [optionDao]
 *
 * @param application - The [Application] which it is linked with
 */
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

    /**
     * Method to get all the options.
     *
     * @return [allOptions] which has been initialized in the constructor
     */
    fun getOptions() : LiveData<List<Option>>? {
        return allOptions
    }

    /**
     * Method to get a particular [Option]
     *
     * @param optionName - [String] with the name of desired [Option]
     * @return a [LiveData] container of [Option]
     */
    fun getOption(optionName: String) : LiveData<Option>? {
        return optionDao?.getOption(optionName)
    }

    /**
     * Method to get all the [OptionField] linked with an [Option]
     *
     * @param option - the [Option] we want to get the [OptionField] of
     * @return a [LiveData] list of [OptionField]
     */
    fun getOptionFields(option : Option) : LiveData<List<OptionField>>? {
        return optionFieldDao?.getOptionFields(option.optionDescription)
    }

    /**
     * Method to update an [OptionField] in database
     *
     * @param field - [OptionField] to update
     * @return [Unit] nothing
     */
    suspend fun updateOptionField(field : OptionField) = withContext(Dispatchers.IO) {
        optionFieldDao?.updateOptionField(field)
    }

    /**
     * Method to get all the [OptionField] linked with an [Option] synchronously
     *
     * @param option - the [Option] we want to get the [OptionField] of
     * @return a list of [OptionField]
     */
    suspend fun getOptionFieldsSync(option : Option) : List<OptionField>? {
        return optionFieldDao?.getOptionFieldsSync(option.optionDescription)
    }
}