package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.Savings;
import com.sucelloztm.sucelloz.repositories.SavingsRepository;

public class AddSavingsDialogViewModel extends AndroidViewModel {
    private SavingsRepository savingsRepository;

    public AddSavingsDialogViewModel(@NonNull Application application) {
        super(application);
        this.savingsRepository = new SavingsRepository(application);
    }

    public void insert(Savings saving) { this.savingsRepository.insert(saving);}
}
