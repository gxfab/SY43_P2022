package com.example.bokudarjan.expense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bokudarjan.expense.Expense
import com.example.bokudarjan.database.BokudarjanDatabase
import com.example.bokudarjan.expense.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class
ExpenseViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Expense>>
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