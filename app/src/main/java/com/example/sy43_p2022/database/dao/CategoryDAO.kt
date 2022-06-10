package com.example.sy43_p2022.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sy43_p2022.database.entities.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Query("SELECT id, amount, name FROM category WHERE name = :name")
    suspend fun getCategoryByName(name: String): Category

    @Query("DELETE FROM category")
    suspend fun nukeTable()
}