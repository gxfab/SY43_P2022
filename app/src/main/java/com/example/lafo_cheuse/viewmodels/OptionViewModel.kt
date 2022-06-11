package com.example.lafo_cheuse.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lafo_cheuse.models.Option
import com.example.lafo_cheuse.models.OptionField
import com.example.lafo_cheuse.repository.OptionRepository
import kotlinx.coroutines.launch

class OptionViewModel(application : Application) : AndroidViewModel(application) {
    private var repository: OptionRepository
    private var allOptions: LiveData<List<Option>>?

    init {
        repository = OptionRepository(application)
        allOptions = repository.getOptions()
    }

    fun getOptions() : LiveData<List<Option>>? {
        return allOptions
    }

    fun getOption(optionName: String) : LiveData<Option>? {
        return repository.getOption(optionName)
    }

    fun getOptionFields(option : Option) : LiveData<List<OptionField>>? {
        return repository.getOptionFields(option)
    }

    fun updateOptionField(field : OptionField) = viewModelScope.launch {
        repository.updateOptionField(field)
    }
}