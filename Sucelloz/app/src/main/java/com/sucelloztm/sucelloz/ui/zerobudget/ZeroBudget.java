package com.sucelloztm.sucelloz.ui.zerobudget;

import android.widget.TextView;

public class ZeroBudget {
    private int infrequentExpenses;
    private int infrequentIncomes;
    private int stableExpenses;
    private int stableIncomes;
    private int resultBudgetZero;
    public ZeroBudget() {
    }

    public int getInfrequentExpenses() {
        return infrequentExpenses;
    }

    public void setInfrequentExpenses(int infrequentExpenses) {
        this.infrequentExpenses = infrequentExpenses;
    }

    public int getInfrequentIncomes() {
        return infrequentIncomes;
    }

    public void setInfrequentIncomes(int infrequentIncomes) {
        this.infrequentIncomes = infrequentIncomes;
    }

    public int getStableExpenses() {
        return stableExpenses;
    }

    public void setStableExpenses(int stableExpenses) {
        this.stableExpenses = stableExpenses;
    }

    public int getStableIncomes() {
        return stableIncomes;
    }

    public void setStableIncomes(int stableIncomes) {
        this.stableIncomes = stableIncomes;
    }

    public int getResultBudgetZero() {
        return resultBudgetZero;
    }

    public void setResultBudgetZero(TextView textView) {
        this.resultBudgetZero = (this.infrequentIncomes + this.stableIncomes) - (this.infrequentExpenses +this.stableExpenses);
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
