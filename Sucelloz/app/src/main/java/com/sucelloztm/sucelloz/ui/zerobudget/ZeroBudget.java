package com.sucelloztm.sucelloz.ui.zerobudget;

import android.widget.TextView;

/**
 * class for the zero budget
 */
public class ZeroBudget {
    private int infrequentExpenses=0;
    private int infrequentIncomes=0;
    private int stableExpenses=0;
    private int stableIncomes=0;
    private int savings=0;
    private int resultBudgetZero=0;
    public ZeroBudget() {
    }

    public int getInfrequentExpenses() {
        return infrequentExpenses;
    }

    /**
     * setter
     * @param infrequentExpenses infrequent
     */
    public void setInfrequentExpenses(int infrequentExpenses) {
        this.infrequentExpenses = infrequentExpenses;
    }

    public int getInfrequentIncomes() {
        return infrequentIncomes;
    }

    /**
     * setter
     * @param infrequentIncomes infrequent
     */
    public void setInfrequentIncomes(int infrequentIncomes) {
        this.infrequentIncomes = infrequentIncomes;
    }

    public int getStableExpenses() {
        return stableExpenses;
    }

    /**
     * setter
     * @param stableExpenses stable
     */
    public void setStableExpenses(int stableExpenses) {
        this.stableExpenses = stableExpenses;
    }

    public int getStableIncomes() {
        return stableIncomes;
    }

    /**
     * setter
     * @param stableIncomes stable
     */
    public void setStableIncomes(int stableIncomes) {
        this.stableIncomes = stableIncomes;
    }


    public int getSavings() {
        return savings;
    }

    public void setSavings(int savings) {
        this.savings = savings;
    }

  /**
   * getter
   * @return result budget zero
   */
    public int getResultBudgetZero() {
        return resultBudgetZero;
    }

    /**
     * setter
     * @param textView text
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
