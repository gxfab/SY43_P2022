package com.example.gestimali.fixexpense

import androidx.lifecycle.LiveData

class FixedExpenseRepository (private val dao: FixedExpenseDao) {
    val readAllData: LiveData<List<FixedExpense>> = dao.readAllData()

    suspend fun addFixedExpense(fixedExpense: FixedExpense) {
        dao.addFixedExpense(fixedExpense)
    }
}