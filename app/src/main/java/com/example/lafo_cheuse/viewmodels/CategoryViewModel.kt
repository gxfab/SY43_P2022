package com.example.lafo_cheuse.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private var repository : CategoryRepository
    var allCategories : LiveData<List<Category>>?

    init {
        repository = CategoryRepository(application)
        allCategories = repository.getCategories()
    }

    fun getCategories() : LiveData<List<Category>>? {
        return allCategories
    }

    fun getCategory(categoryName : String, categoryEmoji : String) : LiveData<List<Category>>? {
        return repository.getCategory(categoryName,categoryEmoji)
    }

    fun getCategorySync(categoryName : String, categoryEmoji : String): List<Category>? {
        return repository.getCategorySync(categoryName,categoryEmoji)
    }

    fun getDefaultCategory() : LiveData<List<Category>>? {
        return getCategory("extras","❔")
    }

    fun createCategory(category: Category) = viewModelScope.launch {
        repository.createCategory(category)
    }

    fun updateCategory(category: Category) = viewModelScope.launch {
        repository.updateCategory(category)
    }

    fun deleteCategory(bId : Long) = viewModelScope.launch {
        repository.deleteCategory(bId)
    }

    fun deleteAllCategories() = viewModelScope.launch {
        repository.deleteAllCategories()
    }
}