package com.example.zeroday.services;

import android.content.Context;

import com.example.zeroday.models.IncomeCategory;
import com.example.zeroday.repositories.IncomeCategoryRepository;

public class IncomeCategoryService extends ZeroBaseServices<IncomeCategoryRepository, IncomeCategory> {


    public IncomeCategoryService(Context context) {
        super(context);
        this.repository = new IncomeCategoryRepository(this.sqLiteDatabase);
    }

}
