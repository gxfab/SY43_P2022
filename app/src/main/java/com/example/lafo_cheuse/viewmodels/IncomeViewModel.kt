package com.example.lafo_cheuse.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.models.Income
import com.example.lafo_cheuse.repository.IncomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel of the incomes.
 *
 * @property repository - An [IncomeRepository] instance which will receive the queries
 * @property allIncomes - A [LiveData] list of [Income] with all the incomes including monthly & one-time incomes
 * @property incomesSum - A [LiveData] sum containing the sum of all the incomes. It is pretty useless.
 *
 * @constructor It will initialize all the properties with the [LiveData] asked from the [repository]
 *
 * @param application - The [Application] which it is linked with
 */
class IncomeViewModel(application : Application) : AndroidViewModel(application) {
    private var repository : IncomeRepository
    var allIncomes : LiveData<List<Income>>?
    var incomesSum : LiveData<Double>

    init {
        repository = IncomeRepository(application)
        allIncomes = repository.getIncomes()
        incomesSum = repository.getIncomesSum()
    }

    /**
     * Method to get all the incomes.
     *
     * @return [allIncomes] which has been initialized in the constructor
     */
    fun getIncomes() : LiveData<List<Income>>? {
        return allIncomes
    }

    /**
     * Method to get all the incomes of a particular month.
     * Warning : It get all the incomes of the month & the regular incomes.
     *
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] list of [Income] in the month
     */
    fun getIncomesForMonth(year: Int,month: Int): LiveData<List<Income>> {
        return repository.getIncomesForMonth(year,month)
    }

    /**
     * Method to retrieve a sum of all the incomes ever created. it is pretty useless.
     *
     * @return [incomesSum] which has been initialized in the constructor
     */
    fun getIncomeSum() : LiveData<Double> {
        return incomesSum
    }

    /**
     * Method to get a sum of all the incomes in a particular month.
     * Warning : It get a sum of all the incomes of the month & the regular incomes.
     *
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] containing the sum of [Income]
     */
    fun getIncomeSumByDate(year: Int, month: Int) : LiveData<Double> {
        return repository.getIncomesSumByDate(year,month)
    }

    /**
     * Method to retrieve a sum of all the incomes ever created but in a synchronous way.
     * Warning : It should be used in a coroutine or it will crash the application
     *
     * @return a [Double] containing the sum of all the incomes
     */
    suspend fun getIncomeSumSync() : Double {
        return repository.getIncomesSumSync()
    }

    /**
     * Method to get a sum of all the incomes in a particular month but in a synchronous way.
     * Warning : It should be used in a coroutine or it will crash the application.
     * Warning : It get a sum of all the incomes of the month & the regular incomes.
     *
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [Double] containing the sum of all the incomes in the month
     */
    suspend fun getIncomesSumSyncByDate(year: Int, month: Int) : Double {
        return repository.getIncomesSumSyncByDate(year, month)
    }

    /**
     * Method to get a particular income with its id
     *
     * @param moneyChangeId - a [Long] corresponding to the [Income] id
     * @return a [LiveData] list of [Income] to avoid an error where 2 incomes have the same ID
     */
    fun getIncome(moneyChangeId : Long): LiveData<List<Income>> {
        return repository.getIncome(moneyChangeId)
    }

    /**
     * Method to get all the regular incomes
     *
     * @return a [LiveData] list of regular [Income]
     */
    fun getMonthlyIncome() : LiveData<List<Income>> {
        return repository.getIncomesByFrequency(Frequency.OUNCE_A_MONTH)
    }

    /**
     * Method to get all the one-time incomes
     *
     * @return a [LiveData] list of one-time [Income]
     */
    fun getOneTimeIncome() : LiveData<List<Income>> {
        return repository.getIncomesByFrequency(Frequency.OUNCE_A_DAY)
    }

    /**
     * Method to get the regular [Income] of a particular month
     *
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] list of regular [Income] corresponding to a particular month
     */
    fun getMonthlyIncomesByMonth(
        year : Int,
        month : Int
    ) : LiveData<List<Income>> {
        return repository.getIncomesByFrequencyAndMonth(Frequency.OUNCE_A_MONTH,year, month)
    }

    /**
     * Method to get the one time [Income] of a particular month
     *
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] list of one-time [Income] corresponding  to a particular month
     */
    fun getOneTimeIncomesByMonth(
        year : Int,
        month : Int
    ) : LiveData<List<Income>> {
        return repository.getIncomesByFrequencyAndMonth(Frequency.OUNCE_A_DAY,year, month)
    }

    /**
     * Method to insert an [Income] into the database
     *
     * @param income
     * @return [Unit] nothing
     */
    fun insertIncome(income : Income) = viewModelScope.launch {
        repository.insertIncome(income)
    }

    /**
     * Method to update an income in the database
     * It is executed in a coroutine in [viewModelScope]
     *
     * @param income - [Income] that will be updated
     * @return [Unit] Nothing
     */
    fun updateIncome(income : Income) = viewModelScope.launch {
        repository.updateIncome(income)
    }

    /**
     * Method to delete an income in the database
     * It is executed in a coroutine in [viewModelScope]
     *
     * @param income - [Income] that will be deleted
     * @return [Unit] Nothing
     */
    fun deleteIncome(income: Income) = viewModelScope.launch {
        repository.deleteIncome(income.moneyChangeId)
    }

    /**
     * Method to delete all the incomes from the database
     * It is executed in a coroutine in [viewModelScope]
     *
     * @return [Unit] Nothing
     */
    fun wipeIncome() = viewModelScope.launch {
        repository.wipeIncome()
    }
}