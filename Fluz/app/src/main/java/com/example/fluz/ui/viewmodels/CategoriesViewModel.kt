package com.example.fluz.ui.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.UserCategory
import com.example.fluz.data.repositories.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CategoriesViewModel(private val userRepository: UserRepository, private val categoryRepository: CategoryRepository, private val userCategoryRepository: UserCategoryRepository) : ViewModel() {
    val categories: MutableLiveData<List<Category>> = MutableLiveData()
    val allCategories = categoryRepository.allCategories().asLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        categories.value = emptyList()
        errorMessage.value = ""
    }

    fun getAllCategories(userId: Int) = viewModelScope.launch {
        val globalCategories = categoryRepository.allCategories().first()
        val userCategories = userRepository.oneWithCategories(userId).first()

        val localCategories: MutableList<Category> = mutableListOf()
        for (category in globalCategories) {
            if (category.type == "Global") {
                localCategories.add(category)
            }
        }

        for (category in userCategories.categories) {
            localCategories.add(category)
        }

        categories.value = localCategories.toList()
    }

    fun addCategory(title: String, userId: Int) = viewModelScope.launch {
        val category = Category(title = title, type = "User")

        for (category in categories.value!!) {
            if (category.title == title) {
                errorMessage.value = "This category already exists"
                return@launch
            }
        }

        errorMessage.value = ""
        categoryRepository.insert(category)
    }

    fun addUserCategory(userId: Int, categoryId : Int) = viewModelScope.launch {
        val userCategory = UserCategory(userId, categoryId)

        userCategoryRepository.insert(userCategory)

        getAllCategories(userId)
    }

    fun deleteOne(categoryId: Int) = viewModelScope.launch {
        userCategoryRepository.deleteOne(categoryId)
        categoryRepository.deleteOne(categoryId)
    }
}

class CategoriesViewModelFactory(
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository,
    private val userCategoryRepository: UserCategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoriesViewModel(
                userRepository,
                categoryRepository,
                userCategoryRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}