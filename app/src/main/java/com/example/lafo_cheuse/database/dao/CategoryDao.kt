package com.example.lafo_cheuse.database.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lafo_cheuse.models.Budget
import com.example.lafo_cheuse.models.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category")
    fun getCategories(): LiveData<List<Category>>?

    @Insert
    fun createCategory(category: Category)

    @Update
    fun updateCategory(category: Category)

    @Query("DELETE FROM Category WHERE categoryId = :bId")
    fun deleteCategory(bId: Long) : Int
}