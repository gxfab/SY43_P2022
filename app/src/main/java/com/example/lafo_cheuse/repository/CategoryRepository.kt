package com.example.lafo_cheuse.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.database.dao.CategoryDao
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Income
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * ViewModel of the incomes.
 *
 * @property categoryDao - An [CategoryDao] instance which will make the queries
 * @property allCategories - A [LiveData] list of all [Category]
 *
 * @constructor It will initialize all the properties with the [LiveData] asked from the [categoryDao] and initialize database
 *
 * @param application - The [Application] which it is linked with
 */
class CategoryRepository(application: Application) {
    private var categoryDao : CategoryDao? = null
    private var allCategories : LiveData<List<Category>>? = null

    init {
        val database : LafoCheuseDatabase? = LafoCheuseDatabase.getInstance(application)
        categoryDao = database?.categoryDao()
        allCategories = categoryDao?.getCategories()
    }

    /**
     * Method to insert a [Category] in the database
     *
     * @param category - [Category] to insert in the database
     */
    suspend fun createCategory(category: Category) = withContext(Dispatchers.IO) {
        categoryDao?.createCategory(category)
    }

    /**
     * Method to update a [Category] in the database
     *
     * @param category - [Category] to update
     */
    suspend fun updateCategory(category : Category) = withContext(Dispatchers.IO) {
        categoryDao?.updateCategory(category)
    }

    /**
     * Method to delete a [Category] from the database
     *
     * @param bId - ID of the [Category] to delete
     */
    suspend fun deleteCategory(bId: Long) = withContext(Dispatchers.IO) {
        categoryDao?.deleteCategory(bId)
    }

    /**
     * Method to delete all [Category] from the database
     *
     */
    suspend fun deleteAllCategories() = withContext(Dispatchers.IO) {
        categoryDao?.deleteAllCategories()
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
        return categoryDao?.getCategory(categoryName,categoryEmoji)
    }

    /**
     * Method to get a particular category synchronously
     *
     * @param categoryName - a [String] matching with the name of a [Category]
     * @param categoryEmoji - a [String] matching with the emoji of a [Category]
     * @return a list of [Category]
     */
    fun getCategorySync(categoryName : String, categoryEmoji : String): List<Category>? {
        return categoryDao?.getCategorySync(categoryName,categoryEmoji)
    }


}