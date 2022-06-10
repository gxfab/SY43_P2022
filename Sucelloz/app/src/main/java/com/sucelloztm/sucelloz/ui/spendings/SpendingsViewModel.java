package com.sucelloztm.sucelloz.ui.spendings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.repositories.InfrequentExepensesAndIncomeRepository;

import java.util.List;

public class SpendingsViewModel extends AndroidViewModel {
    private InfrequentExepensesAndIncomeRepository spendingsRepository;

    private LiveData<List<InfrequentExpensesAndIncome>> currentPositiveSpendings;

    private LiveData<List<InfrequentExpensesAndIncome>> currentNegativeSpendings;

    public SpendingsViewModel(@NonNull Application application) {
        super(application);
        this.spendingsRepository = new InfrequentExepensesAndIncomeRepository(application);
    }

    public LiveData<List<InfrequentExpensesAndIncome>> getAllPositiveSpendings(){
        if (this.currentPositiveSpendings == null){
            this.currentPositiveSpendings = spendingsRepository.getAllPositiveInfrequent();
        }
        return this.currentPositiveSpendings;
    }

    public LiveData<List<InfrequentExpensesAndIncome>> getAllNegativeSpendings(){
        if(this.currentNegativeSpendings==null){
            this.currentNegativeSpendings = spendingsRepository.getAllNegativeInfrequent();
        }
        return  this.currentNegativeSpendings;
    }
}
