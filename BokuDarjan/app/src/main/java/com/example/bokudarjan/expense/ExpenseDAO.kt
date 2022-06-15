package com.example.bokudarjan.expense

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bokudarjan.month

@Dao
interface ExpenseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addExpense(expense: Expense);

    @Query("SELECT * FROM expense_table ORDER BY date DESC")
    fun readAllData(): LiveData<List<Expense>>;

    @Query("SELECT * FROM expense_table WHERE month = :month ORDER BY date DESC")
    fun getMonthData(month: Int): LiveData<List<Expense>>;

    @Query("SELECT SUM(amount) FROM expense_table WHERE categoryName='Bénéfices' AND month =:month")
    fun getSumOfPositiveExpenses(month: Int): LiveData<Float>;

    @Query("SELECT SUM(amount) FROM expense_table WHERE NOT categoryName='Bénéfices'  AND month =:month")
    fun getSumOfNegativeExpenses(month: Int): LiveData<Float>;

    @Query("SELECT SUM(amount) FROM expense_table WHERE  categoryName = :name  AND month =:month")
    fun getSumOfExpenseByCategory(name: String, month: Int):LiveData<Float>;

}