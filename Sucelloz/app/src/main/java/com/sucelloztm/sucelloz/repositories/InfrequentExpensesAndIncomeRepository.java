package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.InfrequentExpensesAndIncomeDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;

import java.util.List;

/**
 * class for the infrequent expenses and income repository
 */
public class InfrequentExpensesAndIncomeRepository {
    private InfrequentExpensesAndIncomeDao infrequentExpensesAndIncomeDao;

    /**
     * custom constructor
     * @param application current application
     */
    public InfrequentExpensesAndIncomeRepository(Application application){
        SucellozDatabase database = SucellozDatabase.getInstance(application);
        this.infrequentExpensesAndIncomeDao = database.infrequentExpensesAndIncomeDao();
    }

    public LiveData<List<InfrequentExpensesAndIncome>> getAllInfrequent(){
        return infrequentExpensesAndIncomeDao.getAllInfrequent();
    }

    /**
     * invokes the query to get all infrequents positevely signed
     * @return livedata of the infrequents
     */
    public LiveData<List<InfrequentExpensesAndIncome>> getAllPositiveInfrequent(){
        return infrequentExpensesAndIncomeDao.getAllPositiveInfrequent();
    }

    /**
     * invokes the query to get all infrequents negatively signed
     * @return livedata of the infrequents
     */
    public LiveData<List<InfrequentExpensesAndIncome>> getAllNegativeInfrequent(){
        return infrequentExpensesAndIncomeDao.getAllNegativeInfrequent();
    }

    /**
     * invokes the query to insert an infrequent
     * @param spending infrequent to insert
     */
    public void insert(InfrequentExpensesAndIncome spending){
        infrequentExpensesAndIncomeDao.insertInfrequentExpenseAndIncome(spending);
    }

    /**
     * invokes the query to sum all infrequent expenses
     * @return livedata of the sum
     */

    public LiveData<Integer> getSumOfInfrequentExpenses(){
        return infrequentExpensesAndIncomeDao.getSumOfInfrequentExpenses();
    }

    /**
     * invokes the query to sum all infrequent incomes
     * @return livedata of the sum
     */

    public LiveData<Integer> getSumOfInfrequentIncomes(){
        return infrequentExpensesAndIncomeDao.getSumOfInfrequentIncomes();
    }

}
