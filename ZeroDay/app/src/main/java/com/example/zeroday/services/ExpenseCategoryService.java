package com.example.zeroday.services;

import android.content.Context;

import com.example.zeroday.models.ExpenseCategory;
import com.example.zeroday.repositories.ExpenseCategoryRepository;

public class ExpenseCategoryService extends ZeroBaseServices<ExpenseCategoryRepository, ExpenseCategory> {

    public ExpenseCategoryService(Context context) {
        super(context);
    }
}
