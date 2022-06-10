package com.example.lafo_cheuse.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.models.Income
import com.example.lafo_cheuse.repository.IncomeRepository
import kotlinx.coroutines.launch

class IncomeViewModel(application : Application) : AndroidViewModel(application) {
    private var repository : IncomeRepository
    var allIncomes : LiveData<List<Income>>?
    var incomesSum : LiveData<Double>

    init {
        repository = IncomeRepository(application)
        allIncomes = repository.getIncomes()
        incomesSum = repository.getIncomesSum()
    }

    fun getIncomes() : LiveData<List<Income>>? {
        return allIncomes
    }

    fun getIncomeSum() : LiveData<Double> {
        return incomesSum
    }

    suspend fun getIncomeSumSync() : Double {
        return repository.getIncomesSumSync()
    }

    fun getIncome(moneyChangeId : Long): LiveData<List<Income>> {
        return repository.getIncome(moneyChangeId)
    }

    fun getMonthlyIncome() : LiveData<List<Income>> {
        return repository.getIncomesByFrequency(Frequency.OUNCE_A_MONTH)
    }

    fun getOneTimeIncome() : LiveData<List<Income>> {
        return repository.getIncomesByFrequency(Frequency.OUNCE_A_DAY)
    }

    fun insertIncome(income : Income) = viewModelScope.launch {
        repository.insertIncome(income)
    }

    fun updateIncome(income : Income) = viewModelScope.launch {
        repository.updateIncome(income)
    }

    fun deleteIncome(income: Income) = viewModelScope.launch {
        repository.deleteIncome(income.moneyChangeId)
    }

    fun deleteIncomeByCategory(category: Category) = viewModelScope.launch {
        repository.deleteIncomeByCategory(category.categoryId)
    }
}