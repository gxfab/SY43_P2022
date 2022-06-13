package com.example.gestimali.income_tags

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gestimali.database.BudgetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeTagsViewModel (application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<IncomeTags>>
    private val repository: IncomeTagsRepository


    init {
        val dao = BudgetDatabase.getDatabase(application).incomeTagsDao()
        repository = IncomeTagsRepository(dao)
        readAllData = repository.readAllData
    }

    fun addIncomeTag(tag: IncomeTags){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIncomeTag(tag)
        }
    }
}