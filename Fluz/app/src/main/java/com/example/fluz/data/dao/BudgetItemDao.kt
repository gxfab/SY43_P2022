package com.example.fluz.data.dao

import androidx.room.*
import com.example.fluz.data.entities.BudgetItem
import com.example.fluz.data.relashionships.BudgetItemAndCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetItemDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(budgetItem: BudgetItem)

    @Transaction
    @Query("SELECT * FROM BudgetItem WHERE id = :budgetItemId")
    fun getWithCategory(budgetItemId: Int): Flow<BudgetItemAndCategory>
}