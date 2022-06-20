package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.StableExpensesAndIncomeDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;

import java.util.List;

/**
 * Class for the stable expenses and income repository
 */
public class StableExpensesAndIncomeRepository {
    private StableExpensesAndIncomeDao stableExpensesAndIncomeDao;

    /**
     * Custom constructor
     * @param application current application
     */
    public StableExpensesAndIncomeRepository(Application application){
        SucellozDatabase database = SucellozDatabase.getInstance(application);
        this.stableExpensesAndIncomeDao = database.stableExpensesAndIncomeDao();
    }

    public LiveData<List<StableExpensesAndIncome>> getAllStable(){
        return stableExpensesAndIncomeDao.getAllStable();
    }

    /**
     * Invokes the query to get all stables positively signed
     * @return livedata of the stables
     */
    public LiveData<List<StableExpensesAndIncome>> getAllPositiveStable(){
        return stableExpensesAndIncomeDao.getAllPositiveStable();
    }

    /**
     * Invokes the query to get all stables negatively signed
     * @return livedata of the stables
     */
    public LiveData<List<StableExpensesAndIncome>> getAllNegativeStable(){
        return stableExpensesAndIncomeDao.getAllNegativeStable();
    }

    /**
     *Invokes the query to insert an stable
     * @param stableExpensesAndIncome stable to insert
     */
    public void insert(StableExpensesAndIncome stableExpensesAndIncome){
        stableExpensesAndIncomeDao.insertStableExpensesAndIncome(stableExpensesAndIncome);
    }

    /**
     * Invokes the query that get all stable elements from a specific sub-category thanks to its id
     * @param idOfSubCategory
     * @return LiveData of all stable elements from the specified sub-category
     */
    public LiveData<List<StableExpensesAndIncome>> getAllStableFromSubCategory(long idOfSubCategory){
        return this.stableExpensesAndIncomeDao.getAllStableFromSubCategory(idOfSubCategory);
    }
    /**
     * invokes the query to sum all stable expenses
     * @return livedata of the sum
     */
    public LiveData<Integer> getSumOfStableExpenses(){
        return stableExpensesAndIncomeDao.getSumOfStableExpenses();
    }

    /**
     * invokes the query to sum all stable incomes
     * @return livedata of the sum
     */

    public LiveData<Integer> getSumOfStableIncomes(){
        return stableExpensesAndIncomeDao.getSumOfStableIncomes();
    }


    /**
     * Invokes the query that get a stable element thanks to its id
     * @param idOfStable id of stable element to get
     * @return resulting stable element
     */
    public StableExpensesAndIncome getStableById(long idOfStable){
        return stableExpensesAndIncomeDao.getStableById(idOfStable);
    }


    /**
     * Invokes the query that updates a stable element
     * @param stableExpensesAndIncome stable element to update
     */
    public void updateStable(StableExpensesAndIncome stableExpensesAndIncome){
        stableExpensesAndIncomeDao.updateStableExpenseOrIncome(stableExpensesAndIncome);
    }

    /**
     * Invokes the query that deletes a stable element
     * @param stableExpensesAndIncome stable element to delete
     */
    public void deleteStable(StableExpensesAndIncome stableExpensesAndIncome){
        stableExpensesAndIncomeDao.deleteStableExpenseOrIncome(stableExpensesAndIncome);
    }
}
