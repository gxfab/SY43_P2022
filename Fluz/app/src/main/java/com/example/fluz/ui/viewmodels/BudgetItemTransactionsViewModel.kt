package com.example.fluz.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.relashionships.BudgetItemAndCategory
import com.example.fluz.data.relashionships.TransactionAndCategory
import com.example.fluz.data.repositories.BudgetItemRepository
import com.example.fluz.data.repositories.BudgetRepository
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * View model class for BudgetItemTransactions fragment
 *
 * @property userRepository
 * @property budgetRepository
 * @property budgetItemRepository
 * @property transactionRepository
 */
class BudgetItemTransactionsViewModel(
    private val userRepository: UserRepository,
    private val budgetRepository: BudgetRepository,
    private val budgetItemRepository: BudgetItemRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {
    val lastBudget: MutableLiveData<Budget> = MutableLiveData()
    val budgetItemTransactions: MutableLiveData<List<TransactionAndCategory>> = MutableLiveData()
    val totalTransactions: MutableLiveData<Int> = MutableLiveData()
    val budgetItemAndCategory: MutableLiveData<BudgetItemAndCategory> = MutableLiveData()

    /**
     * Retrieve last budget from all user budgets
     *
     * @param userId the id of the user
     */
    fun getLastBudget(userId: Int) = viewModelScope.launch {
        val userWithBudgets = userRepository.oneWithBudgets(userId).first()

        lastBudget.value = userWithBudgets.budgets.last()
    }

    /**
     * Retrieve all the transactions of a specific budget item
     *
     * @param budgetId the id of the budget
     * @param budgetItemId the id of the budget item
     * @return
     */
    fun getAllTransactionsForBudgetItem(budgetId: Int, budgetItemId: Int) = viewModelScope.launch {
        val localBudgetItemAndCategory = budgetItemRepository.oneWithCategory(budgetItemId).first()

        budgetItemAndCategory.value = localBudgetItemAndCategory

        val budget = budgetRepository.oneWithTransactions(budgetId).first()

        val transactions: MutableList<TransactionAndCategory> = mutableListOf()
        for (transaction in budget.transactions) {
            if (transaction.categoryId == localBudgetItemAndCategory.category.id) {
                val transactionAndCategory = transactionRepository.oneWithCategory(transaction.id).first()
                transactions.add(transactionAndCategory)
            }
        }

        var total = 0;
        for (transaction in transactions) {
            total += transaction.transaction.amount
        }

        totalTransactions.value = total
        budgetItemTransactions.value = transactions.toList()
    }

    /**
     * Delete one transaction with budget id
     *
     * @param transactionId the id of the transaction
     * @return
     */
    fun deleteOne(transactionId: Int) = viewModelScope.launch {
        transactionRepository.deleteOne(transactionId)
    }
}

/**
 * Factory class used for dependency injection
 *
 * @property userRepository
 * @property budgetRepository
 * @property budgetItemRepository
 * @property transactionRepository
 */
class BudgetItemTransactionsViewModelFactory(
    private val userRepository: UserRepository,
    private val budgetRepository: BudgetRepository,
    private val budgetItemRepository: BudgetItemRepository,
    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BudgetItemTransactionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BudgetItemTransactionsViewModel(
                userRepository,
                budgetRepository,
                budgetItemRepository,
                transactionRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
