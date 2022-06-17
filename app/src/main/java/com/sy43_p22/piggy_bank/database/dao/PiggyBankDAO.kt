package com.sy43_p22.piggy_bank.database.dao

import androidx.room.*
import com.sy43_p22.piggy_bank.database.entities.Category
import com.sy43_p22.piggy_bank.database.entities.SubCategory

/**
 * DAO: Interface for SQL requests between the APP & the Database
 */
@Dao
interface PiggyBankDAO {

    // --- Equivalent of INSERT INTO
    @Insert(entity = Category::class, onConflict = OnConflictStrategy.IGNORE)
    /** @description add one or multiple categories to the database */
    suspend fun insertCategory(vararg name: Category)

    @Insert(entity = SubCategory::class, onConflict = OnConflictStrategy.IGNORE)
    /** @description add one or multiple sub-categories to the database */
    suspend fun insertSubCategory(vararg name: SubCategory)
    // ---

    @Transaction
    @Query("SELECT * FROM category")
    /** @description request all categories (Used to check if the DB is empty) */
    suspend fun getAllCategories(): List<Category>

    @Transaction
    @Query("SELECT * FROM category WHERE catid = :id")
    /** @description get corresponding category for the given id */
    suspend fun getCategoryById(id: String): Category

    @Transaction
    @Query("SELECT * FROM category WHERE name = :name")
    /** @description get corresponding category for the given name */
    suspend fun getCategoryByName(name: String): Category

    @Transaction
    @Query("SELECT * FROM subcategory WHERE categoryId = :id")
    /** @description get corresponding sub-categories for the given parent category's id */
    suspend fun getSubCategoriesByCategoryId(id: Int): List<SubCategory>

    @Transaction
    @Query("UPDATE subcategory SET saving = :amount WHERE subid = :id")
    /** @description set saving value of a sub-category for the given id */
    suspend fun updateSubCategorySaving(id: Int, amount: Int)

    @Transaction
    @Query("UPDATE subcategory SET spending = :amount WHERE subid = :id")
    /** @description set spending value of a sub-category for the given id */
    suspend fun updateSubCategorySpending(id: Int, amount: Int)

    @Transaction
    @Query("UPDATE category SET saving = :amount WHERE catid = :id")
    /** @description set saving value of a category for the given id */
    suspend fun updateCategorySaving(id: Int, amount: Int)

    @Transaction
    @Query("UPDATE category SET spending = :amount WHERE catid = :id")
    /** @description set spending value of a category for the given id */
    suspend fun updateCategorySpending(id: Int, amount: Int)

    @Transaction
    @Query("SELECT SUM(saving) FROM subcategory WHERE categoryId = :id")
    /** @description get saving value for the given category's id */
    suspend fun getCategorySaving(id: Int): Int

    @Transaction
    @Query("SELECT SUM(spending) FROM subcategory WHERE categoryId = :id")
    /** @description get spending value for the given category's id */
    suspend fun getCategorySpending(id: Int): Int

    @Transaction
    @Query("SELECT SUM(saving) FROM subcategory")
    /** @description get global saving value */
    suspend fun getSavingAmount(): Int

    @Transaction
    @Query("SELECT SUM(spending) FROM subcategory")
    /** @description get global spending value */
    suspend fun getSpendingAmount(): Int

    @Transaction
    @Query("DELETE FROM category")
    /** @description DROP TABLE */
    fun nukeCategoryTable()

    @Transaction
    @Query("DELETE FROM subcategory")
    /** @description DROP TABLE */
    fun nukeSubCategoryTable()


}