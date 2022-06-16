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

public class SpendingsViewModel extends AndroidViewModel {
    private InfrequentExpensesAndIncomeRepository spendingsRepository;
    private SubCategoriesRepository subCategoriesRepository;
    private CategoriesRepository categoriesRepository;

    private LiveData<List<InfrequentExpensesAndIncome>> currentPositiveSpendings;

    private LiveData<List<InfrequentExpensesAndIncome>> currentNegativeSpendings;

    public SpendingsViewModel(@NonNull Application application) {
        super(application);
        this.spendingsRepository = new InfrequentExpensesAndIncomeRepository(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
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

    public SubCategories getSubCategoryByName(String nameOfSubCategory){
        return this.subCategoriesRepository.getSubCategoryWithName(nameOfSubCategory);
    }

    public String getSubCategoryNameWithId(long idOfSubCategory){
        return subCategoriesRepository.getSubCategoryNameWithId(idOfSubCategory);
    }

    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithPositiveInfrequentSum(){
        return subCategoriesRepository.getAllSubCategoriesWithPositiveInfrequentSum();
    }

    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithNegativeInfrequentSum(){
        return subCategoriesRepository.getAllSubCategoriesWithNegativeInfrequentSum();
    }
}
