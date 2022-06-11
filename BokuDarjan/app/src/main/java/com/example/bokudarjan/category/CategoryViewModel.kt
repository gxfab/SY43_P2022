package com.example.bokudarjan.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bokudarjan.database.BokudarjanDatabase
import com.example.bokudarjan.category.Category
import com.example.bokudarjan.category.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CategoryViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Category>>
    val sumOFExpenseAndEnvelope: LiveData<Int>
    private val repository: CategoryRepository


    init {
        val categoryDao = BokudarjanDatabase.getDatabase(application).categoryDao()
        repository = CategoryRepository(categoryDao)
        readAllData = repository.readAllData
        sumOFExpenseAndEnvelope = repository.sumOFExpenseAndEnvelope
    }

    fun addCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(category)
        }
    }

}
