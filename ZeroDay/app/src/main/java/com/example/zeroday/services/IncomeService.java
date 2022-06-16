package com.example.zeroday.services;

import android.content.Context;

import com.example.zeroday.models.Income;
import com.example.zeroday.repositories.IncomeRepository;

public class IncomeService extends ZeroBaseServices<IncomeRepository, Income> {

    public IncomeService(Context context) {
        super(context);
        this.repository = new IncomeRepository(this.sqLiteDatabase);
    }
}
