package com.example.bokudarjan.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bokudarjan.data.Expense
import com.example.bokudarjan.database.BokudarjanDatabase
import com.example.bokudarjan.repository.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class
ExpenseViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Expense>>
    private val repository: ExpenseRepository


     init {
        val expenseDao = BokudarjanDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
        readAllData = repository.readAllData
    }

    fun addExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(expense)
        }
    }

}