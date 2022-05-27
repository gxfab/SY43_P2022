package com.example.sy43_p2022.database.dao

import androidx.room.*
import com.example.sy43_p2022.database.entities.Category
import com.example.sy43_p2022.database.entities.SubCategory

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Query ("SELECT * FROM category")
    suspend fun getAllCategory(): List<Category>

    @Update
    suspend fun updateTotalObjective(vararg totalObjective: Category)

    @Update
    suspend fun updateTotalSpendings(vararg totalSpendings: Category)

    @Query("SELECT * FROM category WHERE name LIKE :name")
    suspend fun getAllCategoryByName(name: String): List<Category>

    @Query("SELECT SUM(objective) FROM subcategory WHERE categoryID LIKE :id")
    suspend fun getAllObjectiveByCategory(id: Int): List<Category>

    @Query("SELECT SUM(spendings) FROM subcategory WHERE categoryID LIKE :id")
    suspend fun getAllSpendingsByCategory(id: Int): List<Category>

    @Query("DELETE FROM category")
    suspend fun nukeTable()
}