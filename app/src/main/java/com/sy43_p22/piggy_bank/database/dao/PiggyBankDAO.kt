package com.sy43_p22.piggy_bank.database.dao

import androidx.room.*
import com.sy43_p22.piggy_bank.database.entities.Category
import com.sy43_p22.piggy_bank.database.entities.SubCategory

@Dao
interface PiggyBankDAO {
    @Insert(entity = Category::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(vararg name: Category)

    @Insert(entity = SubCategory::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubCategory(vararg name: SubCategory)

    @Transaction
    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<Category>

    @Transaction
    @Query("SELECT * FROM category WHERE catid = :id")
    suspend fun getCategoryById(id: String): Category

    @Transaction
    @Query("SELECT * FROM category WHERE name = :name")
    suspend fun getCategoryByName(name: String): Category

    @Transaction
    @Query("SELECT * FROM subcategory WHERE categoryId = :id")
    suspend fun getSubCategoriesByCategoryId(id: Int): List<SubCategory>

    @Transaction
    @Query("UPDATE subcategory SET saving = :amount WHERE subid = :id")
    suspend fun updateSubCategorySaving(id: Int, amount: Int)

    @Transaction
    @Query("UPDATE subcategory SET spending = :amount WHERE subid = :id")
    suspend fun updateSubCategorySpending(id: Int, amount: Int)

    @Transaction
    @Query("UPDATE category SET saving = :amount WHERE catid = :id")
    suspend fun updateCategorySaving(id: Int, amount: Int)

    @Transaction
    @Query("UPDATE category SET spending = :amount WHERE catid = :id")
    suspend fun updateCategorySpending(id: Int, amount: Int)

    @Transaction
    @Query("SELECT SUM(saving) FROM subcategory WHERE categoryId = :id")
    suspend fun getCategorySaving(id: Int): Int

    @Transaction
    @Query("SELECT SUM(spending) FROM subcategory WHERE categoryId = :id")
    suspend fun getCategorySpending(id: Int): Int

    @Transaction
    @Query("SELECT SUM(saving) FROM subcategory")
    suspend fun getSavingAmount(): Int

    @Transaction
    @Query("SELECT SUM(spending) FROM subcategory")
    suspend fun getSpendingAmount(): Int

    @Transaction
    @Query("DELETE FROM category")
    fun nukeCategoryTable()

    @Transaction
    @Query("DELETE FROM subcategory")
    fun nukeSubCategoryTable()


}