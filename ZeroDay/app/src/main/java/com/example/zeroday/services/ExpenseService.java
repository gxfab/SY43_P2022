package com.example.zeroday.services;

import android.content.Context;

import com.example.zeroday.models.Expense;
import com.example.zeroday.repositories.ExpenseRepository;

public class ExpenseService extends ZeroBaseServices<ExpenseRepository, Expense> {

    public ExpenseService(Context context) {
        super(context);
        this.repository = new ExpenseRepository(this.sqLiteDatabase);
    }
}
