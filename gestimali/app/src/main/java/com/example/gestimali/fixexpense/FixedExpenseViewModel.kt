package com.example.gestimali.fixexpense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gestimali.database.BudgetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FixedExpenseViewModel (application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<FixedExpense>>
    private val repository: FixedExpenseRepository


    init {
        val dao = BudgetDatabase.getDatabase(application).fixedExpenseDao()
        repository = FixedExpenseRepository(dao)
        readAllData = repository.readAllData
    }

    fun addFixedExpense(exp: FixedExpense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFixedExpense(exp)
        }
    }
}