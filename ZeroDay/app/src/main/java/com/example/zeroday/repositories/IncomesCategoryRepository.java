package com.example.zeroday.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.IncomesCategory;

import java.util.ArrayList;
import java.util.List;


public class IncomesCategoryRepository {

    // Select All Query
    private static final String SELECT_ALL_INCOMES_CATEGORY = "SELECT * FROM " + DbHelper.TABLE_INCOMES_CATEGORY;

    private SQLiteDatabase sqLiteDatabase;

    public IncomesCategoryRepository(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }


//    @SuppressLint("Range")
    public List<IncomesCategory> fromCursor(Cursor cursor) {
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
            throw new IllegalStateException("IncomesCategory table has not idIncomesCategory column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_CODE_CATEGORY_TYPE) == -1) {
            throw new IllegalStateException("IncomesCategory table has not codeIncomesCategory column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_LABEL_CATEGORY_TYPE) == -1) {
            throw new IllegalStateException("IncomesCategory table has not labelIncomesCategory column");
        }
        try {
            List<IncomesCategory> incomesCategories = new ArrayList<>();
            while(cursor.moveToNext()) {
                incomesCategories.add(new IncomesCategory(
                        cursor.getLong(DbHelper.COL_ID_CATEGORY_TYPE_INDEX),
                        cursor.getString(DbHelper.COL_CODE_CATEGORY_TYPE_INDEX),
                        cursor.getString(DbHelper.COL_LABEL_CATEGORY_TYPE_INDEX)
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

    public ContentValues toContentValues(IncomesCategory incomesCategory) {
        if(incomesCategory == null) {
            return null;
        }
        ContentValues contentValues =  new ContentValues(3);
            contentValues.put(DbHelper.KEY_ID_CATEGORY_TYPE, incomesCategory.getIdIncomesCategory());
        contentValues.put(DbHelper.KEY_CODE_CATEGORY_TYPE, incomesCategory.getCodeIncomesCategory());
        contentValues.put(DbHelper.KEY_LABEL_CATEGORY_TYPE, incomesCategory.getLabelIncomesCategory());
       return contentValues;
    }

    public List<IncomesCategory> findAll(){
        try {
            Cursor cursor = this.sqLiteDatabase.rawQuery(SELECT_ALL_INCOMES_CATEGORY, null);
            return fromCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public IncomesCategory findOne(int id){
        try {
            Cursor cursor = this.sqLiteDatabase.rawQuery(SELECT_ALL_INCOMES_CATEGORY + " WHERE " + DbHelper.KEY_ID_CATEGORY_TYPE + " = " + id, null);
            // get first
            if(cursor.moveToFirst()) {
                return fromCursor(cursor).get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return null;
    }

    public void delete(IncomesCategory incomesCategory) {
        try {
            // TODO: correct datatype of id
            this.sqLiteDatabase.delete(DbHelper.TABLE_INCOMES_CATEGORY, DbHelper.KEY_ID_CATEGORY_TYPE + " = ?", new String[]{String.valueOf(incomesCategory.getIdIncomesCategory())});
         } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void save(IncomesCategory incomesCategory) {
        try {
            this.sqLiteDatabase.insert(DbHelper.TABLE_INCOMES_CATEGORY, null, toContentValues(incomesCategory));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public int getCount() {
        try {
            Cursor cursor = this.sqLiteDatabase.rawQuery(SELECT_ALL_INCOMES_CATEGORY, null);
            return cursor.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }



    public void createDefaultIncomesCategory(){
        this.sqLiteDatabase.execSQL("DELETE FROM " + DbHelper.TABLE_INCOMES_CATEGORY);
        this.save(new IncomesCategory(1L, "1", "Salary"));
        this.save(new IncomesCategory(2L, "2", "Bonus"));
        this.save(new IncomesCategory(3L, "3", "Gift"));
        this.save(new IncomesCategory(4L, "4", "Other"));
        Log.i("RTT", "Created");
    }
}
