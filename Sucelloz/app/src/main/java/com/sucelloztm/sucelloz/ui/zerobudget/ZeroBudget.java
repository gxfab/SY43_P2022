package com.sucelloztm.sucelloz.ui.zerobudget;

import android.widget.TextView;

public class ZeroBudget {
    private int infrequentExpenses=0;
    private int infrequentIncomes=0;
    private int stableExpenses=0;
    private int stableIncomes=0;
    private int savings=0;
    private int resultBudgetZero=0;
    public ZeroBudget() {
    }

    /**
     * Get infrequent Expenses
     * @return int infrequentExpenses
     */
    public int getInfrequentExpenses() {
        return infrequentExpenses;
    }

    /**
     * Set infrequent Expenses
     * @param infrequentExpenses new infrequent expenses
     */
    public void setInfrequentExpenses(int infrequentExpenses) {
        this.infrequentExpenses = infrequentExpenses;
    }

    /**
     * Get infrequent Incomes
     * @return int infrequentIncomes
     */
    public int getInfrequentIncomes() {
        return infrequentIncomes;
    }

    /**
     * Set infrequent Expenses
     * @param infrequentIncomes new infrequent incomes
     */
    public void setInfrequentIncomes(int infrequentIncomes) {
        this.infrequentIncomes = infrequentIncomes;
    }

    /**
     * Get stable Expenses
     * @return int stableExpenses
     */
    public int getStableExpenses() {
        return stableExpenses;
    }

    /**
     * Set stable Expenses
     * @param stableExpenses new stable expenses
     */
    public void setStableExpenses(int stableExpenses) {
        this.stableExpenses = stableExpenses;
    }

    /**
     * Get stable Incomes
     * @return int stableIncomes
     */
    public int getStableIncomes() {
        return stableIncomes;
    }

    /**
     * Set stable incomes
     * @param stableIncomes new stable incomes
     */
    public void setStableIncomes(int stableIncomes) {
        this.stableIncomes = stableIncomes;
    }

    /**
     * Get savings
     * @return int savings
     */
    public int getSavings() {
        return savings;
    }

    /**
     * Set savings
     * @param savings new savings
     */
    public void setSavings(int savings) {
        this.savings = savings;
    }

    /**
     * Get result of Budget Zero calculation
     * @return int resultBudgetZero
     */
    public int getResultBudgetZero() {
        return resultBudgetZero;
    }

    /**
     * Set infrequent Expenses
     * @param textView textView printing out the result on the ZeroBudgetFragment
     */
    public void setResultBudgetZero(TextView textView) {
        this.resultBudgetZero = (this.infrequentIncomes + this.stableIncomes) - (this.infrequentExpenses +this.stableExpenses + this.savings);
        if(this.resultBudgetZero > 0){
            textView.setTextColor(-16711936);
        }else if (this.resultBudgetZero == 0){
            textView.setTextColor(-16776961);
        }else{
            textView.setTextColor(-65536);
        }
        textView.setText(this.resultBudgetZero+"€");
    }
}
