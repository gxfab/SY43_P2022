package com.sucelloztm.sucelloz.ui.zerobudget;

import android.app.Application;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.InfrequentExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.StableExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.util.List;

public class ZeroBudgetViewModel extends AndroidViewModel {

    private SubCategoriesRepository subCategoriesRepository;

    private InfrequentExpensesAndIncomeRepository infrequentExpensesAndIncomeRepository;

    private StableExpensesAndIncomeRepository stableExpensesAndIncomeRepository;
    private LiveData<Integer> infrequentExpenses;
    private Integer infrequentIncomes;
    private Integer stableExpenses;
    private Integer stableIncomes;

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

    public LiveData<Integer> getInfrequentExpenses(){
        if(this.infrequentExpenses==null){
            this.infrequentExpenses = this.infrequentExpensesAndIncomeRepository.getSumOfInfrequentExpenses();
        }
        return this.infrequentExpenses;
    }
    public LiveData<Integer> getInfrequentIncomes(){
        return this.infrequentExpensesAndIncomeRepository.getSumOfInfrequentIncomes();
    }
    public LiveData<Integer> getStableExpenses(){
        return this.stableExpensesAndIncomeRepository.getSumOfStableExpenses();
    }
    public LiveData<Integer> getStableIncomes(){
        return this.stableExpensesAndIncomeRepository.getSumOfStableIncomes();
    }

}

