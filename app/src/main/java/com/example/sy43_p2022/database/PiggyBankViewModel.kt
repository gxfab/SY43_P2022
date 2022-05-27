package com.example.sy43_p2022.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sy43_p2022.database.entities.Category

class PiggyBankViewModel(private val repository: PiggyBankRepository): ViewModel() {
    val allCategories: LiveData<List<Category>> = repository.allCategories.asLiveData()
}