package com.example.fluz.ui.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.UserCategory
import com.example.fluz.data.repositories.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * View Model class for Categories fragment
 *
 * @property userRepository
 * @property budgetRepository
 * @property categoryRepository
 */
class CategoriesViewModel(
    private val userRepository: UserRepository,
    private val budgetRepository: BudgetRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    val categories: MutableLiveData<List<Category>> = MutableLiveData()
    val allCategories = categoryRepository.allCategories().asLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        categories.value = emptyList()
        errorMessage.value = ""
    }

    /**
     * Retrieve all the categories of the budget items of the last budget
     *
     * @param userId the id of the user
     * @return
     */
    fun getAllCategories(userId: Int) = viewModelScope.launch {
        val localCategories: MutableList<Category> = mutableListOf()

        val userWithBudgets = userRepository.oneWithBudgets(userId).first()
        val lastBudget = userWithBudgets.budgets.last()
        val budgetWithBudgetitems = budgetRepository.oneWithBudgetItems(lastBudget.id).first()

        for (budgetItem in budgetWithBudgetitems.budgetItems) {
            val category = categoryRepository.oneById(budgetItem.categoryId).first()
            localCategories.add(category)
        }

        categories.value = localCategories.toList()
    }
}

/**
 * Factory class used for dependency injection
 *
 * @property userRepository
 * @property budgetRepository
 * @property categoryRepository
 */
class CategoriesViewModelFactory(
    private val userRepository: UserRepository,
    private val budgetRepository: BudgetRepository,
    private val categoryRepository: CategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoriesViewModel(
                userRepository,
                budgetRepository,
                categoryRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}