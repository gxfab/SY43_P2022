package com.example.sy43_p2022.database

import androidx.annotation.WorkerThread
import com.example.sy43_p2022.database.dao.CategoryDAO
import com.example.sy43_p2022.database.entities.Category
import kotlinx.coroutines.flow.Flow

class PiggyBankRepository(private val categoryDao: CategoryDAO) {
    val allCategories: Flow<List<Category>> = categoryDao.getAllCategories()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(category: Category) {
        categoryDao.insertCategory(category)
    }
}