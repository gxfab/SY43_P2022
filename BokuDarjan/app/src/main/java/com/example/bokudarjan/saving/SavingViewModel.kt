package com.example.bokudarjan.saving

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bokudarjan.category.CategoryRepository
import com.example.bokudarjan.database.BokudarjanDatabase
import com.example.bokudarjan.saving.SavingDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel class for the [SavingRepository]
 */
class SavingViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Saving>>;

    private val repository : SavingRepository

    init{
        val savingDao = BokudarjanDatabase.getDatabase(application).savingDao()
        repository = SavingRepository(savingDao)
        readAllData = repository.readAllData
    }

    fun addSaving(saving: Saving){
        viewModelScope.launch(Dispatchers.IO){
            repository.addSaving(saving)
        }
    }

    fun updateSum(n: Float, name: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateSum(n,name)
        }
    }
}