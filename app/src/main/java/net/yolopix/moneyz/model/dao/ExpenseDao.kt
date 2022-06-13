package net.yolopix.moneyz.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import net.yolopix.moneyz.model.entities.Expense

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense where categoryUid == :categoryUid ")
    suspend fun getExpenseForCategory(categoryUid: Int): List<Expense>

    @Insert
    suspend fun insertExpense(expense: Expense)
}