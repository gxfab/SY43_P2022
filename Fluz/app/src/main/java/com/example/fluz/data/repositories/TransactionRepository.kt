package com.example.fluz.data.repositories

import androidx.annotation.WorkerThread
import com.example.fluz.data.dao.TransactionDao
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.entities.User
import com.example.fluz.data.relashionships.TransactionAndCategory
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {
    fun oneWithCategory(transactionId: Int): Flow<TransactionAndCategory> =
        transactionDao.getWithCategory(transactionId)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }
}