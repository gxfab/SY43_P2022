package com.sucelloztm.sucelloz.ui.savings;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.Savings;
import com.sucelloztm.sucelloz.repositories.SavingsRepository;

import java.util.List;

public class SavingsViewModel extends AndroidViewModel {
    private SavingsRepository savingsRepository;

    private LiveData<List<Savings>> currentSavings;

    public SavingsViewModel(Application application){
        super(application);
        this.savingsRepository = new SavingsRepository(application);
    }

    public LiveData<List<Savings>> getAllSavings(){
        if (this.currentSavings == null){
            this.currentSavings = savingsRepository.getAllSavings();
        }
        return this.currentSavings;
    }
}
