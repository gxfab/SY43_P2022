package com.example.lafo_cheuse.database.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lafo_cheuse.models.ExpensesBudget

@Dao
interface ExpensesBudgetDao {
    @Query("SELECT * FROM ExpensesBudget")
    fun getAllExpenses(): LiveData<List<ExpensesBudget?>?>?

    @Query("SELECT * FROM ExpensesBudget WHERE budget_category_name = :budgetName")
    fun getExpensesByCategory(budgetName : String): LiveData<List<ExpensesBudget?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpensesBudget(budgetExpense: ExpensesBudget?): Long

    @Update
    fun updateExpensesBudget(budgetExpense: ExpensesBudget?): Int
}