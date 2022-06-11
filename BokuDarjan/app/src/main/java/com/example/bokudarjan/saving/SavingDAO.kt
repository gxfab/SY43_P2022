package com.example.bokudarjan.saving

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSaving(saving : Saving);

    @Query("SELECT * FROM saving ORDER BY name")
    fun readAllData(): LiveData<List<Saving>>

    @Query("UPDATE saving SET current = :value WHERE name = :name")
    fun updateSum(value: Float, name: String);
}