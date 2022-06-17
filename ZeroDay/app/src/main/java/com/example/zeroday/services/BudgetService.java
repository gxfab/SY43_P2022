package com.example.zeroday.services;

import android.content.Context;

import com.example.zeroday.models.Budget;
import com.example.zeroday.models.Income;
import com.example.zeroday.repositories.BudgetRepository;

public class BudgetService extends ZeroBaseServices<BudgetRepository, Budget> {

    public BudgetService(Context context) {
        super(context);
        this.repository = new BudgetRepository(this.sqLiteDatabase);
    }

    public void addIncome(String budgetCode,Income income){
        Budget budget = this.repository.findOneByCode(budgetCode);
        if (budget != null){
            income.setBudget(budget);
            new IncomeService(this.context).create(income);
        }
    }

}
