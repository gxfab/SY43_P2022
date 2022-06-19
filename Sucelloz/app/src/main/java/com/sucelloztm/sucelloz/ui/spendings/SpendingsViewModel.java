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
 * view model for the spendings
 */
public class SpendingsViewModel extends AndroidViewModel {
    private InfrequentExpensesAndIncomeRepository spendingsRepository;
    private SubCategoriesRepository subCategoriesRepository;
    private CategoriesRepository categoriesRepository;

    private LiveData<List<InfrequentExpensesAndIncome>> currentPositiveSpendings;

    private LiveData<List<InfrequentExpensesAndIncome>> currentNegativeSpendings;

    /**
     * custom constructor
     * @param application application
     */
    public SpendingsViewModel(@NonNull Application application) {
        super(application);
        this.spendingsRepository = new InfrequentExpensesAndIncomeRepository(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
    }

    /**
     * invokes the get all positive spendings query
     * @return livedata of the positive spendings
     */
    public LiveData<List<InfrequentExpensesAndIncome>> getAllPositiveSpendings(){
        if (this.currentPositiveSpendings == null){
            this.currentPositiveSpendings = spendingsRepository.getAllPositiveInfrequent();
        }
        return this.currentPositiveSpendings;
    }


    /**
     * invokes the get all negative spendings query
     * @return livedata of the negative spendings
     */
    public LiveData<List<InfrequentExpensesAndIncome>> getAllNegativeSpendings(){
        if(this.currentNegativeSpendings==null){
            this.currentNegativeSpendings = spendingsRepository.getAllNegativeInfrequent();
        }
        return  this.currentNegativeSpendings;
    }

    public SubCategories getSubCategoryByName(String nameOfSubCategory){
        return this.subCategoriesRepository.getSubCategoryWithName(nameOfSubCategory);
    }

    /**
     * invokes the query to get a subcategory name thanks to its id
     * @param idOfSubCategory id of the subcategory
     * @return name of the subcategory
     */
    public String getSubCategoryNameWithId(long idOfSubCategory){
        return subCategoriesRepository.getSubCategoryNameWithId(idOfSubCategory);
    }

    /**
     * invokes the query to get all subcategories with a positive sum
     * @return livedata of the subcategories with positive sums
     */
    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithPositiveInfrequentSum(){
        return subCategoriesRepository.getAllSubCategoriesWithPositiveInfrequentSum();
    }

    /**
     * invokes the query to get all subcatgories with a negative sum
     * @return livedata of the subcategories with negative sums
     */
    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithNegativeInfrequentSum(){
        return subCategoriesRepository.getAllSubCategoriesWithNegativeInfrequentSum();
    }
}
