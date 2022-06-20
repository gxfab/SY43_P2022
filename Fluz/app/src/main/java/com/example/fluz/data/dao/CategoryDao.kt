package com.example.fluz.data.dao

import androidx.room.*
import com.example.fluz.data.entities.Category
import com.example.fluz.data.relashionships.CategoriesWithSubCategories
import com.example.fluz.data.relashionships.CategoriesWithUsers
import kotlinx.coroutines.flow.Flow

/**
 * SQL Requests for Category entity
 */
@Dao
interface CategoryDao {

    /**
     * Insert a new category
     *
     * @param category the category to insert
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(category: Category)

    /**
     * Select all the categories
     *
     * @return a list of all the categories
     */
    @Query("SELECT * FROM Category")
    fun getAll(): Flow<List<Category>>

    /**
     * Select one category with category id
     *
     * @param categoryId the id of the category
     * @return the selected category
     */
    @Query("SELECT * FROM Category WHERE id = :categoryId")
    fun getOne(categoryId: Int): Flow<Category>

    /**
     * Select all the categories with their subcategories
     *
     * @return a list of the categories with their subcategories
     */
    @Transaction
    @Query("SELECT * FROM Category")
    fun getAllWithSubCategories(): Flow<List<CategoriesWithSubCategories>>

    /**
     * Select one category with its subcategories
     *
     * @param categoryId the id of the category
     * @return the selected category with its subcategories
     */
    @Transaction
    @Query("SELECT * FROM Category WHERE id = :categoryId")
    fun getWithSubCategories(categoryId: Int): Flow<CategoriesWithSubCategories>

    /**
     * Select all categories with their users
     *
     * @return a list of the categories with their users
     */
    @Transaction
    @Query("SELECT * FROM Category")
    fun getAllWithUsers(): Flow<List<CategoriesWithUsers>>

    /**
     * Select one category with its users
     *
     * @param categoryId the id of the category
     * @return the selected category with its users
     */
    @Transaction
    @Query("SELECT * FROM Category WHERE id = :categoryId")
    fun getWithUsers(categoryId: Int): Flow<CategoriesWithUsers>

    /**
     * Delete all categories
     *
     */
    @Query("DELETE FROM Category")
    suspend fun deleteAll()

    /**
     * Delete one category with category id
     *
     * @param categoryId the id of the category
     */
    @Query("DELETE FROM Category WHERE id = :categoryId")
    suspend fun deleteOne(categoryId: Int)

}