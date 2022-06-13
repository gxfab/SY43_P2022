package com.example.gestimali.income

import androidx.lifecycle.LiveData

class IncomeRepository (private val dao: IncomeDao)
{
    val readAllData: LiveData<List<Income>> = dao.readAllData()

    suspend fun addIncome(income: Income) {
        dao.addIncome(income)
    }
}