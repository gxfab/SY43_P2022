package com.example.fluz.data.dao

import androidx.room.*
import com.example.fluz.data.entities.BudgetItem
import com.example.fluz.data.relashionships.BudgetItemAndCategory
import kotlinx.coroutines.flow.Flow

/**
 * SQL Requests for BudgetItem entity
 */
@Dao
interface BudgetItemDao {

    /**
     * Insert a new budget item
     *
     * @param budgetItem the budget item to insert
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(budgetItem: BudgetItem)

    /**
     * Select one budget item with its category with budget item id
     *
     * @param budgetItemId the id of the budget item
     * @return the selected budget item with its category
     */
    @Transaction
    @Query("SELECT * FROM BudgetItem WHERE id = :budgetItemId")
    fun getWithCategory(budgetItemId: Int): Flow<BudgetItemAndCategory>
}