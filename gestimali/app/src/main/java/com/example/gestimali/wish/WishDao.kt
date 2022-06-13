package com.example.gestimali.wish

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WishDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWish(wish : Wish)

    @Query("SELECT * FROM T_wish ORDER BY wis_id ASC")
    fun readAllData() : LiveData<List<Wish>>
}