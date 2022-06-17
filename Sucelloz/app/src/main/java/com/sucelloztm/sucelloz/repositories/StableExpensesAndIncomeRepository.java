package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.StableExpensesAndIncomeDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;

import java.util.List;

/**
 * class for the stable expenses and income repository
 */
public class StableExpensesAndIncomeRepository {
    private StableExpensesAndIncomeDao stableExpensesAndIncomeDao;

    /**
     * custom constructor
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
     * invokes the query to get all stables positively signed
     * @return livedata of the stables
     */
    public LiveData<List<StableExpensesAndIncome>> getAllPositiveStable(){
        return stableExpensesAndIncomeDao.getAllPositiveStable();
    }

    /**
     * invokes the query to get all stables negatively signed
     * @return livedata of the stables
     */
    public LiveData<List<StableExpensesAndIncome>> getAllNegativeStable(){
        return stableExpensesAndIncomeDao.getAllNegativeStable();
    }

    /**
     * invokes the query to insert an stable
     * @param stableExpensesAndIncome stable to insert
     */
    public void insert(StableExpensesAndIncome stableExpensesAndIncome){
        stableExpensesAndIncomeDao.insertStableExpensesAndIncome(stableExpensesAndIncome);
    }

    public LiveData<List<StableExpensesAndIncome>> getAllStableFromSubCategory(long idOfSubCategory){
        return this.stableExpensesAndIncomeDao.getAllStableFromSubCategory(idOfSubCategory);
    }
    /**
     * invokes the query to sum all infrequent expenses
     * @return livedata of the sum
     */

    public LiveData<Integer> getSumOfStableExpenses(){
        return stableExpensesAndIncomeDao.getSumOfStableExpenses();
    }

    /**
     * invokes the query to sum all infrequent incomes
     * @return livedata of the sum
     */

    public LiveData<Integer> getSumOfStableIncomes(){
        return stableExpensesAndIncomeDao.getSumOfStableIncomes();
    }

    public StableExpensesAndIncome getStableById(long idOfStable){
        return stableExpensesAndIncomeDao.getStableById(idOfStable);
    }

    public void updateStable(StableExpensesAndIncome stableExpensesAndIncome){
        stableExpensesAndIncomeDao.updateStableExpenseOrIncome(stableExpensesAndIncome);
    }

    public void deleteStable(StableExpensesAndIncome stableExpensesAndIncome){
        stableExpensesAndIncomeDao.deleteStableExpenseOrIncome(stableExpensesAndIncome);
    }
}
