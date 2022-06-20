package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.SavingsDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Savings;

import java.util.List;

/**
 * Class for the savings repository
 */
public class SavingsRepository {
    private SavingsDao savingsDao;

    /**
     * Custom constructor
     * @param application current application
     */
    public SavingsRepository(Application application){
        SucellozDatabase database=SucellozDatabase.getInstance(application);
        this.savingsDao=database.savingsDao();
    }

    /**
     * Invokes the query to get all savings
     * @return livedata of the savings
     */
    public LiveData<List<Savings>> getAllSavings(){
        return savingsDao.getAllSavings();
    }

    /**
     * Invokes the query to insert a saving
     * @param saving saving to insert
     */
    public void insert(Savings saving) { savingsDao.insertSaving(saving); }

    /**
     * Invokes the query to delete a saving
     * @param saving saving to delete
     */
    public void deleteSaving(Savings saving){
        this.savingsDao.deleteSaving(saving);
    }

    /**
     * Invokes the query to get a saving by its id
     * @param idOfSaving id of the searched saving
     * @return searched saving
     */
    public Savings getSavingById(long idOfSaving){
        return this.savingsDao.getSavingById(idOfSaving);
    }

    /**
     * Invokes the query to update a saving
     * @param saving saving to update
     */
    public void updateSaving(Savings saving){
        this.savingsDao.updateSaving(saving);
    }

    public LiveData<Integer> getSumOfSavings(){
        return this.savingsDao.getSumOfSavings();
    }
}
