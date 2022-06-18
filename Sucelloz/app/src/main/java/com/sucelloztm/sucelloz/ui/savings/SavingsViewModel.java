package com.sucelloztm.sucelloz.ui.savings;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.Savings;
import com.sucelloztm.sucelloz.repositories.SavingsRepository;

import java.util.List;

/**
 * class for the savings view model
 */
public class SavingsViewModel extends AndroidViewModel {
    private SavingsRepository savingsRepository;

    private LiveData<List<Savings>> currentSavings;

    /**
     * custom constructor
     * @param application application
     */
    public SavingsViewModel(Application application){
        super(application);
        this.savingsRepository = new SavingsRepository(application);
    }

    /**
     * invokes the get all savings query
     * @return livedata of the savings
     */
    public LiveData<List<Savings>> getAllSavings(){
        if (this.currentSavings == null){
            this.currentSavings = savingsRepository.getAllSavings();
        }
        return this.currentSavings;
    }

    /**
     * invokes the delete saving query
     * @param saving saving
     */
    public void deleteSaving(Savings saving){
        this.savingsRepository.deleteSaving(saving);
    }

    /**
     * invokes the get saving by its id query
     * @param idOfSaving id of the saving
     * @return saving
     */
    public Savings getSavingById(long idOfSaving){
        return this.savingsRepository.getSavingById(idOfSaving);
    }

    /**
     * invokes the update saving query
     * @param saving saving
     */
    public void updateSaving(Savings saving){
        this.savingsRepository.updateSaving(saving);
    }

}
