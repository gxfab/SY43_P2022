package com.example.fluz.data.repositories

import androidx.annotation.WorkerThread
import com.example.fluz.data.dao.UserCategoryDao
import com.example.fluz.data.entities.UserCategory

/**
 * UserCategory repository
 */
class UserCategoryRepository(private val userCategoryDao: UserCategoryDao) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(userCategory: UserCategory) {
        userCategoryDao.insert(userCategory)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteOne(categoryId: Int) {
        userCategoryDao.deleteOne(categoryId)
    }
}