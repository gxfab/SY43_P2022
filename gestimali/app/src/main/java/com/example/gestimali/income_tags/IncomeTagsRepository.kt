package com.example.gestimali.income_tags

import androidx.lifecycle.LiveData

class IncomeTagsRepository(private val dao: IncomeTagsDao)
{
    val readAllData: LiveData<List<IncomeTags>> = dao.readAllData()

    suspend fun addIncomeTag(incomeTag: IncomeTags) {
        dao.addIncomeTag(incomeTag)
    }
}