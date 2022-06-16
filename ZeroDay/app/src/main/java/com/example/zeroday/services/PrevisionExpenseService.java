package com.example.zeroday.services;

import android.content.Context;

import com.example.zeroday.models.PrevisionExpense;
import com.example.zeroday.repositories.PrevisionExpenseRepository;

public class PrevisionExpenseService extends ZeroBaseServices<PrevisionExpenseRepository, PrevisionExpense> {

    public PrevisionExpenseService(Context context) {
        super(context);
        this.repository = new PrevisionExpenseRepository(this.sqLiteDatabase);
    }
}
