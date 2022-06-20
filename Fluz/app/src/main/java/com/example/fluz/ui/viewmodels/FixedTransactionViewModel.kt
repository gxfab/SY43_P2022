package com.example.fluz.ui.viewmodels

import androidx.lifecycle.*
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.relashionships.TransactionAndCategory
import com.example.fluz.data.relashionships.UserWithTransactions
import com.example.fluz.data.repositories.CategoryRepository
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * View Model class for FixedTransactions fragment
 *
 * @property userRepository
 * @property transactionRepository
 * @property categoryRepository
 */
class FixedTransactionViewModel(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository

) : ViewModel() {

    val incomeWithCategory = MutableLiveData<MutableList<TransactionAndCategory>>()
    val expensesWithCategory = MutableLiveData<MutableList<TransactionAndCategory>>()
    var connectedUserId: Int = -1

    val allCategories = categoryRepository.allCategories().asLiveData()

    /**
     * retrieve all transactions with their category
     *
     * @param type type of the transaction
     * @return
     */
    fun getTransactionsWithCategory(type: String) = viewModelScope.launch {
        var userWithTransactions: UserWithTransactions = userRepository.oneWithTransactions(connectedUserId).first()

        val transactions: MutableList<TransactionAndCategory> = mutableListOf()
        for (transaction in userWithTransactions.transactions) {
            if (type == "income" && transaction.type == "income") {
                val item = transactionRepository.oneWithCategory(transaction.id)
                transactions.add(item.first())
            } else if (type == "expense" && transaction.type == "expense") {
                val item = transactionRepository.oneWithCategory(transaction.id)
                transactions.add(item.first())
            }

        }

        if (type == "income") {
            incomeWithCategory.value = transactions
        } else {
            expensesWithCategory.value = transactions
        }

    }

    /**
     * Insert a new transaction
     *
     * @param amount
     * @param tag
     * @param type
     * @param categoryId
     * @param userId
     * @return
     */
    fun insert(amount: Int, tag: String?, type: String, categoryId: Int, userId: Int) =
        viewModelScope.launch {
            transactionRepository.insert(
                Transaction(
                    amount = amount,
                    tag = tag,
                    type = type,
                    categoryId = categoryId,
                    date = null,
                    userId = userId,
                    budgetId = null
                )
            )

            getTransactionsWithCategory(type)
        }

    /**
     * Delete a transaction
     *
     * @param transactionId
     * @param type
     * @return
     */
    fun delete(transactionId: Int, type: String) = viewModelScope.launch {
        transactionRepository.deleteOne(transactionId)
        getTransactionsWithCategory(type)
    }
}

class FixedTransactionViewModelFactory(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FixedTransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FixedTransactionViewModel(userRepository, transactionRepository, categoryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}