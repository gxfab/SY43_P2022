package com.example.fluz.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fluz.data.entities.BudgetItem
import com.example.fluz.data.repositories.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * View Model class for Statistics fragment
 *
 * @property userRepository
 * @property budgetRepository
 */
class StatisticsViewModel(private val userRepository: UserRepository, private val budgetRepository: BudgetRepository) : ViewModel() {
    val data: MutableLiveData<Map<BudgetItem, Int>> = MutableLiveData()

    /**
     * Get all data for the budget
     *
     * @param userId
     * @return
     */
    fun getData(userId: Int) = viewModelScope.launch {
        val userWithBudgets = userRepository.oneWithBudgets(userId).first()
        val lastBudget = userWithBudgets.budgets.last()

        val budgetWithBudgetItems = budgetRepository.oneWithBudgetItems(lastBudget.id).first()
        val budgetWithTransactions = budgetRepository.oneWithTransactions(lastBudget.id).first()

        val localData: MutableMap<BudgetItem, Int> = mutableMapOf()
        for (budgetItem in budgetWithBudgetItems.budgetItems) {
            var totalTransactions = 0
            for (transaction in budgetWithTransactions.transactions) {
                if (transaction.categoryId == budgetItem.categoryId) {
                    totalTransactions += transaction.amount
                }
            }
            localData[budgetItem] = totalTransactions
        }

        data.value = localData.toMap()
    }
}

/**
 * Factory class used for dependency injection
 *
 * @property userRepository
 * @property budgetRepository
 */
class StatisticsViewModelFactory(
    private val userRepository: UserRepository, private val budgetRepository: BudgetRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatisticsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StatisticsViewModel(
                userRepository,
                budgetRepository,
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}