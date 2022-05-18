package com.example.zeroday.repositories;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.IncomesCategory;

import java.util.ArrayList;
import java.util.List;


public class IncomesRepository {

    // Select All Query
    private static final String SELECT_ALL_INCOMES_CATEGORY = "SELECT * FROM " + DbHelper.TABLE_INCOMES_CATEGORY;

    @SuppressLint("Range")
    public static List<IncomesCategory> fromCursor(Cursor cursor) {
        if(cursor == null) {
            return null;
        }
        if(cursor.getCount() == 0) {
            return null;
        }
        //verify cursor data integrity
        if(cursor.getColumnCount() != 3) {
            throw new IllegalStateException("IncomesCategory table has not 3 columns");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_ID_CATEGORY_TYPE) == -1) {
            throw new IllegalStateException("IncomesCategory table has not idCategoryType column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_CODE_CATEGORY_TYPE) == -1) {
            throw new IllegalStateException("IncomesCategory table has not codeCategoryType column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_LABEL_CATEGORY_TYPE) == -1) {
            throw new IllegalStateException("IncomesCategory table has not labelCategoryType column");
        }
        try {
            List<IncomesCategory> incomesCategories = new ArrayList<>();
            while(cursor.moveToNext()) {
                incomesCategories.add(new IncomesCategory(
                        cursor.getString(cursor.getColumnIndex(DbHelper.KEY_ID_CATEGORY_TYPE)),
                        cursor.getString(cursor.getColumnIndex(DbHelper.KEY_CODE_CATEGORY_TYPE)),
                        cursor.getString(cursor.getColumnIndex(DbHelper.KEY_LABEL_CATEGORY_TYPE))
                ));
            }
            return incomesCategories;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            cursor.close();
        }
    }

    public List<IncomesCategory> findAll(SQLiteDatabase sqLiteDatabase){
                Cursor cursor = sqLiteDatabase.query(SELECT_ALL_INCOMES_CATEGORY, null, null, null, null, null, null);
        return fromCursor(cursor);
                
    }
}
