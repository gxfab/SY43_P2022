package com.example.zeroday.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.ExpenseCategory;

import java.util.ArrayList;
import java.util.List;
public class ExpenseCategoryRepository {

    // Select All Query
    private static final String SELECT_ALL_EXPENSE_CATEGORY = "SELECT * FROM " + DbHelper.TABLE_EXPENSE_CATEGORY;

    private SQLiteDatabase sqLiteDatabase;

    public ExpenseCategoryRepository(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public List<ExpenseCategory> fromCursor(Cursor cursor) {
        if(cursor == null) {
            return null;
        }
        if(cursor.getCount() == 0) {
            return null;
        }
        //verify cursor data integrity
        if(cursor.getColumnCount() != 3) {
            throw new IllegalStateException("ExpenseCategory table has not 3 columns");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_ID_CATEGORY_TYPE) == -1) {
            throw new IllegalStateException("ExpenseCategory table has not idExpenseCategory column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_CODE_CATEGORY_TYPE) == -1) {
            throw new IllegalStateException("ExpenseCategory table has not codeExpenseCategory column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_LABEL_CATEGORY_TYPE) == -1) {
            throw new IllegalStateException("ExpenseCategory table has not labelExpenseCategory column");
        }
        try {
            List<ExpenseCategory> expenseCategories = new ArrayList<>();
            while(cursor.moveToNext()) {
                expenseCategories.add(new ExpenseCategory(
                        cursor.getLong(DbHelper.COL_ID_CATEGORY_TYPE_INDEX),
                        cursor.getString(DbHelper.COL_CODE_CATEGORY_TYPE_INDEX),
                        cursor.getString(DbHelper.COL_LABEL_CATEGORY_TYPE_INDEX)
                ));
            }
            return expenseCategories;
        } catch (Exception e) {
            Log.e("ExpenseCategoryRepository", "fromCursor: ", e);
            return null;
        }
    }

    public ContentValues toContentValues(ExpenseCategory expenseCategory) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.KEY_ID_CATEGORY_TYPE, expenseCategory.getIdExpenseCategory());
        contentValues.put(DbHelper.KEY_CODE_CATEGORY_TYPE, expenseCategory.getCodeExpenseCategory());
        contentValues.put(DbHelper.KEY_LABEL_CATEGORY_TYPE, expenseCategory.getLabelExpenseCategory());
        return contentValues;
    }

    public List<ExpenseCategory> selectAll() {
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_EXPENSE_CATEGORY, null);
        return fromCursor(cursor);
    }

    public ExpenseCategory findOneById(Long id) {
        String query = SELECT_ALL_EXPENSE_CATEGORY + " WHERE " + DbHelper.KEY_ID_CATEGORY_TYPE + " = ?";
        String[] selectionArgs = {id.toString()};
        Cursor cursor = sqLiteDatabase.rawQuery(query, selectionArgs);
        List<ExpenseCategory> expenseCategories = fromCursor(cursor);
        if(expenseCategories == null || expenseCategories.size() == 0) {
            return null;
        }
        return expenseCategories.get(0);
    }

    public void delete(ExpenseCategory expenseCategory) {
        String query = "DELETE FROM " + DbHelper.TABLE_EXPENSE_CATEGORY + " WHERE " + DbHelper.KEY_ID_CATEGORY_TYPE + " = ?";
        String[] selectionArgs = {expenseCategory.getIdExpenseCategory().toString()};
        sqLiteDatabase.execSQL(query, selectionArgs);
    }

    public void save(ExpenseCategory expenseCategory) {
        ContentValues contentValues = toContentValues(expenseCategory);
        sqLiteDatabase.insert(DbHelper.TABLE_EXPENSE_CATEGORY, null, contentValues);
    }

    public void update(ExpenseCategory expenseCategory) {
        ContentValues contentValues = toContentValues(expenseCategory);
        String whereClause = DbHelper.KEY_ID_CATEGORY_TYPE + " = ?";
        String[] whereArgs = {expenseCategory.getIdExpenseCategory().toString()};
        sqLiteDatabase.update(DbHelper.TABLE_EXPENSE_CATEGORY, contentValues, whereClause, whereArgs);
    }


    public void createDefaultExpeneCategory(){
        this.save(new ExpenseCategory(1L, "1", "Alimentation"));
        this.save(new ExpenseCategory(2L, "2", "Transport"));
        this.save(new ExpenseCategory(3L, "3", "Loisirs"));
        this.save(new ExpenseCategory(4L, "4", "Santé"));
        this.save(new ExpenseCategory(5L, "5", "Divers"));
    }

     


}
