package com.example.gestimali.wish

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gestimali.database.BudgetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Wish>>
    private val repository: WishRepository


    init {
        val dao = BudgetDatabase.getDatabase(application).wishDao()
        repository = WishRepository(dao)
        readAllData = repository.readAllData
    }

    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWish(wish)
        }
    }
}