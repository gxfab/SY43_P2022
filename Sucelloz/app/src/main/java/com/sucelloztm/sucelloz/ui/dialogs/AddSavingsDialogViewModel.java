package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.Savings;
import com.sucelloztm.sucelloz.repositories.SavingsRepository;

/**
 * class to add a savings dialog to a viewmodel
 */
public class AddSavingsDialogViewModel extends AndroidViewModel {
    private SavingsRepository savingsRepository;

    /**
     * custom constructor
     * @param application application
     */
    public AddSavingsDialogViewModel(@NonNull Application application) {
        super(application);
        this.savingsRepository = new SavingsRepository(application);
    }

    /**
     * invokes the insert saving query
     * @param saving saving
     */
    public void insert(Savings saving) { this.savingsRepository.insert(saving);}
}
