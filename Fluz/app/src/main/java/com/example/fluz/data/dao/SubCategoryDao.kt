package com.example.fluz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.SubCategory

/**
 * SQL Requests for SubCategory entity
 */
@Dao
interface SubCategoryDao {

    /**
     * Insert a new subcategory
     *
     * @param subCategory the subcategory to insert
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(subCategory: SubCategory)
}