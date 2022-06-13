package com.example.gestimali.income

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gestimali.database.BudgetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeViewModel (application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Income>>
    private val repository: IncomeRepository


    init {
        val dao = BudgetDatabase.getDatabase(application).incomeDao()
        repository = IncomeRepository(dao)
        readAllData = repository.readAllData
    }

    fun addIncome(inc: Income){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIncome(inc)
        }
    }
}