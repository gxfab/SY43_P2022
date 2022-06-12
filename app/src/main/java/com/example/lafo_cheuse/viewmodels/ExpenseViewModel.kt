package com.example.lafo_cheuse.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.models.Expense
import com.example.lafo_cheuse.models.ExpenseSumContainer
import com.example.lafo_cheuse.repository.ExpenseRepository
import kotlinx.coroutines.launch

class ExpenseViewModel(application : Application) : AndroidViewModel(application) {
    private var repository : ExpenseRepository
    var allExpenses : LiveData<List<Expense>>?

    init {
        repository = ExpenseRepository(application)
        allExpenses = repository.getExpenses()
    }

    fun getExpenses() : LiveData<List<Expense>>? {
        return allExpenses
    }

    fun getExpense(moneyChangeId : Long): LiveData<List<Expense>> {
        return repository.getExpense(moneyChangeId)
    }

    fun getMonthlyExpense() : LiveData<List<Expense>> {
        return repository.getExpensesByFrequency(Frequency.OUNCE_A_MONTH)
    }

    fun getOneTimeExpense() : LiveData<List<Expense>> {
        return repository.getExpensesByFrequency(Frequency.OUNCE_A_DAY)
    }

    fun insertExpense(expense : Expense) = viewModelScope.launch {
        repository.insertExpense(expense)
    }

    fun updateExpense(expense : Expense) = viewModelScope.launch {
        repository.updateExpense(expense)
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repository.deleteExpense(expense.moneyChangeId)
    }

    fun deleteExpenseByCategory(category: Category) = viewModelScope.launch {
        repository.deleteExpenseByCategory(category.categoryId)
    }

    fun getMonthlyExpensesSumByCategory() : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumByCategory(Frequency.OUNCE_A_MONTH)
    }

    fun getOneTimeExpensesSumByCategory() : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumByCategory(Frequency.OUNCE_A_DAY)
    }

    fun getMonthlyExpensesSumForCategory(category: Category) : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumForCategory(Frequency.OUNCE_A_MONTH, category.categoryId)
    }

    fun getOneTimeExpensesSumForCategory(category: Category) : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumForCategory(Frequency.OUNCE_A_DAY, category.categoryId)
    }

    fun getOneTimeExpenseAndMonth(
        year : Int,
        month : Int
    ) : LiveData<List<Expense>> {
        return repository.getExpenseByFrequencyAndMonth(Frequency.OUNCE_A_DAY,year,month)
    }

    fun getOneTimeExpensesSumByCategoryAndMonth(
        year: Int,
        month: Int
    ) : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumByCategoryAndMonth(Frequency.OUNCE_A_DAY,year,month)
    }

    fun getOneTimeExpensesSumForCategoryAndMonth(
        categoryID: Long,
        year: Int,
        month: Int
    ) : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumForCategoryAndMonth(Frequency.OUNCE_A_DAY,categoryID,year,month)
    }

    fun getMonthlyExpenseAndMonth(
        year : Int,
        month : Int
    ) : LiveData<List<Expense>> {
        return repository.getExpenseByFrequencyAndMonth(Frequency.OUNCE_A_MONTH,year,month)
    }

    fun getMonthlyExpensesSumByCategoryAndMonth(
        year: Int,
        month: Int
    ) : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumByCategoryAndMonth(Frequency.OUNCE_A_MONTH,year,month)
    }

    fun getMonthlyExpensesSumForCategoryAndMonth(
        categoryID: Long,
        year: Int,
        month: Int
    ) : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumForCategoryAndMonth(Frequency.OUNCE_A_MONTH,categoryID,year,month)
    }

    fun getOneTimeExpensesSum() : LiveData<Double> {
        return repository.getExpensesSumByFrequency(Frequency.OUNCE_A_DAY)
    }

    fun getMonthlyExpensesSum() : LiveData<Double> {
        return repository.getExpensesSumByFrequency(Frequency.OUNCE_A_MONTH)
    }

    fun getOneTimeExpensesSumByDate(year : Int, month : Int) : Double {
        return repository.getExpensesSumByDate(Frequency.OUNCE_A_DAY,year,month)
    }

    fun getMonthlyExpensesSumByDate(year : Int, month : Int) : Double {
        return repository.getExpensesSumByDate(Frequency.OUNCE_A_MONTH,year,month)
    }

    suspend fun getMonthlyExpensesSumSync() : Double {
        return repository.getExpensesSumByFrequencySync(Frequency.OUNCE_A_MONTH)
    }
}