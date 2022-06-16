package com.example.fluz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.entities.UserCategory

@Dao
interface UserCategoryDao {
    @Insert
    suspend fun insert(userCategory: UserCategory)

    @Query("DELETE FROM UserCategory where categoryId = :categoryId")
    suspend fun deleteOne(categoryId: Int)
}