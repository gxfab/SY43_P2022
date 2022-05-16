package com.example.fluz.data.repositories

import androidx.annotation.WorkerThread
import com.example.fluz.data.dao.BudgetDao
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.relashionships.BudgetWithBudgetItems
import com.example.fluz.data.relashionships.BudgetWithTransactions
import kotlinx.coroutines.flow.Flow

class BudgetRepository(private val budgetDao: BudgetDao) {
    fun oneWithBudgetItems(budgetId: Int): Flow<BudgetWithBudgetItems> =
        budgetDao.getWithBudgetItems(budgetId)

    fun oneWithTransactions(budgetId: Int): Flow<BudgetWithTransactions> =
        budgetDao.getWithTransactions(budgetId)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(budget: Budget) {
        budgetDao.insert(budget)
    }
}