package com.sucelloztm.sucelloz.ui.zerobudget;

import android.app.Application;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.InfrequentExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.SavingsRepository;
import com.sucelloztm.sucelloz.repositories.StableExpensesAndIncomeRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;



public class ZeroBudgetViewModel extends AndroidViewModel {

    private SubCategoriesRepository subCategoriesRepository;

    private InfrequentExpensesAndIncomeRepository infrequentExpensesAndIncomeRepository;
    private SavingsRepository savingsRepository;
    private StableExpensesAndIncomeRepository stableExpensesAndIncomeRepository;
    private LiveData<Integer> infrequentExpenses;
    private LiveData<Integer> infrequentIncomes;
    private LiveData<Integer> stableExpenses;
    private LiveData<Integer> stableIncomes;
    private LiveData<Integer> savings;

    public ZeroBudgetViewModel(Application application) {
        super(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
        this.infrequentExpensesAndIncomeRepository = new InfrequentExpensesAndIncomeRepository(application);
        this.stableExpensesAndIncomeRepository = new StableExpensesAndIncomeRepository(application);
        this.savingsRepository = new SavingsRepository(application);

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
        if(this.infrequentIncomes==null){
            this.infrequentIncomes = this.infrequentExpensesAndIncomeRepository.getSumOfInfrequentIncomes();
        }
        return this.infrequentIncomes;
    }
    public LiveData<Integer> getStableExpenses(){
        if(this.stableExpenses==null){
            this.stableExpenses=this.stableExpensesAndIncomeRepository.getSumOfStableExpenses();
        }
        return this.stableExpenses;
    }
    public LiveData<Integer> getStableIncomes(){
        if(this.stableIncomes==null){
            this.stableIncomes=this.stableExpensesAndIncomeRepository.getSumOfStableIncomes();
        }
        return this.stableIncomes;
    }

    public LiveData<Integer> getSavings(){
        if(this.savings==null){
            this.savings=this.savingsRepository.getSumOfSavings();
        }
        return this.savings;
    }

}

