package com.sucelloztm.sucelloz.ui.subZeroBudget;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.repositories.StableExpensesAndIncomeRepository;

import java.util.List;

/**
 * view model for the sub zero budget
 */
public class SubZeroBudgetViewModel extends AndroidViewModel {
    private StableExpensesAndIncomeRepository stableExpensesAndIncomeRepository;

    private LiveData<List<StableExpensesAndIncome>> currentStableList;

    /**
     * custom constructor
     * @param application application
     */
    public SubZeroBudgetViewModel(@NonNull Application application) {
        super(application);
        this.stableExpensesAndIncomeRepository = new StableExpensesAndIncomeRepository(application);
    }

    /**
     * ivokes the query to get all stables of a subcategory
     * @param idOfSubCategory id of the subcategory
     * @return livedata with the list of stables
     */
    public LiveData<List<StableExpensesAndIncome>> getAllStableFromSubCategory(long idOfSubCategory){
        if(this.currentStableList == null) {
            this.currentStableList = this.stableExpensesAndIncomeRepository.getAllStableFromSubCategory(idOfSubCategory);
        }
        return this.currentStableList;
    }

    /**
     * invokes the query to get a stable thanks to its id
     * @param idOfStable id of the stable
     * @return stable
     */
    public StableExpensesAndIncome getStableById(long idOfStable){
        return this.stableExpensesAndIncomeRepository.getStableById(idOfStable);
    }

    /**
     * invokes the update stable query
     * @param stableExpensesAndIncome stable
     */
    public void updateStable(StableExpensesAndIncome stableExpensesAndIncome){
        stableExpensesAndIncomeRepository.updateStable(stableExpensesAndIncome);
    }

    /**
     * invokes the delete stable query
     * @param stableExpensesAndIncome stable
     */
    public void deleteStable(StableExpensesAndIncome stableExpensesAndIncome){
        stableExpensesAndIncomeRepository.deleteStable(stableExpensesAndIncome);
    }
}

