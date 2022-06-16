package com.example.bokudarjan.expense

import androidx.lifecycle.LiveData



class ExpenseRepository(private val expenseDao: ExpenseDAO) {

    val readAllData: LiveData<List<Expense>> = expenseDao.readAllData()



    fun addExpense(expense: Expense) {
        expenseDao.addExpense(expense)
    }

    fun getMonthData(month: Int): LiveData<List<Expense>> {
        return expenseDao.getMonthData(month)
    }


    fun getSumOfExpenseByCategory(name: String, month: Int):LiveData<Float>{
        return expenseDao.getSumOfExpenseByCategory(name, month)
    }

    fun getSumOfPositiveExpenses(month: Int):LiveData<Float>{
        return expenseDao.getSumOfPositiveExpenses(month)
    }

    fun getSumOfNegativeExpenses(month: Int):LiveData<Float>{
        return expenseDao.getSumOfNegativeExpenses(month)
    }



}