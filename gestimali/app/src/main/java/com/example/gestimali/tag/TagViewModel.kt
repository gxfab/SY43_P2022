package com.example.gestimali.tag

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gestimali.database.BudgetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TagViewModel  (application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<Tag>>
    private val repository: TagRepository


    init {
        val dao = BudgetDatabase.getDatabase(application).tagDao()
        repository = TagRepository(dao)
        readAllData = repository.readAllData
    }

    fun addTag(tag: Tag){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTag(tag)
        }
    }
}