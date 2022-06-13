package com.example.gestimali.transaction

import androidx.lifecycle.LiveData

class TransactionRepository(private val dao: TransactionDao)  {
    val readAllData: LiveData<List<Transaction>> = dao.readAllData()

    suspend fun addTransaction(transaction: Transaction) {
        dao.addTransaction(transaction)
    }
}