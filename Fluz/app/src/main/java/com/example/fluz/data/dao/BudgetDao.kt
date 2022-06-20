package com.example.fluz.data.dao

import androidx.room.*
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.relashionships.BudgetWithBudgetItems
import com.example.fluz.data.relashionships.BudgetWithTransactions
import kotlinx.coroutines.flow.Flow

/**
 * SQL Requests for Budget entity
 */
@Dao
interface BudgetDao {

    /**
     * insert a new budget
     *
     * @param budget the budget to insert
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(budget: Budget)

    /**
     * Select one budget with budget id
     *
     * @param budgetId the id of the budget
     * @return the selected budget
     */
    @Query("SELECT * FROM Budget where id = :budgetId")
    fun getOne(budgetId: Int): Flow<Budget>

    /**
     * Select one budget with all its budget items with budget id
     *
     * @param budgetId the id of the budget
     * @return the selected budget with budget items
     */
    @Transaction
    @Query("SELECT * FROM Budget where id = :budgetId")
    fun getWithBudgetItems(budgetId: Int): Flow<BudgetWithBudgetItems>

    /**
     * Select one budget with all its transactions with budget id
     *
     * @param budgetId the id of the budget
     * @return the select budget with transactions
     */
    @Transaction
    @Query("SELECT * FROM Budget where id = :budgetId")
    fun getWithTransactions(budgetId: Int): Flow<BudgetWithTransactions>
}