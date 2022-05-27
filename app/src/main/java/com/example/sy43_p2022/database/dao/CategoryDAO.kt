package com.example.sy43_p2022.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sy43_p2022.database.entities.Category

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Query ("SELECT * FROM category")
    suspend fun getAllCategory(): List<Category>

    @Update
    suspend fun updateTotalObjective(vararg totalObjective: Category)

    @Update
    suspend fun updateTotalSpending(vararg totalSpending: Category)

    @Query("SELECT * FROM category WHERE name LIKE :name")
    suspend fun getAllCategoryByName(name: String): LiveData<List<Category>>

    @Query("SELECT SUM(objective) FROM subcategory WHERE categoryID LIKE :id")
    suspend fun getAllObjectiveByCategory(id: Int): LiveData<List<Category>>

    @Query("SELECT SUM(spending) FROM subcategory WHERE categoryID LIKE :id")
    suspend fun getAllSpendingByCategory(id: Int): LiveData<List<Category>>

    @Query("DELETE FROM category")
    suspend fun nukeTable()
}