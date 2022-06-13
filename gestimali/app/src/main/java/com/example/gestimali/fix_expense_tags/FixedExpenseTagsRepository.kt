package com.example.gestimali.fix_expense_tags

import androidx.lifecycle.LiveData

class FixedExpenseTagsRepository (private val dao: FixedExpenseTagsDao) {
    val readAllData: LiveData<List<FixedExpenseTags>> = dao.readAllData()

    suspend fun addFixedExpenseTag(fixedExpenseTag: FixedExpenseTags) {
        dao.addFixedExpenseTag(fixedExpenseTag)
    }
}