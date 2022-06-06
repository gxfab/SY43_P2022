package net.yolopix.moneyz.model.entities

import androidx.room.*

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense)
}