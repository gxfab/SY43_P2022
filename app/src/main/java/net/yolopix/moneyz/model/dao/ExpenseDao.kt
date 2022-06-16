package net.yolopix.moneyz.model.dao

import androidx.room.*
import net.yolopix.moneyz.model.entities.Expense

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense where categoryUid == :categoryUid ")
    suspend fun getExpenseForCategory(categoryUid: Int): List<Expense>

    @Query("SELECT IFNULL(SUM(amount),0) FROM Expense WHERE categoryUid == :categoryUid ")
    suspend fun getExpenseAmountForOneCategory(categoryUid: Int): Float

    @Update
    suspend fun updateExpense(expense: Expense)

    @Insert
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)
}