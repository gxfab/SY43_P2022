package com.example.lafo_cheuse.database.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lafo_cheuse.models.Budget
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.models.Income

@Dao
interface IncomeDao {
    @Query("SELECT * FROM Income")
    fun getIncomes(): LiveData<List<Income>>?

    @Query("SELECT * FROM Income WHERE moneyChangeId = :moneyChangeId")
    fun getIncome(moneyChangeId : Long): LiveData<List<Income>>

    @Query("SELECT * FROM Income WHERE frequency = :frequency ORDER BY date_year DESC,date_month DESC,date_day DESC")
    fun getIncomesByFrequency(frequency: Frequency) : LiveData<List<Income>>

    @Query("SELECT * FROM Income WHERE frequency = :frequency AND date_year = :year AND date_month = :month ORDER BY date_year DESC,date_month DESC,date_day DESC")
    fun getIncomesByFrequencyAndMonth(frequency: Frequency = Frequency.OUNCE_A_DAY, year : Int, month : Int) : LiveData<List<Income>>

    @Query("SELECT SUM(amount) FROM Income")
    fun getIncomesSum() : LiveData<Double>

    @Query("SELECT SUM(amount) FROM Income WHERE (date_year is null OR date_year = :year) AND (date_month is null OR date_month = :month)")
    fun getIncomesSumByDate(year: Int, month: Int) : LiveData<Double>

    @Query("SELECT SUM(amount) FROM Income")
    suspend fun getIncomesSumSynchronous() : Double

    @Query("SELECT SUM(amount) FROM Income WHERE (date_year is null OR date_year = :year) AND (date_month is null OR date_month = :month)")
    suspend fun getIncomesSumSynchronousByDate(year: Int, month: Int) : Double

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncome(income : Income): Long

    @Update
    fun updateIncome(income: Income): Int

    @Query("DELETE FROM Income WHERE moneyChangeId = :bId")
    fun deleteIncome(bId: Long): Int

    @Query("DELETE FROM Income WHERE category_categoryId = :categoryID")
    fun deleteIncomeByCategory(categoryID : Long) : Int

}