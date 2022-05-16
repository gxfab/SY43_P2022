package com.example.fluz.data.repositories

import androidx.annotation.WorkerThread
import com.example.fluz.data.dao.SubCategoryDao
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.SubCategory

class SubCategoryRepository(private val subCategoryDao: SubCategoryDao) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(subCategory: SubCategory) {
        subCategoryDao.insert(subCategory)
    }
}