package com.example.fluz.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.relashionships.TransactionAndCategory
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FixedTransactionViewModel(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    val transactionsWithCategory = MutableLiveData<MutableList<TransactionAndCategory>>()
    var connectedUserId: Int = -1



    fun getTransactionsWithCategory() = viewModelScope.launch {
        val userWithTransactions = userRepository.oneWithTransactions(connectedUserId).first()

        val transactions: MutableList<TransactionAndCategory> = mutableListOf()
        for (transaction in userWithTransactions.transactions) {
            val item = transactionRepository.oneWithCategory(transaction.id)
            transactions.add(item.first())
        }

        transactionsWithCategory.value = transactions
    }

    fun insert(amount: Int, tag: String?, type: String, categoryId: Int, userId: Int) =
        viewModelScope.launch {
            transactionRepository.insert(
                Transaction(
                    amount = amount,
                    tag = tag,
                    type = type,
                    categoryId = categoryId,
                    userId = userId,
                    budgetId = null
                )
            )

            getTransactionsWithCategory()
        }

    fun delete(transactionId: Int) = viewModelScope.launch {
        transactionRepository.deleteOne(transactionId)
        getTransactionsWithCategory()
    }
}

class FixedTransactionViewModelFactory(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FixedTransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FixedTransactionViewModel(userRepository, transactionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}