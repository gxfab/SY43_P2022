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

    @Query("SELECT * FROM Income WHERE frequency = :frequency")
    fun getIncomesByFrequency(frequency: Frequency) : LiveData<List<Income>>

    @Query("SELECT SUM(amount) FROM Income")
    fun getIncomesSum() : LiveData<Double>

    @Query("SELECT SUM(amount) FROM Income")
    suspend fun getIncomesSumSynchronous() : Double

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncome(income : Income): Long

    @Update
    fun updateIncome(income: Income): Int

    @Query("DELETE FROM Income WHERE moneyChangeId = :bId")
    fun deleteIncome(bId: Long): Int

    @Query("DELETE FROM Income WHERE category_categoryId = :categoryID")
    fun deleteIncomeByCategory(categoryID : Long) : Int

}