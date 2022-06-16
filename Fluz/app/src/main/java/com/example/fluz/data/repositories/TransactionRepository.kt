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

    fun allTransactions() : Flow<List<Transaction>> = transactionDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteOne(transactionId: Int) {
        transactionDao.delete(transactionId)
    }
}