package com.example.sy43_p2022.database.dao

import androidx.room.*
import com.example.sy43_p2022.database.entities.SubCategory
import androidx.lifecycle.LiveData

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
    suspend fun updateSpending(vararg spending: SubCategory)

    @Query("SELECT * FROM subcategory WHERE name LIKE :name")
    suspend fun getAllSubCategoryByName(name: String): LiveData<List<SubCategory>>

    @Query("SELECT * FROM subcategory WHERE objective LIKE :objective")
    suspend fun getAllSubCategoryByObjective(objective: Int): LiveData<List<SubCategory>>

    @Query("SELECT * FROM subcategory WHERE spending LIKE :spending")
    suspend fun getAllSubCategoryBySpending(spending: Int): LiveData<List<SubCategory>>

    @Query("DELETE FROM subcategory")
    suspend fun nukeTable()
}