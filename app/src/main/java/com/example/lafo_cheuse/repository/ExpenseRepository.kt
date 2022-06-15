package com.example.lafo_cheuse.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.database.dao.ExpenseDao
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Expense
import com.example.lafo_cheuse.models.ExpenseSumContainer
import com.example.lafo_cheuse.models.Frequency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository of the expenses.
 *
 * @property expenseDao - An [ExpenseDao] instance which will execute the queries
 * @property allExpenses - A [LiveData] list of [Expense] with all the expenses including monthly & one-time incomes
 *
 * @constructor It will initialize all the properties with the [LiveData] asked from the [expenseDao]
 *
 * @param application - The [Application] which it is linked with
 */
class ExpenseRepository(application: Application) {
    private var expenseDao : ExpenseDao
    private var allExpenses : LiveData<List<Expense>>?

    init {
        val database : LafoCheuseDatabase? = LafoCheuseDatabase.getInstance(application)
        expenseDao = database?.expenseDao()!!
        allExpenses = expenseDao.getExpenses()
    }

    /**
     * Insert an [Expense] in database
     *
     * @param expense - The [Expense] to insert in database
     */
    suspend fun insertExpense(expense: Expense) = withContext(Dispatchers.IO) {
        expenseDao.insertExpense(expense)
    }

    /**
     * Method to update an expense in the database
     * It is executed with a context in [Dispatchers.IO] thread
     *
     * @param expense - [Expense] that will be updated
     */
    suspend fun updateExpense(expense : Expense) = withContext(Dispatchers.IO) {
        expenseDao.updateExpense(expense)
    }

    /**
     * Method to delete an expense in the database
     * It is executed with a context in [Dispatchers.IO] thread
     *
     * @param bId - [Long] corresponding to the [Expense] id that will be deleted
     */
    suspend fun deleteExpense(bId : Long) = withContext(Dispatchers.IO) {
        expenseDao.deleteExpense(bId)
    }

    /**
     * Method to get all the expenses
     *
     * @return [allExpenses] a [LiveData] list of [Expense] that has been initialized in the constructor
     */
    fun getExpenses() : LiveData<List<Expense>>? {
        return allExpenses
    }

    /**
     * Method to get a particular expense
     *
     * @param expenseId - [Long] value containing the ID of the expense
     * @return a [LiveData] list of [Expense] containing the targeted [Expense]
     */
    fun getExpense(expenseId : Long): LiveData<List<Expense>> {
        return expenseDao.getExpense(expenseId)
    }

    /**
     * Method to get all the expenses with the specified [frequency]
     *
     * @param frequency - [Frequency] of the desired expenses
     * @return a [LiveData] list of [Expense]
     */
    fun getExpensesByFrequency(frequency: Frequency) : LiveData<List<Expense>> {
        return expenseDao.getExpensesByFrequency(frequency)
    }

    /**
     * Get a list of sum of expenses for a particular month with a specified frequency
     *
     * @param frequency - [Frequency] of the desired expenses
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] list of [ExpenseSumContainer]
     */
    fun getExpensesSumByCategoryAndMonth(
        frequency: Frequency,
        year: Int,
        month: Int
    ) : LiveData<List<ExpenseSumContainer>> {
        return expenseDao.getExpensesSumByCategoryAndMonth(frequency,year,month)
    }

    /**
     * Get a list of sum of expenses for a particular month and a particular category with a
     * specified frequency
     *
     * @param frequency - [Frequency] of the desired expenses
     * @param category - [Category] of the targeted category
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] list of [ExpenseSumContainer]
     */
    fun getExpensesSumForCategoryAndMonth(
        frequency: Frequency,
        category: Category,
        year: Int,
        month: Int
    ) : LiveData<List<ExpenseSumContainer>> {
        return expenseDao.getExpensesSumForCategoryAndMonth(frequency,category.categoryId,year,month)
    }

    /**
     * Get a list of sum of expenses for a particular month and a particular category with a
     * specified frequency synchronously
     *
     * @param frequency - [Frequency] of the desired expenses
     * @param category - [Category] of the targeted category
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a list of [ExpenseSumContainer]
     */
    suspend fun getExpensesSumForCategoryAndMonthSync(
        frequency: Frequency,
        category: Category,
        year: Int,
        month: Int
    ) : List<ExpenseSumContainer> {
        return expenseDao.getExpensesSumForCategoryAndMonthSync(frequency,category.categoryId,year,month)
    }

    /**
     * Get a list of sum of expenses in a particular category with a specified [frequency]
     *
     * @return a [LiveData] list of [ExpenseSumContainer]
     */
    fun getExpensesSumByCategory(frequency: Frequency) : LiveData<List<ExpenseSumContainer>> {
        return expenseDao.getExpensesSumByCategory(frequency)
    }

    /**
     * Get a sum of all expenses for a particular month with a specified [frequency]
     *
     * @param frequency - [Frequency] of the desired expenses
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] containing a [Double]
     */
    fun getExpensesSumByDate(frequency: Frequency,year : Int, month : Int) : LiveData<Double> {
        return expenseDao.getExpensesSumByDate(frequency,year,month)
    }

    /**
     * Get a sum of expenses for a particular category and a specified [Frequency]
     *
     * @param frequency - the [Frequency] of the expense
     * @param categoryID - [Long] id of a [Category] targeted
     * @return a [LiveData] list of [ExpenseSumContainer]
     */
    fun getExpensesSumForCategory(frequency: Frequency, categoryID: Long) : LiveData<List<ExpenseSumContainer>> {
        return expenseDao.getExpensesSumForCategory(frequency,categoryID)
    }

    /**
     * Get a sum of all expenses with a specified [frequency]
     *
     * @param frequency - the [Frequency] of the expense
     * @return [LiveData] of [Double] containing the sum
     */
    fun getExpensesSumByFrequency(frequency: Frequency) : LiveData<Double> {
        return expenseDao.getExpensesSum(frequency)
    }

    /**
     * Get a sum of all expenses with a specified [frequency]
     *
     * @param frequency - the [Frequency] of the expense
     * @return [Double] containing the sum
     */
    suspend fun getExpensesSumByFrequencySync(frequency: Frequency) : Double {
        return expenseDao.getExpensesSumSynchronous(frequency)
    }

    /**
     * Method to delete all the expenses from the database
     * It is executed with a context on the [Dispatchers.IO] thread
     *
     * @return [Unit] Nothing
     */
    suspend fun wipeExpense() = withContext(Dispatchers.IO) {
        expenseDao.wipeExpense()
    }
}