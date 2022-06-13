package com.example.gestimali.moneysaved

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoneySavedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMoneySaved(monSaved : MoneySaved)

    @Query("SELECT * FROM T_money_saved ORDER BY mon_year, mon_month,wis_id ASC")
    fun readAllData() : LiveData<List<MoneySaved>>
}