package com.example.fluz.data.dao

import androidx.room.*
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.relashionships.BudgetWithBudgetItems
import com.example.fluz.data.relashionships.BudgetWithTransactions
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(budget: Budget)

    @Transaction
    @Query("SELECT * FROM Budget where id = :budgetId")
    fun getWithBudgetItems(budgetId: Int): Flow<BudgetWithBudgetItems>

    @Transaction
    @Query("SELECT * FROM Budget where id = :budgetId")
    fun getWithTransactions(budgetId: Int): Flow<BudgetWithTransactions>
}