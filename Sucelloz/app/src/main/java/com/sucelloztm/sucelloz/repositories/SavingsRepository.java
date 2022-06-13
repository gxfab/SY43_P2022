package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.SavingsDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Savings;

import java.util.List;

public class SavingsRepository {
    private SavingsDao savingsDao;

    public SavingsRepository(Application application){
        SucellozDatabase database=SucellozDatabase.getInstance(application);
        this.savingsDao=database.savingsDao();
    }

    public LiveData<List<Savings>> getAllSavings(){
        return savingsDao.getAllSavings();
    }

    public void insert(Savings saving) { savingsDao.insertSaving(saving); }

    public void deleteSaving(Savings saving){
        this.savingsDao.deleteSaving(saving);
    }

    public Savings getSavingById(long idOfSaving){
        return this.savingsDao.getSavingById(idOfSaving);
    }

    public void updateSaving(Savings saving){
        this.savingsDao.updateSaving(saving);
    }
}
