package com.example.gestimali.transaction

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gestimali.database.BudgetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel (application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<Transaction>>
    private val repository: TransactionRepository


    init {
        val dao = BudgetDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(dao)
        readAllData = repository.readAllData
    }

    fun addTransaction(tra: Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransaction(tra)
        }
    }
}