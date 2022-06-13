package com.example.gestimali.fix_expense_tags

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gestimali.database.BudgetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FixedExpenseTagsViewModel(application: Application): AndroidViewModel(application)  {
    val readAllData: LiveData<List<FixedExpenseTags>>
    private val repository: FixedExpenseTagsRepository


    init {
        val dao = BudgetDatabase.getDatabase(application).fixedExpenseTagsDao()
        repository = FixedExpenseTagsRepository(dao)
        readAllData = repository.readAllData
    }

    fun addFixedExpenseTag(tag: FixedExpenseTags){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFixedExpenseTag(tag)
        }
    }
}