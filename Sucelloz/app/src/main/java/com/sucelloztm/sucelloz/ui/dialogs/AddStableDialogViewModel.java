package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.StableExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

/**
 * Class to add a cstable dialog to a view model
 */
public class AddStableDialogViewModel extends AndroidViewModel {
    private StableExpensesAndIncomeRepository stableExpensesAndIncomeRepository;

    /**
     * Custom constructor
     * @param application application
     */
    public AddStableDialogViewModel(@NonNull Application application) {
        super(application);
        this.stableExpensesAndIncomeRepository = new StableExpensesAndIncomeRepository(application);
    }

    /**
     * Invokes the insert stable query
     * @param stableExpensesAndIncome stable
     */
    public void insert(StableExpensesAndIncome stableExpensesAndIncome){
        this.stableExpensesAndIncomeRepository.insert(stableExpensesAndIncome);
    }

    /**
     * Get current sub-category
     * @return subcategory
     */
    public SubCategories getCurrentSubCategory(){
        return SubCategoriesRepository.getCurrentSubCategory();
    }
}
