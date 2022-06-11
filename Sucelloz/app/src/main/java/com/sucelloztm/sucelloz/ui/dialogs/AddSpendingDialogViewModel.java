package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.InfrequentExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.util.List;

public class AddSpendingDialogViewModel extends AndroidViewModel {
    private InfrequentExpensesAndIncomeRepository spendingRepository;
    private SubCategoriesRepository subCategoriesRepository;

    public AddSpendingDialogViewModel(@NonNull Application application) {
        super(application);
        this.spendingRepository = new InfrequentExpensesAndIncomeRepository(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
    }
    public void insert(InfrequentExpensesAndIncome spending){
        this.spendingRepository.insert(spending);
    }

    public List<String> getSubCategoriesNames(){
        return this.subCategoriesRepository.getSubCategoriesNames();
    }

    public SubCategories getSubCategoryWithName(String nameOfSubCategory){
        return this.subCategoriesRepository.getSubCategoryWithName(nameOfSubCategory);
    }
}
