package com.sucelloztm.sucelloz.ui.subZeroBudget;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.repositories.StableExpensesAndIncomeRepository;

import java.util.List;

/**
 * ViewModel for SubZeroBudgetFragment
 */
public class SubZeroBudgetViewModel extends AndroidViewModel {
    private StableExpensesAndIncomeRepository stableExpensesAndIncomeRepository;

    private LiveData<List<StableExpensesAndIncome>> currentStableList;

    /**
     * Custom constructor
     *
     * @param application application
     */
    public SubZeroBudgetViewModel(@NonNull Application application) {
        super(application);
        this.stableExpensesAndIncomeRepository = new StableExpensesAndIncomeRepository(application);
    }

    /**
     * Invokes the getAllStableFromSubCategory Method from StableExpensesAndIncomeRepository
     *
     * @param idOfSubCategory id of the subcategory
     * @return livedata with the list of all stables from specific subcategory
     */
    public LiveData<List<StableExpensesAndIncome>> getAllStableFromSubCategory(long idOfSubCategory) {
        if (this.currentStableList == null) {
            this.currentStableList = this.stableExpensesAndIncomeRepository.getAllStableFromSubCategory(idOfSubCategory);
        }
        return this.currentStableList;
    }

    /**
     * Invokes the getStableById Method from StableExpensesAndIncomeRepository
     *
     * @param idOfStable id of the stable
     * @return stable
     */
    public StableExpensesAndIncome getStableById(long idOfStable) {
        return this.stableExpensesAndIncomeRepository.getStableById(idOfStable);
    }

    /**
     * Invokes the updateStable Method from StableExpensesAndIncomeRepository
     *
     * @param stableExpensesAndIncome stable to update
     */
    public void updateStable(StableExpensesAndIncome stableExpensesAndIncome) {
        stableExpensesAndIncomeRepository.updateStable(stableExpensesAndIncome);
    }

    /**
     * invokes the deleteStable from StableExpensesAndIncomeRepository
     *
     * @param stableExpensesAndIncome stable to delete
     */
    public void deleteStable(StableExpensesAndIncome stableExpensesAndIncome) {
        stableExpensesAndIncomeRepository.deleteStable(stableExpensesAndIncome);
    }
}

