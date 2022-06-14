package com.example.zeroday.repositories;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.Frequency;
import com.example.zeroday.models.Income;

import java.util.ArrayList;
import java.util.List;

public class IncomeRepository {

    private SQLiteDatabase sqLiteDatabase;

    public IncomeRepository(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

     public List<Income> fromCursor(Cursor cursor) {
        if(cursor == null) {
            return null;
        }
        if(cursor.getCount() == 0) {
            return null;
        }
        //verify cursor data integrity
        if(cursor.getColumnCount() != 5) {
            throw new IllegalStateException("Income table has not 5 columns");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_ID_INCOME) == -1) {
            throw new IllegalStateException("Income table has not idIncome column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_LABEL_INCOME) == -1) {
            throw new IllegalStateException("Income table has not labelIncome column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_AMOUNT_INCOME) == -1) {
            throw new IllegalStateException("Income table has not amountIncome column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_ID_INCOME_CATEGORY) == -1) {
            throw new IllegalStateException("Income table has not dateIncome column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_FREQUENCY_INCOME) == -1) {
            throw new IllegalStateException("Income table has not frequenceIncome column");
        }
        try {
            List<Income> incomes = new ArrayList<>();
            IncomesCategoryRepository incomesCategoryRepository = new IncomesCategoryRepository(this.sqLiteDatabase);
            while(cursor.moveToNext()) {
                incomes.add(new Income(
                        cursor.getLong(DbHelper.COL_ID_INCOME_INDEX),
                        Frequency.valueOf( cursor.getString(DbHelper.COL_FRQUENCY_INCOME_INDEX)),
                        cursor.getLong(DbHelper.COL_AMOUNT_INCOME_INDEX),
                        cursor.getString(DbHelper.COL_LABEL_INCOME_INDEX),
                        incomesCategoryRepository.findOne(cursor.getInt(DbHelper.COL_ID_INCOME_CATEGORY_INDEX))
                        
                ));
            }
            return incomes;
        } catch (Exception e) {
            Log.e("IncomeRepository", "fromCursor: ", e);
            return null;
        }
}}
