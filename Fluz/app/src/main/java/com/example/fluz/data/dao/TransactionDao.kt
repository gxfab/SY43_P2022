package com.example.fluz.data.dao

import androidx.room.*
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.relashionships.TransactionAndCategory
import kotlinx.coroutines.flow.Flow

/**
 * SQL Requests for Transaction entity
 */
@Dao
interface TransactionDao {

    /**
     * Insert a new transaction
     *
     * @param transaction the transaction to insert
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(transaction: Transaction)

    /**
     * Select one transaction with its category
     *
     * @param transactionId the id of the transaction
     * @return the select transaction with its category
     */
    @androidx.room.Transaction
    @Query("SELECT * FROM 'Transaction' WHERE id =:transactionId")
    fun getWithCategory(transactionId: Int): Flow<TransactionAndCategory>

    /**
     * Select all transaction
     *
     * @return a list of all the transactions
     */
    @Query("SELECT * FROM 'Transaction'")
    fun getAll(): Flow<List<Transaction>>

    /**
     * Delete one transaction with transaction id
     *
     * @param transactionId the id of the transaction
     */
    @Query("DELETE FROM 'Transaction' WHERE id = :transactionId")
    suspend fun delete(transactionId: Int)

    /**
     * Delete all categories
     *
     */
    @Query("DELETE FROM 'Transaction'")
    suspend fun deleteAll()
}