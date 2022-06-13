package com.example.gestimali.moneysaved

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gestimali.database.BudgetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoneySavedViewModel  (application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<MoneySaved>>
    private val repository: MoneySavedRepository


    init {
        val dao = BudgetDatabase.getDatabase(application).moneySavedDao()
        repository = MoneySavedRepository(dao)
        readAllData = repository.readAllData
    }

    fun addMoneySaved(money: MoneySaved){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMoneySaved(money)
        }
    }
}