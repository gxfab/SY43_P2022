package com.example.lafo_cheuse.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Income
import com.example.lafo_cheuse.repository.CategoryRepository
import com.example.lafo_cheuse.repository.IncomeRepository
import kotlinx.coroutines.launch

/**
 * ViewModel of the categories.
 *
 * @property repository - An [IncomeRepository] instance which will receive the queries
 * @property allCategories - A [LiveData] list of all the [Category]
 *
 * @constructor It will initialize all the properties with the [LiveData] asked from the [repository]
 *
 * @param application - The [Application] which it is linked with
 */
class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private var repository : CategoryRepository
    var allCategories : LiveData<List<Category>>?

    init {
        repository = CategoryRepository(application)
        allCategories = repository.getCategories()
    }

    /**
     * Method to get all the categories
     *
     * @return [allCategories]
     */
    fun getCategories() : LiveData<List<Category>>? {
        return allCategories
    }

    /**
     * Method to get a particular category
     *
     * @param categoryName - a [String] matching with the name of a [Category]
     * @param categoryEmoji - a [String] matching with the emoji of a [Category]
     * @return a [LiveData] list of [Category]
     */
    fun getCategory(categoryName : String, categoryEmoji : String) : LiveData<List<Category>>? {
        return repository.getCategory(categoryName,categoryEmoji)
    }

    /**
     * Method to get a particular category synchronously
     *
     * @param categoryName - a [String] matching with the name of a [Category]
     * @param categoryEmoji - a [String] matching with the emoji of a [Category]
     * @return a list of [Category]
     */
    fun getCategorySync(categoryName : String, categoryEmoji : String): List<Category>? {
        return repository.getCategorySync(categoryName,categoryEmoji)
    }

    /**
     * Method to return the default category ( the extras category)
     *
     * @return a [LiveData] list of [Category] only containing one [Category]
     */
    fun getDefaultCategory() : LiveData<List<Category>>? {
        return getCategory("extras","❔")
    }

    /**
     * Method to insert a [Category] in the database
     *
     * @param category - [Category] to insert in the database
     * @return [Unit] nothing
     */
    fun createCategory(category: Category) = viewModelScope.launch {
        repository.createCategory(category)
    }

    /**
     * Method to update a [Category] in the database
     *
     * @param category - [Category] to update
     * @return [Unit] nothing
     */
    fun updateCategory(category: Category) = viewModelScope.launch {
        repository.updateCategory(category)
    }

    /**
     * Method to delete a [Category] from the database
     *
     * @param bId - ID of the [Category] to delete
     * @return [Unit] nothing
     */
    fun deleteCategory(bId : Long) = viewModelScope.launch {
        repository.deleteCategory(bId)
    }

    /**
     * Method to delete all [Category] from the database
     *
     * @return [Unit] nothing
     */
    fun deleteAllCategories() = viewModelScope.launch {
        repository.deleteAllCategories()
    }
}