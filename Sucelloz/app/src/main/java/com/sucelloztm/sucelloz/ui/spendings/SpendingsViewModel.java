package com.sucelloztm.sucelloz.ui.spendings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.CategoriesWithSubCategoriesWithInfrequentSum;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentSum;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;
import com.sucelloztm.sucelloz.repositories.InfrequentExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.util.List;

/**
 * ViewModel for Spendings
 */
public class SpendingsViewModel extends AndroidViewModel {
    private InfrequentExpensesAndIncomeRepository spendingsRepository;
    private SubCategoriesRepository subCategoriesRepository;

    private LiveData<List<InfrequentExpensesAndIncome>> currentPositiveSpendings;

    private LiveData<List<InfrequentExpensesAndIncome>> currentNegativeSpendings;

    /**
     * Custom constructor
     * @param application application
     */
    public SpendingsViewModel(@NonNull Application application) {
        super(application);
        this.spendingsRepository = new InfrequentExpensesAndIncomeRepository(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
    }

    /**
     * Invokes the getAllPositiveSpendings Method from SpendingsRepository
     * @return livedata of a list of all the positive spendings
     */
    public LiveData<List<InfrequentExpensesAndIncome>> getAllPositiveSpendings(){
        if (this.currentPositiveSpendings == null){
            this.currentPositiveSpendings = spendingsRepository.getAllPositiveInfrequent();
        }
        return this.currentPositiveSpendings;
    }


    /**
     * Invokes the getAllNegativeSpendings Method from SpendingsRepository
     * @return livedata of a list of all the negative spendings
     */
    public LiveData<List<InfrequentExpensesAndIncome>> getAllNegativeSpendings(){
        if(this.currentNegativeSpendings==null){
            this.currentNegativeSpendings = spendingsRepository.getAllNegativeInfrequent();
        }
        return  this.currentNegativeSpendings;
    }


    /**
     * Invokes the getSubCategoryNameWithId Method from SubCategoriesRepository
     * @param idOfSubCategory id of the subcategory
     * @return name of the subcategory
     */
    public String getSubCategoryNameWithId(long idOfSubCategory){
        return subCategoriesRepository.getSubCategoryNameWithId(idOfSubCategory);
    }

    /**
     * Invokes the getAllSubCategoriesWithPositiveInfrequentSum Method from SubCategoriesRepository
     * @return livedata of the list of all subcategories with their respective positive sums
     */
    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithPositiveInfrequentSum(){
        return subCategoriesRepository.getAllSubCategoriesWithPositiveInfrequentSum();
    }

    /**
     * Invokes the getAllSubCategoriesWithNegativeInfrequentSum Method from SubCategoriesRepository
     * @return livedata of the list of all subcategories with their respective negative sums
     */
    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithNegativeInfrequentSum(){
        return subCategoriesRepository.getAllSubCategoriesWithNegativeInfrequentSum();
    }
}
