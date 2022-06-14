package com.example.zeroday.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.IncomeCategory;
import com.example.zeroday.repositories.IncomesCategoryRepository;

import java.util.List;

public class IncomeCategoryService {

    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private IncomesCategoryRepository incomesCategoryRepository;

    public IncomeCategoryService(Context context) {
        this.context = context;
        this.sqLiteDatabase = new DbHelper(this.context).getWritableDatabase();
        this.incomesCategoryRepository = new IncomesCategoryRepository(this.sqLiteDatabase);
    }

    public List<IncomeCategory> findAll(){
        return this.incomesCategoryRepository.findAll();
    }
}
