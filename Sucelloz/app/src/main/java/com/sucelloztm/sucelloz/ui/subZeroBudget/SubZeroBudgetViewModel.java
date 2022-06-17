package com.sucelloztm.sucelloz.ui.subZeroBudget;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.repositories.StableExpensesAndIncomeRepository;

import java.util.List;

public class SubZeroBudgetViewModel extends AndroidViewModel {
    private StableExpensesAndIncomeRepository stableExpensesAndIncomeRepository;

    private LiveData<List<StableExpensesAndIncome>> currentStableList;

    public SubZeroBudgetViewModel(@NonNull Application application) {
        super(application);
        this.stableExpensesAndIncomeRepository = new StableExpensesAndIncomeRepository(application);
    }

    public LiveData<List<StableExpensesAndIncome>> getAllStableFromSubCategory(long idOfSubCategory){
        if(this.currentStableList == null) {
            this.currentStableList = this.stableExpensesAndIncomeRepository.getAllStableFromSubCategory(idOfSubCategory);
        }
        return this.currentStableList;
    }

    public StableExpensesAndIncome getStableById(long idOfStable){
        return this.stableExpensesAndIncomeRepository.getStableById(idOfStable);
    }

    public void updateStable(StableExpensesAndIncome stableExpensesAndIncome){
        stableExpensesAndIncomeRepository.updateStable(stableExpensesAndIncome);
    }

    public void deleteStable(StableExpensesAndIncome stableExpensesAndIncome){
        stableExpensesAndIncomeRepository.deleteStable(stableExpensesAndIncome);
    }
}

