package com.example.fluz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.entities.UserCategory

/**
 * SQL Requests for UserCategory entity
 */
@Dao
interface UserCategoryDao {

    /**
     * Insert a new UserCategory
     *
     * @param userCategory the UserCategory to insert
     */
    @Insert
    suspend fun insert(userCategory: UserCategory)

    /**
     * Delete one UserCategory with category id
     *
     * @param categoryId the id of the category
     */
    @Query("DELETE FROM UserCategory where categoryId = :categoryId")
    suspend fun deleteOne(categoryId: Int)
}