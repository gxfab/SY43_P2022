package com.example.fluz.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.repositories.BudgetRepository
import com.example.fluz.data.repositories.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * View Model class for Home fragment
 *
 * @property userRepository
 * @property budgetRepository
 */
class HomeViewModel(private val userRepository: UserRepository, private val budgetRepository: BudgetRepository) : ViewModel() {
    val budgets = MutableLiveData<List<Budget>>()
    val totalTransactions = MutableLiveData<Int>()

    /**
     * Retrieve list of budgets of the connected user
     *
     * @param userId the id of the user
     * @return
     */
    fun getUserBudgetList(userId: Int) = viewModelScope.launch {
        val userWithBudgets = userRepository.oneWithBudgets(userId)

        budgets.value = userWithBudgets.first().budgets
    }

    /**
     * Retrieve all transactions for the current budget
     *
     * @param userId
     * @param budgetId
     * @return
     */
    fun getTransactions(userId: Int, budgetId: Int) = viewModelScope.launch {
        val localTransactions: MutableList<Transaction> = mutableListOf()

        val userWithTransactions = userRepository.oneWithTransactions(userId)
        for (transaction in userWithTransactions.first().transactions) {
            if (transaction.type == "expense")
                localTransactions.add(transaction)
        }

        val budgetWithTransactions = budgetRepository.oneWithTransactions(budgetId)
        for (transaction in budgetWithTransactions.first().transactions) {
            if (transaction.type == "expense")
                localTransactions.add(transaction)
        }

        var total = 0
        for (transaction in localTransactions) {
            total += transaction.amount
        }
        totalTransactions.value = total
    }
}

/**
 * Factory class used for dependency injection
 *
 * @property repository
 * @property budgetRepository
 */
class HomeViewModelFactory(private val repository: UserRepository, private val budgetRepository: BudgetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository, budgetRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}