package com.example.sy43_p2022.database.dao

import androidx.room.*
import com.example.sy43_p2022.database.entities.SubCategory
import androidx.lifecycle.LiveData

@Dao
interface SubCategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategory(subcategory: SubCategory)

    @Query("SELECT * FROM subcategory WHERE categoryId = :id")
    suspend fun getSubCategoriesFromParentId(id: Int): List<SubCategory>

    @Query("SELECT * FROM subcategory WHERE id = :id")
    suspend fun getSubcategoryById(id: Int): SubCategory

    @Query("DELETE FROM subcategory")
    suspend fun nukeTable()
}