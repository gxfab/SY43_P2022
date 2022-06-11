package com.example.bokudarjan.expense

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExpenseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addExpense(expense: Expense);

    @Query("SELECT * FROM expense_table ORDER BY name")
    fun readAllData(): LiveData<List<Expense>>;

    @Query("SELECT SUM(amount) FROM expense_table WHERE moneyIncoming" )
    fun getSumOfPositiveExpenses(): LiveData<Int>;

    @Query("SELECT SUM(amount) FROM expense_table WHERE NOT moneyIncoming")
    fun getSumOfNegativeExpenses(): LiveData<Int>;
}