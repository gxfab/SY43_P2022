package com.example.lafo_cheuse.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lafo_cheuse.models.Option
import com.example.lafo_cheuse.models.OptionField
import com.example.lafo_cheuse.repository.OptionRepository
import kotlinx.coroutines.launch

/**
 * ViewModel of the options and optionFields
 *
 * @property repository - An [OptionRepository] instance which will receive the queries
 * @property allOptions - A [LiveData] list of [Option] in database
 *
 * @constructor It will initialize all the properties with the [LiveData] asked from the [repository]
 *
 * @param application - The [Application] which it is linked with
 */
class OptionViewModel(application : Application) : AndroidViewModel(application) {
    private var repository: OptionRepository
    private var allOptions: LiveData<List<Option>>?

    init {
        repository = OptionRepository(application)
        allOptions = repository.getOptions()
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
        return repository.getOption(optionName)
    }

    /**
     * Method to get all the [OptionField] linked with an [Option]
     *
     * @param option - the [Option] we want to get the [OptionField] of
     * @return a [LiveData] list of [OptionField]
     */
    fun getOptionFields(option : Option) : LiveData<List<OptionField>>? {
        return repository.getOptionFields(option)
    }

    /**
     * Method to get all the [OptionField] linked with an [Option] synchronously
     *
     * @param option - the [Option] we want to get the [OptionField] of
     * @return a list of [OptionField]
     */
    suspend fun getOptionFieldsSync(option : Option) : List<OptionField>? {
        return repository.getOptionFieldsSync(option)
    }

    /**
     * Method to update an [OptionField] in database
     *
     * @param field - [OptionField] to update
     * @return [Unit] nothing
     */
    fun updateOptionField(field : OptionField) = viewModelScope.launch {
        repository.updateOptionField(field)
    }
}