package com.example.bokudarjan.repository

import androidx.lifecycle.LiveData
import com.example.bokudarjan.dao.ExpenseDAO
import com.example.bokudarjan.data.Expense


class ExpenseRepository(private val expenseDao: ExpenseDAO) {

    val readAllData: LiveData<List<Expense>> = expenseDao.readAllData()

    suspend fun addUser(expense: Expense) {
        expenseDao.addExpense(expense)
    }

}