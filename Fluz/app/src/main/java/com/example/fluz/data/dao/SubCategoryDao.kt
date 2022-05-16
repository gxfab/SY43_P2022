package com.example.fluz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.SubCategory

@Dao
interface SubCategoryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(subCategory: SubCategory)
}