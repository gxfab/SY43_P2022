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

    @Query("SELECT * FROM Category WHERE name = :categoryName AND emoji = :categoryEmoji")
    fun getCategory(categoryName : String, categoryEmoji : String): LiveData<List<Category>>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createCategory(category: Category)

    @Update
    fun updateCategory(category: Category)

    @Query("DELETE FROM Category WHERE categoryId = :bId")
    fun deleteCategory(bId: Long) : Int

    @Query("DELETE FROM Category")
    fun deleteAllCategories() : Int
}