package com.example.lafo_cheuse.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.database.dao.ExpenseDao
import com.example.lafo_cheuse.models.Expense
import com.example.lafo_cheuse.models.ExpenseSumContainer
import com.example.lafo_cheuse.models.Frequency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExpenseRepository(application: Application) {
    private var expenseDao : ExpenseDao
    private var allExpenses : LiveData<List<Expense>>?

    init {
        val database : LafoCheuseDatabase? = LafoCheuseDatabase.getInstance(application)
        expenseDao = database?.expenseDao()!!
        allExpenses = expenseDao.getExpenses()
    }

    suspend fun insertExpense(expense: Expense) = withContext(Dispatchers.IO) {
        expenseDao.insertExpense(expense)
    }

    suspend fun updateExpense(expense : Expense) = withContext(Dispatchers.IO) {
        expenseDao.updateExpense(expense)
    }

    suspend fun deleteExpense(bId : Long) = withContext(Dispatchers.IO) {
        expenseDao.deleteExpense(bId)
    }

    suspend fun deleteExpenseByCategory(categoryId : Long) = withContext(Dispatchers.IO) {
        expenseDao.deleteExpenseByCategory(categoryId)
    }

    fun getExpenses() : LiveData<List<Expense>>? {
        return allExpenses
    }

    fun getExpense(moneyChangeId : Long): LiveData<List<Expense>> {
        return expenseDao.getExpense(moneyChangeId)
    }

    fun getExpensesByFrequency(frequency: Frequency) : LiveData<List<Expense>> {
        return expenseDao.getExpensesByFrequency(frequency)
    }

    fun getExpensesSumByCategory(frequency: Frequency) : LiveData<List<ExpenseSumContainer>> {
        return expenseDao.getExpensesSumByCategory(frequency)
    }

    fun getExpensesSumForCategory(frequency: Frequency, categoryID: Long) : LiveData<List<ExpenseSumContainer>> {
        return expenseDao.getExpensesSumForCategory(frequency,categoryID)
    }


}