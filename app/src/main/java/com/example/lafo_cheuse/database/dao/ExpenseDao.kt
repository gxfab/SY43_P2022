package com.example.lafo_cheuse.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lafo_cheuse.models.Expense
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.models.Income

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    fun getExpenses(): LiveData<List<Expense>>?

    @Query("SELECT * FROM Expense WHERE moneyChangeId = :moneyChangeId")
    fun getExpense(moneyChangeId : Long): LiveData<List<Expense>>

    @Query("SELECT * FROM Expense WHERE frequency = :frequency")
    fun getExpensesByFrequency(frequency: Frequency) : LiveData<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpense(expense: Expense): Long

    @Update
    fun updateExpense(expense: Expense): Int

    @Query("DELETE FROM Expense WHERE moneyChangeId = :bId")
    fun deleteExpense(bId: Long): Int

    @Query("DELETE FROM Expense WHERE category_categoryId = :categoryID")
    fun deleteExpenseByCategory(categoryID : Long) : Int
}