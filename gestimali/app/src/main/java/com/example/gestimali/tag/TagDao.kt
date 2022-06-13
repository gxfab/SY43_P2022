package com.example.gestimali.tag

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TagDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTag(tag : Tag)

    @Query("SELECT * FROM T_tag ORDER BY tag_id ASC")
    fun readAllData() : LiveData<List<Tag>>
}