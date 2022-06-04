package com.example.bokudarjan.category

import androidx.lifecycle.LiveData
import com.example.bokudarjan.expense.Expense
import com.example.bokudarjan.expense.ExpenseDAO

class CategoryRepository(private val categoryDAO: CategoryDAO) {

    val readAllData: LiveData<List<Category>> = categoryDAO.readAllData()

    suspend fun addUser(category: Category) {
        categoryDAO.addCategory(category)
    }

}