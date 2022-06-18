package com.example.bokudarjan.bmonth

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bokudarjan.category.Category

@Dao
/**
 * DAO Interface of [BMonth]
 */
interface BMonthDAO {
     @Query("SELECT * FROM month ORDER BY id")
     fun readAllData(): LiveData<List<BMonth>>

    @Query("SELECT * FROM month WHERE id= :id ORDER BY id")
    fun getMonth(id: Int): LiveData<BMonth>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMonth(month: BMonth);
}