package com.example.fluz.data.repositories

import androidx.annotation.WorkerThread
import com.example.fluz.data.dao.BudgetItemDao
import com.example.fluz.data.entities.BudgetItem
import com.example.fluz.data.relashionships.BudgetItemAndCategory
import kotlinx.coroutines.flow.Flow

class BudgetItemRepository(private val budgetItemDao: BudgetItemDao) {
    fun oneWithCategory(budgetItemId: Int): Flow<BudgetItemAndCategory> =
        budgetItemDao.getWithCategory(budgetItemId)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(budgetItem: BudgetItem) {
        budgetItemDao.insert(budgetItem)
    }
}