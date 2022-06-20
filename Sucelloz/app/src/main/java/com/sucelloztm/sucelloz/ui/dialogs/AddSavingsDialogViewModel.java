package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.Savings;
import com.sucelloztm.sucelloz.repositories.SavingsRepository;

/**
 * ViewModel for AddSavingsDialogFragment
 */
public class AddSavingsDialogViewModel extends AndroidViewModel {
    private SavingsRepository savingsRepository;

    /**
     * Custom constructor
     * @param application application
     */
    public AddSavingsDialogViewModel(@NonNull Application application) {
        super(application);
        this.savingsRepository = new SavingsRepository(application);
    }

    /**
     * Invokes the insert saving query
     * @param saving saving
     */
    public void insert(Savings saving) { this.savingsRepository.insert(saving);}
}
