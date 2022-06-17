package com.example.zeroday.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.ExpenseCategory;
import com.example.zeroday.models.ZeroBaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpenseCategoryRepository extends ZeroBaseRepository<ExpenseCategory> {


    public ExpenseCategoryRepository(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
        this.tableName = DbHelper.TABLE_EXPENSE_CATEGORY;
        this.primaryKeyColumn = DbHelper.KEY_ID_EXPENSE_CATEGORY;
    }



    @Override
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
        if(cursor.getColumnIndex(DbHelper.KEY_ID_EXPENSE_CATEGORY) == -1) {
            throw new IllegalStateException("ExpenseCategory table has not idExpenseCategory column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_CODE_EXPENSE_CATEGORY) == -1) {
            throw new IllegalStateException("ExpenseCategory table has not codeExpenseCategory column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_LABEL_EXPENSE_CATEGORY) == -1) {
            throw new IllegalStateException("ExpenseCategory table has not labelExpenseCategory column");
        }
        try {
            List<ExpenseCategory> expenseCategories = new ArrayList<>();
            while(cursor.moveToNext()) {
                expenseCategories.add(new ExpenseCategory(
                        cursor.getLong(DbHelper.COL_ID_EXPENSE_CATEGORY_INDEX),
                        cursor.getString(DbHelper.COL_CODE_EXPENSE_CATEGORY_INDEX),
                        cursor.getString(DbHelper.COL_LABEL_EXPENSE_CATEGORY_INDEX)
                ));
            }
            return expenseCategories;
        } catch (Exception e) {
            Log.e("ExpenseCategoryRepository", "fromCursor: ", e);
            return null;
        }
    }


    @Override
    public ContentValues toContentValues(ExpenseCategory expenseCategory) {
        try {
            ContentValues contentValues = new ContentValues();
            // contentValues.put(DbHelper.KEY_ID_EXPENSE_CATEGORY, expenseCategory.getId());
            contentValues.put(DbHelper.KEY_CODE_EXPENSE_CATEGORY, expenseCategory.getCodeExpenseCategory());
            contentValues.put(DbHelper.KEY_LABEL_EXPENSE_CATEGORY, expenseCategory.getLabelExpenseCategory());
            return contentValues;
        } catch (Exception e) {
            Log.e("ExpenseCategoryRepository", "toContentValues: ", e);
            return null;
        }
    }

    @Override
    public ExpenseCategory findOneByCode(String code){
        try {
            String query = "SELECT * FROM " + this.tableName + " WHERE " + DbHelper.KEY_CODE_EXPENSE_CATEGORY + " = '" + code + "'";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            return fromCursor(cursor).get(0);
        } catch (Exception e) {
            Log.e("ExpenseCategoryRepository", "findOneByCode: ", e);
            return null;
        }
    }


}