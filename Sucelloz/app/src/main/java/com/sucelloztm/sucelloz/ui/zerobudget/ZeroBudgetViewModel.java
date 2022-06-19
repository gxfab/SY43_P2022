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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * view model for the zero budget
 */
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
    private ExecutorService executor;

    /**
     * custom constructor
     * @param application application
     */
    public ZeroBudgetViewModel(Application application) {
        super(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
        this.infrequentExpensesAndIncomeRepository = new InfrequentExpensesAndIncomeRepository(application);
        this.stableExpensesAndIncomeRepository = new StableExpensesAndIncomeRepository(application);
        this.savingsRepository = new SavingsRepository(application);
        this.executor = Executors.newSingleThreadExecutor();

    }

    /**
     * setter
     * @param subCategory subcategory
     */
    public void setCurrentSubCategory(SubCategories subCategory){
        SubCategoriesRepository.setCurrentSubCategory(subCategory);
    }

    /**
     * invokes the query to get a subcategory thanks to its name
     * @param name name
     * @return subcategory
     */
    public LiveData<SubCategories> getSubCategoryByName(String name){
        return this.subCategoriesRepository.getSubCategoryWithName(name);
    }

    public void setTextView(TextView textView, String str){
        textView.setText(str);
    }
    public void setTextViewColor(TextView  textView, int color){
        textView.setTextColor(color);
    }

    /**
     * getter
     * @return livedata of integers
     */
    public LiveData<Integer> getInfrequentExpenses(){
        if(this.infrequentExpenses==null){
            this.infrequentExpenses = this.infrequentExpensesAndIncomeRepository.getSumOfInfrequentExpenses();
        }
        return this.infrequentExpenses;
    }

    /**
     * getter
     * @return livedata of integers
     */
    public LiveData<Integer> getInfrequentIncomes(){
        if(this.infrequentIncomes==null){
            this.infrequentIncomes = this.infrequentExpensesAndIncomeRepository.getSumOfInfrequentIncomes();
        }
        return this.infrequentIncomes;
    }

    /**
     * getter
     * @return livedata of integers
     */
    public LiveData<Integer> getStableExpenses(){
        if(this.stableExpenses==null){
            this.stableExpenses=this.stableExpensesAndIncomeRepository.getSumOfStableExpenses();
        }
        return this.stableExpenses;
    }

    /**
     * getter
     * @return livedata of integers
     */
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

    public LiveData<List<SubCategories>> getSubCategoriesWithCategoryId(long id){
        return subCategoriesRepository.getSubCategoriesWithCategoryId(id);
    }
}

