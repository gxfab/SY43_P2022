package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.InfrequentExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.util.List;

/**
 * class to add a spending dialog to a view model
 */
public class AddSpendingDialogViewModel extends AndroidViewModel {
    private InfrequentExpensesAndIncomeRepository spendingRepository;
    private SubCategoriesRepository subCategoriesRepository;

    /**
     * custom constructor
     * @param application application
     */
    public AddSpendingDialogViewModel(@NonNull Application application) {
        super(application);
        this.spendingRepository = new InfrequentExpensesAndIncomeRepository(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
    }

    /**
     * invokes the insert spending query
     * @param spending spending
     */
    public void insert(InfrequentExpensesAndIncome spending){
        this.spendingRepository.insert(spending);
    }

    /**
     * invokes the query to get all subcategories names
     * @return list of names
     */
    public List<String> getSubCategoriesNames(){
        return this.subCategoriesRepository.getSubCategoriesNames();
    }

    /**
     * invokes the query to get a subcategory by its name
     * @param nameOfSubCategory name of the subcategory
     * @return subcategory
     */
    public SubCategories getSubCategoryWithName(String nameOfSubCategory){
        return this.subCategoriesRepository.getSubCategoryWithName(nameOfSubCategory);
    }
}
