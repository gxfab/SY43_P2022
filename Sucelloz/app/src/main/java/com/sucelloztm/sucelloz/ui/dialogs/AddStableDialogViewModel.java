package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.StableExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

public class AddStableDialogViewModel extends AndroidViewModel {
    private StableExpensesAndIncomeRepository stableExpensesAndIncomeRepository;

    public AddStableDialogViewModel(@NonNull Application application) {
        super(application);
        this.stableExpensesAndIncomeRepository = new StableExpensesAndIncomeRepository(application);
    }

    public void insert(StableExpensesAndIncome stableExpensesAndIncome){
        this.stableExpensesAndIncomeRepository.insert(stableExpensesAndIncome);
    }

    public SubCategories getCurrentSubCategory(){
        return SubCategoriesRepository.getCurrentSubCategory();
    }
}
