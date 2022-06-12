package com.example.bokudarjan.expense

import androidx.lifecycle.LiveData
import com.example.bokudarjan.expense.ExpenseDAO
import com.example.bokudarjan.expense.Expense


class ExpenseRepository(private val expenseDao: ExpenseDAO) {

    val readAllData: LiveData<List<Expense>> = expenseDao.readAllData()
    val sumOfPositiveExpenses: LiveData<Float> = expenseDao.getSumOfPositiveExpenses()
    val sumOfNegativeExpense: LiveData<Float> = expenseDao.getSumOfNegativeExpenses()

    suspend fun addUser(expense: Expense) {
        expenseDao.addExpense(expense)
    }

    fun getMonthData(month: Int): LiveData<List<Expense>> {
        return expenseDao.getMonthData(month)
    }

}