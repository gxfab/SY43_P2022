package com.example.bokudarjan.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.bokudarjan.database.BokudarjanDatabase
import com.example.bokudarjan.category.Category
import com.example.bokudarjan.category.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class CategoryViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Category>>

    private val repository: CategoryRepository


    init {
        val categoryDao = BokudarjanDatabase.getDatabase(application).categoryDao()
        repository = CategoryRepository(categoryDao)
        readAllData = repository.readAllData



        repository.getCategory("Bénéfices").observeForever( Observer{
            if(it.isEmpty()){
                viewModelScope.launch(Dispatchers.IO) {
                    repository.addUser(Category("Bénéfices","#4CAF50"))
                }
            }
        })

    }

    fun addCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(category)
        }
    }

    fun getCategory(name: String):LiveData<List<Category>>{
        return repository.getCategory(name);
    }

}
