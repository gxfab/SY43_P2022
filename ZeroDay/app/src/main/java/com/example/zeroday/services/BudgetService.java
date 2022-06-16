package com.example.zeroday.services;

import android.content.Context;

import com.example.zeroday.models.Budget;
import com.example.zeroday.repositories.BudgetRepository;

public class BudgetService extends ZeroBaseServices<BudgetRepository, Budget> {

    public BudgetService(Context context) {
        super(context);
        this.repository = new BudgetRepository(this.sqLiteDatabase);
    }
}
