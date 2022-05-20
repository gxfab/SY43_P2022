package com.example.zeroday.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.IncomesCategory;

import java.util.ArrayList;
import java.util.List;


public class IncomesRepository {

    // Select All Query
    private static final String SELECT_ALL_INCOMES_CATEGORY = "SELECT * FROM " + DbHelper.TABLE_INCOMES_CATEGORY;

    private static final SQLiteDatabase sqLiteDatabase = new DbHelper(null).getDatabase();

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

    public static ContentValues toContentValues(IncomesCategory incomesCategory) {
        if(incomesCategory == null) {
            return null;
        }
        return new ContentValues(3) {{
            put(DbHelper.KEY_ID_CATEGORY_TYPE, incomesCategory.getIdIncomesCategory());
            put(DbHelper.KEY_CODE_CATEGORY_TYPE, incomesCategory.getCodeIncomesCategory());
            put(DbHelper.KEY_LABEL_CATEGORY_TYPE, incomesCategory.getLabelIncomesCategory());
        }};
    }

    public static List<IncomesCategory> findAll(){
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_INCOMES_CATEGORY, null);
            return fromCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }finally {
            sqLiteDatabase.close();
        }      
    }

    public static IncomesCategory findOne(int id){
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_INCOMES_CATEGORY + " WHERE " + DbHelper.KEY_ID_CATEGORY_TYPE + " = " + id, null);
            // get first
            if(cursor.moveToFirst()) {
                return fromCursor(cursor).get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }finally {
            sqLiteDatabase.close();
        }
        
    }

    public static void delete(IncomesCategory incomesCategory) {
        try {
            sqLiteDatabase.delete(DbHelper.TABLE_INCOMES_CATEGORY, DbHelper.KEY_ID_CATEGORY_TYPE + " = ?", new String[]{incomesCategory.getIdIncomesCategory()});
         } catch (Exception e) {
            e.printStackTrace();

        }finally {
            sqLiteDatabase.close();
        } 
    }

    public static void save(IncomesCategory incomesCategory) {
        try {
            sqLiteDatabase.insert(DbHelper.TABLE_INCOMES_CATEGORY, null, toContentValues(incomesCategory));
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            sqLiteDatabase.close();
        } 
    }

    public static int getCount() {
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_INCOMES_CATEGORY, null);
            return cursor.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }finally {
            sqLiteDatabase.close();
        } 
    }



    public static void createDefaultIncomesCategory(){
        save(new IncomesCategory("1", "1", "Salary"));
        save(new IncomesCategory("2", "2", "Bonus"));
        save(new IncomesCategory("3", "3", "Gift"));
        save(new IncomesCategory("4", "4", "Other"));
    }
}
