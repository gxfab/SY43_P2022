package com.example.fluz.data.repositories

import androidx.annotation.WorkerThread
import com.example.fluz.data.dao.CategoryDao
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.Category
import com.example.fluz.data.relashionships.CategoriesWithSubCategories
import com.example.fluz.data.relashionships.CategoriesWithUsers
import kotlinx.coroutines.flow.Flow
import java.util.*

class CategoryRepository(private val categoryDao: CategoryDao) {
    fun allCategories(): Flow<List<Category>> = categoryDao.getAll()
    fun allWithSubCategories(): Flow<List<CategoriesWithSubCategories>> =
        categoryDao.getAllWithSubCategories()

    fun oneWithSubCategories(categoryId: Int): Flow<CategoriesWithSubCategories> =
        categoryDao.getWithSubCategories(categoryId)

    fun allWithUsers(): Flow<List<CategoriesWithUsers>> =
        categoryDao.getAllWithUsers()

    fun oneWithUsers(categoryId: Int): Flow<CategoriesWithUsers> =
        categoryDao.getWithUsers(categoryId)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }
}