package com.example.sy43_p2022.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.sy43_p2022.database.entities.Category
import com.example.sy43_p2022.database.entities.CategoryAndSubCategory
import com.example.sy43_p2022.database.entities.SubCategory

@Dao
interface PiggyBankDAO {
    @Insert
    suspend fun insertCategory(name: Category)

    @Insert
    suspend fun insertSubCategory(vararg name: SubCategory)

    @Transaction
    @Query("SELECT * FROM category")
    suspend fun getAll(): List<CategoryAndSubCategory>

    @Transaction
    @Query("SELECT * FROM category WHERE catid = :id")
    suspend fun getByCategoryId(id: String): CategoryAndSubCategory

    @Transaction
    @Query("DELETE FROM CATEGORY")
    fun nukeTable()


}