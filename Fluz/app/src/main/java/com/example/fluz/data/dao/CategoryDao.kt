package com.example.fluz.data.dao

import androidx.room.*
import com.example.fluz.data.entities.Category
import com.example.fluz.data.relashionships.CategoriesWithSubCategories
import com.example.fluz.data.relashionships.CategoriesWithUsers
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(category: Category)

    @Query("SELECT * FROM Category")
    fun getAll(): Flow<List<Category>>

    @Query("SELECT * FROM Category WHERE id = :categoryId")
    fun getOne(categoryId: Int): Flow<Category>

    @Transaction
    @Query("SELECT * FROM Category")
    fun getAllWithSubCategories(): Flow<List<CategoriesWithSubCategories>>

    @Transaction
    @Query("SELECT * FROM Category WHERE id = :categoryId")
    fun getWithSubCategories(categoryId: Int): Flow<CategoriesWithSubCategories>

    @Transaction
    @Query("SELECT * FROM Category")
    fun getAllWithUsers(): Flow<List<CategoriesWithUsers>>

    @Transaction
    @Query("SELECT * FROM Category WHERE id = :categoryId")
    fun getWithUsers(categoryId: Int): Flow<CategoriesWithUsers>

    @Query("DELETE FROM Category")
    suspend fun deleteAll()

    @Query("DELETE FROM Category WHERE id = :categoryId")
    suspend fun deleteOne(categoryId: Int)

}