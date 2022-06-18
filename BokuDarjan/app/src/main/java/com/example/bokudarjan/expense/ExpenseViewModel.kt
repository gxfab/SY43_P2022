package com.example.bokudarjan.expense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bokudarjan.category.CategoryRepository
import com.example.bokudarjan.database.BokudarjanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel class for the [ExpenseRepository]
 */
class ExpenseViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Expense>>
    private val repository: ExpenseRepository

     init {
        val expenseDao = BokudarjanDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
        readAllData = repository.readAllData
    }

    fun addExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExpense(expense)
        }
    }

    fun getMonthData(month: Int): LiveData<List<Expense>> {
        return repository.getMonthData(month)
    }


    fun getSumOfExpenseByCategory(name: String, month: Int):LiveData<Float>{
            return repository.getSumOfExpenseByCategory(name, month)
    }

    fun getSumOfPositiveExpenses(month: Int):LiveData<Float>{
        return repository.getSumOfPositiveExpenses(month)
    }

    fun getSumOfNegativeExpenses(month: Int):LiveData<Float>{
        return repository.getSumOfNegativeExpenses(month)
    }


}