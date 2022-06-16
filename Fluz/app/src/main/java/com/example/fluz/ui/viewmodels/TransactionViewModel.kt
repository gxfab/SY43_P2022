package com.example.fluz.ui.viewmodels

import androidx.lifecycle.*
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.repositories.CategoryRepository
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val transactionRepository: TransactionRepository,
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    val lastBudget: MutableLiveData<Budget> = MutableLiveData()
    val category: MutableLiveData<Category> = MutableLiveData()
    val transactions = transactionRepository.allTransactions().asLiveData()

    fun getLastBudget(userId: Int) = viewModelScope.launch {
        val userWithBudgets = userRepository.oneWithBudgets(userId).first()

        lastBudget.value = userWithBudgets.budgets.last()
    }

    fun getCategory(categoryId: Int) = viewModelScope.launch {
        val cat = categoryRepository.oneById(categoryId).first()

        category.value = cat
    }


    fun addTransaction(title: String, amount: Int, date: String, categoryId: Int, budgetId: Int) =
        viewModelScope.launch {
            transactionRepository.insert(
                Transaction(
                    tag = title,
                    type = "expense",
                    date = date,
                    categoryId = categoryId,
                    budgetId = budgetId,
                    userId = null,
                    amount = amount
                )
            )
        }
}

class TransactionViewModelFactory(
    private val transactionRepository: TransactionRepository,
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransactionViewModel(
                transactionRepository,
                userRepository,
                categoryRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}