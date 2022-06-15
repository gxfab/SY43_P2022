package com.example.lafo_cheuse.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lafo_cheuse.models.*
import com.example.lafo_cheuse.repository.ExpenseRepository
import com.example.lafo_cheuse.repository.IncomeRepository
import kotlinx.coroutines.launch

/**
 * ViewModel of the expenses.
 *
 * @property repository - An [ExpenseRepository] instance which will receive the queries
 * @property allExpenses - A [LiveData] list of [Expense] with all the expenses including monthly & one-time incomes
 *
 * @constructor It will initialize all the properties with the [LiveData] asked from the [repository]
 *
 * @param application - The [Application] which it is linked with
 */
class ExpenseViewModel(application : Application) : AndroidViewModel(application) {
    private var repository : ExpenseRepository
    var allExpenses : LiveData<List<Expense>>?

    init {
        repository = ExpenseRepository(application)
        allExpenses = repository.getExpenses()
    }

    /**
     * Method to get a particular expense
     *
     * @param expenseId - [Long] value containing the ID of the expense
     * @return a [LiveData] list of [Expense] containing the targeted [Expense]
     */
    fun getExpense(expenseId : Long): LiveData<List<Expense>> {
        return repository.getExpense(expenseId)
    }

    /**
     * Method to get all the regular expenses
     *
     * @return a [LiveData] list of [Expense]
     */
    fun getMonthlyExpense() : LiveData<List<Expense>> {
        return repository.getExpensesByFrequency(Frequency.OUNCE_A_MONTH)
    }

    /**
     * Method to get all the one-time expenses
     *
     * @return a [LiveData] list of [Expense]
     */
    fun getOneTimeExpense() : LiveData<List<Expense>> {
        return repository.getExpensesByFrequency(Frequency.OUNCE_A_DAY)
    }

    /**
     * Insert an [Expense] in database
     *
     * @param expense - The [Expense] to insert in database
     * @return [Unit] nothing
     */
    fun insertExpense(expense : Expense) = viewModelScope.launch {
        repository.insertExpense(expense)
    }

    /**
     * Method to update an expense in the database
     * It is executed in a coroutine in [viewModelScope]
     *
     * @param expense - [Expense] that will be updated
     * @return [Unit] Nothing
     */
    fun updateExpense(expense : Expense) = viewModelScope.launch {
        repository.updateExpense(expense)
    }

    /**
     * Method to delete an expense in the database
     * It is executed in a coroutine in [viewModelScope]
     *
     * @param expense - [Expense] that will be deleted
     * @return [Unit] Nothing
     */
    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repository.deleteExpense(expense.moneyChangeId)
    }

    /**
     * Get a list of sum of regular expenses in a particular category
     *
     * @return a [LiveData] list of [ExpenseSumContainer]
     */
    fun getMonthlyExpensesSumByCategory() : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumByCategory(Frequency.OUNCE_A_MONTH)
    }

    /**
     * Get a sum of regular expenses for a particular category
     *
     * @param category - the [Category] targeted
     * @return a [LiveData] list of [ExpenseSumContainer]
     */
    fun getMonthlyExpensesSumForCategory(category: Category) : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumForCategory(Frequency.OUNCE_A_MONTH, category.categoryId)
    }

    /**
     * Get a list of sum of one-time expenses for a particular month
     *
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] list of [ExpenseSumContainer]
     */
    fun getOneTimeExpensesSumByCategoryAndMonth(
        year: Int,
        month: Int
    ) : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumByCategoryAndMonth(Frequency.OUNCE_A_DAY,year,month)
    }

    /**
     * Get a list of sum of one-time expenses for a particular month and a particular category
     *
     * @param category - [Category] of the targeted category
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] list of [ExpenseSumContainer]
     */
    fun getOneTimeExpensesSumForCategoryAndMonth(
        category: Category,
        year: Int,
        month: Int
    ) : LiveData<List<ExpenseSumContainer>> {
        return repository.getExpensesSumForCategoryAndMonth(Frequency.OUNCE_A_DAY,category,year,month)
    }

    /**
     * Get a sum of all regular expenses
     *
     * @return [LiveData] of [Double] containing the sum
     */
    fun getMonthlyExpensesSum() : LiveData<Double> {
        return repository.getExpensesSumByFrequency(Frequency.OUNCE_A_MONTH)
    }

    /**
     * Get a sum of all one-time expenses for a particular month
     *
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] containing a [Double]
     */
    fun getOneTimeExpensesSumByDate(year : Int, month : Int) : LiveData<Double> {
        return repository.getExpensesSumByDate(Frequency.OUNCE_A_DAY,year,month)
    }

    /**
     * Get a sum of all regular expenses for a particular month
     *
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] containing a [Double]
     */
    fun getMonthlyExpensesSumByDate(year : Int, month : Int) : LiveData<Double> {
        return repository.getExpensesSumByDate(Frequency.OUNCE_A_MONTH,year,month)
    }

    /**
     * Get a sum of all regular expenses synchronously
     *
     * @return [Double] containing the sum
     */
    suspend fun getMonthlyExpensesSumSync() : Double {
        return repository.getExpensesSumByFrequencySync(Frequency.OUNCE_A_MONTH)
    }

    /**
     * Get a sum a of one-time expenses for a particular month and a particular [Category] synchronously
     *
     * @param category - [Category] to focus on
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a list of [ExpenseSumContainer] containing only the [ExpenseSumContainer] linked with [category]
     */
    suspend fun getOneTimeExpensesSumForCategoryAndMonthSync(
        category: Category,
        year: Int,
        month: Int
    ) : List<ExpenseSumContainer> {
        return repository.getExpensesSumForCategoryAndMonthSync(Frequency.OUNCE_A_DAY,category,year,month)
    }

    /**
     * Get a sum a of regular expenses for a particular [Category] synchronously
     *
     * @param category - [Category] to focus on
     * @return a list of [ExpenseSumContainer] containing only the [ExpenseSumContainer] linked with [category]
     */
    suspend fun getMonthlyExpensesSumForCategorySync(
        category: Category
    ) : List<ExpenseSumContainer>? {
        return repository.getExpensesSumByFrequencyForCategorySync(Frequency.OUNCE_A_MONTH,category)
    }

    /**
     * Method to delete all the expenses from the database
     * It is executed in a coroutine in [viewModelScope]
     *
     * @return [Unit] Nothing
     */
    fun wipeExpense() = viewModelScope.launch {
        repository.wipeExpense()
    }
}