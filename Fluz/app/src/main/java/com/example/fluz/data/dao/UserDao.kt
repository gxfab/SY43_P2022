package com.example.fluz.data.dao

import androidx.room.*
import com.example.fluz.data.entities.User
import com.example.fluz.data.relashionships.UserWithBudgets
import com.example.fluz.data.relashionships.UserWithCategories
import com.example.fluz.data.relashionships.UserWithTransactions
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User)

    @Query("SELECT * FROM User")
    fun getAll(): Flow<List<User>>

    @Transaction
    @Query("SELECT * FROM User WHERE id = :userId")
    fun getWithBudgets(userId: Int): Flow<UserWithBudgets>

    @Transaction
    @Query("SELECT * FROM User WHERE id = :userId")
    fun getWithCategories(userId: Int): Flow<UserWithCategories>

    @Transaction
    @Query("SELECT * FROM User WHERE id = :userId")
    fun getWithTransactions(userId: Int): Flow<UserWithTransactions>

    @Query("DELETE FROM User")
    suspend fun deleteAll()
}