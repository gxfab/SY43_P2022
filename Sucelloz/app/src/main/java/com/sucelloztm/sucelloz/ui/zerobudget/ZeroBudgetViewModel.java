package com.sucelloztm.sucelloz.ui.zerobudget;

import android.app.Application;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;


import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.InfrequentExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.StableExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.util.List;

public class ZeroBudgetViewModel extends AndroidViewModel {
    private SubCategoriesRepository subCategoriesRepository;
    private InfrequentExpensesAndIncomeRepository infrequentExpensesAndIncomeRepository;
    private StableExpensesAndIncomeRepository stableExpensesAndIncomeRepository;

    public ZeroBudgetViewModel(Application application) {
        super(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
        this.infrequentExpensesAndIncomeRepository = new InfrequentExpensesAndIncomeRepository(application);
        this.stableExpensesAndIncomeRepository = new StableExpensesAndIncomeRepository(application);
    }

    public void setCurrentSubCategory(SubCategories subCategory){
        SubCategoriesRepository.setCurrentSubCategory(subCategory);
    }

    public SubCategories getSubCategoryByName(String name){
        return this.subCategoriesRepository.getSubCategoryWithName(name);
    }

    public void setTextView(TextView textView, String str){
        textView.setText(str);
    }
    public void setTextViewColor(TextView  textView, int color){
        textView.setTextColor(color);
    }

    public int getAllZeroBudgetResult(){
        List<InfrequentExpensesAndIncome> infrequentIncomes = infrequentExpensesAndIncomeRepository.getAllPositiveInfrequent().getValue();
        List<InfrequentExpensesAndIncome> infrequentExpenses = infrequentExpensesAndIncomeRepository.getAllNegativeInfrequent().getValue();

        List<StableExpensesAndIncome> stableIncomes = stableExpensesAndIncomeRepository.getAllPositiveStable().getValue();
        List<StableExpensesAndIncome> stableExpenses = stableExpensesAndIncomeRepository.getAllNegativeStable().getValue();



        int intInfrequentExpenses = infrequentExpenses.stream().mapToInt(InfrequentExpensesAndIncome::getAmount).sum();
        int intInfrequentIncomes = infrequentIncomes.stream().mapToInt(InfrequentExpensesAndIncome::getAmount).sum();

        int intStableExpenses = stableExpenses.stream().mapToInt(StableExpensesAndIncome::getAmount).sum();
        int intStableIncomes = stableIncomes.stream().mapToInt(StableExpensesAndIncome::getAmount).sum();


        int zeroBudgetResult = (intInfrequentIncomes + intStableIncomes) - (intInfrequentExpenses + intStableExpenses);

        return zeroBudgetResult;
    }

    public int getSumOfList(List<Object> list){

        int res = 0;

        for (Object object:list
             ) {


        }

        return res;
    }

}

