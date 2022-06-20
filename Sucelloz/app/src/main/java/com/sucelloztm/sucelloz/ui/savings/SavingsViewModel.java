package com.sucelloztm.sucelloz.ui.savings;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.Savings;
import com.sucelloztm.sucelloz.repositories.SavingsRepository;

import java.util.List;

/**
 * ViewModel SavingsFragment
 */
public class SavingsViewModel extends AndroidViewModel {
    private SavingsRepository savingsRepository;

    private LiveData<List<Savings>> currentSavings;

    /**
     * Custom constructor
     * @param application application
     */
    public SavingsViewModel(Application application){
        super(application);
        this.savingsRepository = new SavingsRepository(application);
    }

    /**
     * Invokes the getAllSavings Method from SavingsRepository
     * @return livedata of a list of the savings
     */
    public LiveData<List<Savings>> getAllSavings(){
        if (this.currentSavings == null){
            this.currentSavings = savingsRepository.getAllSavings();
        }
        return this.currentSavings;
    }

    /**
     * invokes the deleteSaving method from SavingsRepository
     * @param saving saving to delete
     */
    public void deleteSaving(Savings saving){
        this.savingsRepository.deleteSaving(saving);
    }

    /**
     * Invokes the getSavingsById Method from SavingsRepository
     * @param idOfSaving id of the saving
     * @return saving
     */
    public Savings getSavingById(long idOfSaving){
        return this.savingsRepository.getSavingById(idOfSaving);
    }

    /**
     * invokes the updateSaving Method from SavingsRepository
     * @param saving saving to update
     */
    public void updateSaving(Savings saving){
        this.savingsRepository.updateSaving(saving);
    }

}
