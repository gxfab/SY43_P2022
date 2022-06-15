package com.example.lafo_cheuse.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.database.dao.CategoryDao
import com.example.lafo_cheuse.models.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(application: Application) {
    private var categoryDao : CategoryDao? = null
    private var allCategories : LiveData<List<Category>>? = null

    init {
        val database : LafoCheuseDatabase? = LafoCheuseDatabase.getInstance(application)
        categoryDao = database?.categoryDao()
        allCategories = categoryDao?.getCategories()
    }

    suspend fun createCategory(category: Category) = withContext(Dispatchers.IO) {
        categoryDao?.createCategory(category)
    }

    suspend fun updateCategory(category : Category) = withContext(Dispatchers.IO) {
        categoryDao?.updateCategory(category)
    }

    suspend fun deleteCategory(bId: Long) = withContext(Dispatchers.IO) {
        categoryDao?.deleteCategory(bId)
    }

    suspend fun deleteAllCategories() = withContext(Dispatchers.IO) {
        categoryDao?.deleteAllCategories()
    }

    fun getCategories() : LiveData<List<Category>>? {
        return allCategories
    }

    fun getCategory(categoryName : String, categoryEmoji : String) : LiveData<List<Category>>? {
        return categoryDao?.getCategory(categoryName,categoryEmoji)
    }

    fun getCategorySync(categoryName : String, categoryEmoji : String): List<Category>? {
        return categoryDao?.getCategorySync(categoryName,categoryEmoji)
    }


}