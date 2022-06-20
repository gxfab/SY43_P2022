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

    /**
     * Constructor of ZeroBudgetViewModel Class
     * @param application instance of Application class
     */

    public ZeroBudgetViewModel(Application application) {
        super(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
        this.infrequentExpensesAndIncomeRepository = new InfrequentExpensesAndIncomeRepository(application);
        this.stableExpensesAndIncomeRepository = new StableExpensesAndIncomeRepository(application);
        this.savingsRepository = new SavingsRepository(application);

    }

    /**
     * Set the new current sub-category
     * @param subCategory sub-category to set as current
     */
    public void setCurrentSubCategory(SubCategories subCategory){
        SubCategoriesRepository.setCurrentSubCategory(subCategory);
    }

    /**
     * Find and return a sub-category by it's name
     * @param name string containing the name to search
     * @return subCategory sub-category found
     */
    public SubCategories getSubCategoryByName(String name){
        return this.subCategoriesRepository.getSubCategoryWithName(name);
    }

    /**
     * Set the new text of a given textView
     * @param textView TextView given
     * @param str new text to output
     */
    public void setTextView(TextView textView, String str){
        textView.setText(str);
    }

    /**
     * Set text color of a given textView
     * @param textView TextView gicen
     * @param color new text color
     */
    public void setTextViewColor(TextView  textView, int color){
        textView.setTextColor(color);
    }

    /**
     * Get a LiveData<integer> of infrequent expenses from infrequent expenses and incomes category
     * @return infrequentExpenses a LiveData<integer>
     */
    public LiveData<Integer> getInfrequentExpenses(){
        if(this.infrequentExpenses==null){
            this.infrequentExpenses = this.infrequentExpensesAndIncomeRepository.getSumOfInfrequentExpenses();
        }
        return this.infrequentExpenses;
    }

    /**
     * Get a LiveData<integer> of infrequent incomes from infrequent expenses and incomes category
     * @return infrequentIncomes a LiveData<integer>
     */
    public LiveData<Integer> getInfrequentIncomes(){
        if(this.infrequentIncomes==null){
            this.infrequentIncomes = this.infrequentExpensesAndIncomeRepository.getSumOfInfrequentIncomes();
        }
        return this.infrequentIncomes;
    }

    /**
     * Get a LiveData<integer> of stable expenses from stable expenses and incomes category
     * @return stableExpenses a LiveData<integer>
     */
    public LiveData<Integer> getStableExpenses(){
        if(this.stableExpenses==null){
            this.stableExpenses=this.stableExpensesAndIncomeRepository.getSumOfStableExpenses();
        }
        return this.stableExpenses;
    }

    /**
     * Get a LiveData<integer> of stable incomes from stable expenses and incomes category
     * @return stableIncomes LiveData<integer>
     */
    public LiveData<Integer> getStableIncomes(){
        if(this.stableIncomes==null){
            this.stableIncomes=this.stableExpensesAndIncomeRepository.getSumOfStableIncomes();
        }
        return this.stableIncomes;
    }

    /**
     * Get a LiveData<integer> of savings from savings category
     * @return savings a LiveData<integer>
     */
    public LiveData<Integer> getSavings(){
        if(this.savings==null){
            this.savings=this.savingsRepository.getSumOfSavings();
        }
        return this.savings;
    }

}

