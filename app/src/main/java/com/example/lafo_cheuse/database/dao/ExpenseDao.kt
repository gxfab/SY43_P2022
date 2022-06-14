package com.example.lafo_cheuse.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lafo_cheuse.models.*

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    fun getExpenses(): LiveData<List<Expense>>?

    @Query("SELECT * FROM Expense WHERE moneyChangeId = :moneyChangeId")
    fun getExpense(moneyChangeId : Long): LiveData<List<Expense>>

    @Query("SELECT * FROM Expense WHERE frequency = :frequency ORDER BY date_year DESC,date_month DESC,date_day DESC, moneyChangeId DESC")
    fun getExpensesByFrequency(frequency: Frequency) : LiveData<List<Expense>>

    @Query("SELECT * FROM Expense WHERE frequency = :frequency AND date_year = :year AND date_month = :month ORDER BY date_year DESC,date_month DESC,date_day DESC")
    fun getExpenseByFrequencyAndMonth(frequency: Frequency = Frequency.OUNCE_A_DAY, year : Int, month : Int) : LiveData<List<Expense>>

    @Query("SELECT category_name,category_emoji," +
            "SUM(EXP.amount) AS totalAmount " +
            "FROM Expense EXP " +
            "WHERE EXP.frequency = :frequency " +
            "GROUP BY category_categoryId")
    fun getExpensesSumByCategory(frequency: Frequency) : LiveData<List<ExpenseSumContainer>>

    @Query("SELECT category_name,category_emoji," +
            "SUM(EXP.amount) AS totalAmount " +
            "FROM Expense EXP " +
            "WHERE EXP.frequency = :frequency " +
            "AND (date_year is null OR date_year = :year) AND (date_month is null OR date_month = :month) " +
            "GROUP BY category_categoryId")
    fun getExpensesSumByCategoryAndMonth(frequency: Frequency, year: Int, month: Int) : LiveData<List<ExpenseSumContainer>>

    @Query("SELECT category_name,category_emoji," +
            "SUM(EXP.amount) AS totalAmount " +
            "FROM Expense EXP " +
            "WHERE EXP.frequency = :frequency " +
            "AND EXP.category_categoryId = :categoryID " +
            "GROUP BY category_categoryId")
    fun getExpensesSumForCategory(frequency: Frequency, categoryID: Long) : LiveData<List<ExpenseSumContainer>>

    @Query("SELECT category_name,category_emoji," +
            "SUM(EXP.amount) AS totalAmount " +
            "FROM Expense EXP " +
            "WHERE EXP.frequency = :frequency " +
            "AND EXP.category_categoryId = :categoryID " +
            "AND (date_year is null OR date_year = :year) AND (date_month is null OR date_month = :month)" +
            "GROUP BY category_categoryId")
    fun getExpensesSumForCategoryAndMonth(frequency: Frequency, categoryID: Long, year: Int, month: Int) : LiveData<List<ExpenseSumContainer>>

    @Query("SELECT SUM(amount) FROM Expense WHERE frequency = :frequency")
    fun getExpensesSum(frequency: Frequency) : LiveData<Double>

    @Query("SELECT SUM(amount) FROM Expense WHERE frequency = :frequency " +
            "AND (date_year is null OR date_year = :year) AND (date_month is null OR date_month = :month)")
    fun getExpensesSumByDate(frequency: Frequency,year : Int, month : Int) : LiveData<Double>

    @Query("SELECT SUM(amount) FROM Expense WHERE frequency = :frequency")
    suspend fun getExpensesSumSynchronous(frequency: Frequency) : Double

    @Query("SELECT SUM(amount) FROM Expense WHERE frequency = :frequency " +
           "AND (date_year is null OR date_year = :year) AND (date_month is null OR date_month = :month)")
    suspend fun getExpensesSumSynchronousByDate(frequency: Frequency,year : Int, month : Int) : Double

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpense(expense: Expense): Long

    @Update
    fun updateExpense(expense: Expense): Int

    @Query("DELETE FROM Expense WHERE moneyChangeId = :bId")
    fun deleteExpense(bId: Long): Int

    @Query("DELETE FROM Expense WHERE category_categoryId = :categoryID")
    fun deleteExpenseByCategory(categoryID : Long) : Int
}