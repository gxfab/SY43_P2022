package com.example.lafo_cheuse.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.database.dao.IncomeDao
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.models.Income
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IncomeRepository(application: Application) {
    private var incomeDao : IncomeDao
    private var allIncomes : LiveData<List<Income>>?

    init {
        val database : LafoCheuseDatabase? = LafoCheuseDatabase.getInstance(application)
        incomeDao = database?.incomeDao()!!
        allIncomes = incomeDao.getIncomes()
    }

    suspend fun insertIncome(income : Income) = withContext(Dispatchers.IO) {
        incomeDao.insertIncome(income)
    }

    suspend fun updateIncome(income : Income) = withContext(Dispatchers.IO) {
        incomeDao.updateIncome(income)
    }

    suspend fun deleteIncome(bId : Long) = withContext(Dispatchers.IO) {
        incomeDao.deleteIncome(bId)
    }

    suspend fun deleteIncomeByCategory(categoryId : Long) = withContext(Dispatchers.IO) {
        incomeDao.deleteIncomeByCategory(categoryId)
    }

    fun getIncomes() : LiveData<List<Income>>? {
        return allIncomes
    }

    fun getIncome(moneyChangeId : Long): LiveData<List<Income>> {
        return incomeDao.getIncome(moneyChangeId)
    }


    fun getIncomesByFrequency(frequency: Frequency) : LiveData<List<Income>> {
        return incomeDao.getIncomesByFrequency(frequency)
    }


}