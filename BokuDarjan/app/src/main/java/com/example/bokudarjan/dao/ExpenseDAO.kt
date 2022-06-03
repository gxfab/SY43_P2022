package com.example.bokudarjan.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bokudarjan.data.Expense

@Dao
interface ExpenseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addExpense(expense: Expense);

    @Query("SELECT * FROM expense_table ORDER BY categoryName")
    fun readAllData(): LiveData<List<Expense>>;


}