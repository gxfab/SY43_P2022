package com.example.lafo_cheuse.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.database.dao.IncomeDao
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.models.Income
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine

/**
 * Repository of the incomes.
 *
 * @property incomeDao - An [IncomeRepository] instance which will make the queries
 * @property allIncomes - A [LiveData] list of [Income] with all the incomes including monthly & one-time incomes
 * @property incomesSum - A [LiveData] sum containing the sum of all the incomes. It is pretty useless.
 *
 * @constructor It will initialize all the properties with the [LiveData] asked from [incomeDao]
 *
 * @param application - The [Application] which it is linked with
 */
class IncomeRepository(application: Application) {
    private var incomeDao : IncomeDao
    private var incomesSum : LiveData<Double>
    private var allIncomes : LiveData<List<Income>>?

    init {
        val database : LafoCheuseDatabase? = LafoCheuseDatabase.getInstance(application)
        incomeDao = database?.incomeDao()!!
        incomesSum = incomeDao.getIncomesSum()
        allIncomes = incomeDao.getIncomes()
    }

    /**
     * Method to insert an [Income] into the database
     * It is executed with a context in [Dispatchers.IO] thread
     *
     * @param income
     * @return [Unit] nothing
     */
    suspend fun insertIncome(income : Income) = withContext(Dispatchers.IO) {
        incomeDao.insertIncome(income)
    }

    /**
     * Method to update an income in the database
     * It is executed with a context in [Dispatchers.IO] thread
     *
     * @param income - [Income] that will be updated
     * @return [Unit] Nothing
     */
    suspend fun updateIncome(income : Income) = withContext(Dispatchers.IO) {
        incomeDao.updateIncome(income)
    }

    /**
     * Method to delete an income in the database
     * It is executed with a context in [Dispatchers.IO] thread
     *
     * @param bId - [Long] id of the targeted [Income]
     * @return [Unit] Nothing
     */
    suspend fun deleteIncome(bId : Long) = withContext(Dispatchers.IO) {
        incomeDao.deleteIncome(bId)
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
        return incomeDao.getIncomesForMonth(year,month)
    }

    /**
     * Method to retrieve a sum of all the incomes ever created. it is pretty useless.
     *
     * @return [incomesSum] which has been initialized in the constructor
     */
    fun getIncomesSum() : LiveData<Double> {
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
    fun getIncomesSumByDate(year: Int, month: Int) : LiveData<Double> {
        return incomeDao.getIncomesSumByDate(year,month)
    }

    /**
     * Method to retrieve a sum of all the incomes ever created but in a synchronous way.
     * Warning : It should be used in a coroutine or it will crash the application
     *
     * @return a [Double] containing the sum of all the incomes
     */
    suspend fun getIncomesSumSync() : Double {
        return incomeDao.getIncomesSumSynchronous()
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
        return incomeDao.getIncomesSumSynchronousByDate(year, month)
    }

    /**
     * Method to get a particular income with its id
     *
     * @param moneyChangeId - a [Long] corresponding to the [Income] id
     * @return a [LiveData] list of [Income] to avoid an error where 2 incomes have the same ID
     */
    fun getIncome(moneyChangeId : Long): LiveData<List<Income>> {
        return incomeDao.getIncome(moneyChangeId)
    }

    /**
     * Method to get all the incomes with specified [frequency]
     *
     * @param frequency - [Frequency] of the desired incomes
     * @return a [LiveData] list of regular [Income]
     */
    fun getIncomesByFrequency(frequency: Frequency) : LiveData<List<Income>> {
        return incomeDao.getIncomesByFrequency(frequency)
    }

    /**
     * Method to get the [Income] of a particular month with a specified [frequency]
     *
     * @param frequency - [Frequency] of the desired incomes
     * @param year - [Int] containing the year of the month we want to show
     * @param month - [Int] containing the month (from 0 to 11) we want to show
     * @return a [LiveData] list of regular [Income] corresponding to a particular month
     */
    fun getIncomesByFrequencyAndMonth(
        frequency: Frequency = Frequency.OUNCE_A_DAY,
        year : Int,
        month : Int
    ) : LiveData<List<Income>> {
        return incomeDao.getIncomesByFrequencyAndMonth(frequency,year, month)
    }

    /**
     * Method to delete all the incomes from the database
     * It is executed with a context in [Dispatchers.IO] thread
     *
     * @return [Unit] Nothing
     */
    suspend fun wipeIncome() = withContext(Dispatchers.IO) {
        incomeDao.wipeIncome()
    }


}