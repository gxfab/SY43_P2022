package net.yolopix.moneyz.model.entities

import androidx.room.*

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    suspend fun getAll(): List<Expense>

    @Insert
    suspend fun insertExpense(expense: Expense)
}