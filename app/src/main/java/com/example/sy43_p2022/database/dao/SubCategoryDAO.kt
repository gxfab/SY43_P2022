package com.example.sy43_p2022.database.dao

import androidx.room.*
import com.example.sy43_p2022.database.entities.SubCategory

@Dao
interface SubCategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategory(subcategory: SubCategory)

    @Delete
    suspend fun delete(category: SubCategory)

    @Query ("SELECT * FROM subcategory")
    suspend fun getAllSubCategory(): List<SubCategory>

    @Update
    suspend fun updateObjective(vararg objective: SubCategory)

    @Update
    suspend fun updateSpendings(vararg spendings: SubCategory)

    @Query("SELECT * FROM subcategory WHERE name LIKE :name")
    suspend fun getAllSubCategoryByName(name: String): List<SubCategory>

    @Query("SELECT * FROM subcategory WHERE objective LIKE :objective")
    suspend fun getAllSubCategoryByObjective(objective: Int): List<SubCategory>

    @Query("SELECT * FROM subcategory WHERE spendings LIKE :spendings")
    suspend fun getAllSubCategoryBySpendings(spendings: Int): List<SubCategory>

    @Query("DELETE FROM subcategory")
    suspend fun nukeTable()
}