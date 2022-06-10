package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.repositories.InfrequentExepensesAndIncomeRepository;

public class AddSpendingDialogViewModel extends AndroidViewModel {
    private InfrequentExepensesAndIncomeRepository spendingRepository;

    public AddSpendingDialogViewModel(@NonNull Application application) {
        super(application);
        this.spendingRepository = new InfrequentExepensesAndIncomeRepository(application);
    }
    public void insert(InfrequentExpensesAndIncome spending){
        this.spendingRepository.insert(spending);
        }
}
