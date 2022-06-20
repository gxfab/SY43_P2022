package com.example.fluz.data.dao

import androidx.room.*
import com.example.fluz.data.entities.User
import com.example.fluz.data.relashionships.UserWithBudgets
import com.example.fluz.data.relashionships.UserWithCategories
import com.example.fluz.data.relashionships.UserWithTransactions
import kotlinx.coroutines.flow.Flow

/**
 * SQL Requests for User entity
 */
@Dao
interface UserDao {
    /**
     * Insert a new User
     *
     * @param user the user to insert
     * @return the id of the inserted user
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User): Long

    /**
     * Select all users
     *
     * @return a list of all the users
     */
    @Query("SELECT * FROM User")
    fun getAll(): Flow<List<User>>

    /**
     * Select one user with user id
     *
     * @param userId the id with the user
     * @return the selected user
     */
    @Query("SELECT * FROM User WHERE id = :userId")
    fun get(userId: Int): Flow<User>

    /**
     * Select one user with email address
     *
     * @param emailAddress the email address of the user
     * @return the selected user
     */
    @Query("SELECT * FROM User WHERE email_address = :emailAddress")
    fun getByEmailAddress(emailAddress: String): Flow<User>

    /**
     * Select one user with its budgets with user id
     *
     * @param userId the id of the user
     * @return the selected user with its budgets
     */
    @Transaction
    @Query("SELECT * FROM User WHERE id = :userId")
    fun getWithBudgets(userId: Int): Flow<UserWithBudgets>

    /**
     * Select one user with its categories
     *
     * @param userId the id of the user
     * @return the selected user with its categories
     */
    @Transaction
    @Query("SELECT * FROM User WHERE id = :userId")
    fun getWithCategories(userId: Int): Flow<UserWithCategories>

    /**
     * Select one user with its transactions
     *
     * @param userId the id of the user
     * @return the selected user with its transactions
     */
    @Transaction
    @Query("SELECT * FROM User WHERE id = :userId")
    fun getWithTransactions(userId: Int): Flow<UserWithTransactions>

    /**
     * Delete all users
     *
     */
    @Query("DELETE FROM User")
    suspend fun deleteAll()
}