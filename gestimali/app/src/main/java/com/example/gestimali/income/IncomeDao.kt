package com.example.gestimali.income

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gestimali.fixexpense.FixedExpense

@Dao
interface IncomeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addIncome(income : Income)

    @Query("SELECT * FROM T_income ORDER BY inc_id ASC")
    fun readAllData() : LiveData<List<Income>>

    @Query("SELECT * FROM T_income WHERE inc_month=:month ORDER BY inc_id ASC")
    fun readAllDataFromMonth(month : Int) : LiveData<List<Income>>
}