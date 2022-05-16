package com.example.fluz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.relashionships.TransactionAndCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(transaction: Transaction)

    @androidx.room.Transaction
    @Query("SELECT * FROM Transaction WHERE id = :transactionId")
    fun getWithCategory(transactionId: Int): Flow<TransactionAndCategory>
}