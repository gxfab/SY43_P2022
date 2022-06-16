package com.example.fluz.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.relashionships.BudgetItemAndCategory
import com.example.fluz.data.relashionships.TransactionAndCategory
import com.example.fluz.data.repositories.BudgetItemRepository
import com.example.fluz.data.repositories.BudgetRepository
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ExpensesViewModel(
    private val userRepository: UserRepository,
    val budgetRepository: BudgetRepository,
    private val budgetItemRepository: BudgetItemRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    val budgetItems: MutableLiveData<List<BudgetItemAndCategory>> = MutableLiveData()
    val lastBudget: MutableLiveData<Budget> = MutableLiveData()
    val allTransactions: MutableLiveData<List<TransactionAndCategory>> = MutableLiveData()
    val totalTransactions: MutableLiveData<Int> = MutableLiveData()

    init {
        budgetItems.value = emptyList()
    }

    fun getLastBudget(userId: Int) = viewModelScope.launch {
        val userWithBudgets = userRepository.oneWithBudgets(userId)

        lastBudget.value = userWithBudgets.first().budgets.last()
    }

    fun getAllBudgetItems(budgetId: Int) = viewModelScope.launch {
        val budgetWithBudgetItems = budgetRepository.oneWithBudgetItems(budgetId).first()
        println(budgetWithBudgetItems)

        val budgetItemsWithCategory: MutableList<BudgetItemAndCategory> = mutableListOf()
        for (budgetItem in budgetWithBudgetItems.budgetItems) {
            val budgetItemWithCategory = budgetItemRepository.oneWithCategory(budgetItem.id).first()

            budgetItemsWithCategory.add(budgetItemWithCategory)
        }

        budgetItems.value = budgetItemsWithCategory.toList()
    }

    fun getTransactions(userId: Int, budgetId: Int) = viewModelScope.launch {
        val localTransactions: MutableList<Transaction> = mutableListOf()
        val localTransactionsAndCategory: MutableList<TransactionAndCategory> = mutableListOf()

        val userWithTransactions = userRepository.oneWithTransactions(userId)
        for (transaction in userWithTransactions.first().transactions) {
            if (transaction.type == "expense") {
                localTransactions.add(transaction)
            }
        }

        val budgetWithTransactions = budgetRepository.oneWithTransactions(budgetId)
        for (transaction in budgetWithTransactions.first().transactions) {
            if (transaction.type == "expense") {
                localTransactions.add(transaction)
                val transactionAndCategory = transactionRepository.oneWithCategory(transaction.id)
                localTransactionsAndCategory.add(transactionAndCategory.first())
            }

        }

        var total = 0
        for (transaction in localTransactions) {
            total += transaction.amount
        }

        totalTransactions.value = total
        allTransactions.value = localTransactionsAndCategory.toList()
    }

}

class ExpensesViewModelFactory(
    private val userRepository: UserRepository,
    private val budgetRepository: BudgetRepository,
    private val budgetItemRepository: BudgetItemRepository,
    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpensesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExpensesViewModel(
                userRepository,
                budgetRepository,
                budgetItemRepository,
                transactionRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}