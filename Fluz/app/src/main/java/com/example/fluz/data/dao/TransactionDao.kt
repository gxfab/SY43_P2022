package com.example.fluz.data.dao

import androidx.room.*
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.relashionships.TransactionAndCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(transaction: Transaction)

    @androidx.room.Transaction
    @Query("SELECT * FROM 'Transaction' WHERE id =:transactionId")
    fun getWithCategory(transactionId: Int): Flow<TransactionAndCategory>

    @Query("SELECT * FROM 'Transaction'")
    fun getAll(): Flow<List<Transaction>>

    @Query("DELETE FROM 'Transaction' WHERE id = :transactionId")
    suspend fun delete(transactionId: Int)

    @Query("DELETE FROM 'Transaction'")
    suspend fun deleteAll()
}