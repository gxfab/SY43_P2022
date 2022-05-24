package com.example.lafo_cheuse.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lafo_cheuse.models.ExpensesBudget

@Dao
interface IncomesBudgetDao {
    @Query("SELECT * FROM IncomesBudget")
    fun getAllIncomes(): LiveData<List<ExpensesBudget?>?>?

    @Query("SELECT * FROM IncomesBudget WHERE budget_category_name = :budgetName")
    fun getIncomesByCategory(budgetName : String): LiveData<List<ExpensesBudget?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncomesBudget(budgetExpense: ExpensesBudget?): Long

    @Update
    fun updateIncomesBudget(budgetExpense: ExpensesBudget?): Int
}