package com.example.sy43_p2022.database.dao

import androidx.room.*
import com.example.sy43_p2022.database.entities.Category
import com.example.sy43_p2022.database.entities.SubCategory

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category)

    @Query ("SELECT * FROM category")
    fun getAllCategory(): List<Category>

    @Update
    fun updateTotalObjective(vararg totalObjective: Category)

    @Update
    fun updateTotalSpendings(vararg totalSpendings: Category)

    @Query("SELECT * FROM category WHERE name LIKE :name")
    fun getAllCategoryByName(name: String): List<Category>

    @Query("SELECT * FROM subcategory WHERE objective LIKE :totalObjective")
    fun getAllCategoryByObjective(totalObjective: Int): List<Category>

    @Query("SELECT * FROM subcategory WHERE spendings LIKE :totalSpendings")
    fun getAllCategoryBySpendings(totalSpendings: Int): List<SubCategory>


    @Query("DELETE FROM category")
    fun nukeTable()
}