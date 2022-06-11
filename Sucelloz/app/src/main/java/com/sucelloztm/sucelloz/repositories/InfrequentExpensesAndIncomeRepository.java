package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.InfrequentExpensesAndIncomeDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;

import java.util.List;

public class InfrequentExpensesAndIncomeRepository {
    private InfrequentExpensesAndIncomeDao infrequentExpensesAndIncomeDao;

    public InfrequentExpensesAndIncomeRepository(Application application){
        SucellozDatabase database = SucellozDatabase.getInstance(application);
        this.infrequentExpensesAndIncomeDao = database.infrequentExpensesAndIncomeDao();
    }

    public LiveData<List<InfrequentExpensesAndIncome>> getAllInfrequent(){
        return infrequentExpensesAndIncomeDao.getAllInfrequent();
    }

    public LiveData<List<InfrequentExpensesAndIncome>> getAllPositiveInfrequent(){
        return infrequentExpensesAndIncomeDao.getAllPositiveInfrequent();
    }

    public LiveData<List<InfrequentExpensesAndIncome>> getAllNegativeInfrequent(){
        return infrequentExpensesAndIncomeDao.getAllNegativeInfrequent();
    }

    public void insert(InfrequentExpensesAndIncome spending){
        infrequentExpensesAndIncomeDao.insertInfrequentExpenseAndIncome(spending);
    }



}
