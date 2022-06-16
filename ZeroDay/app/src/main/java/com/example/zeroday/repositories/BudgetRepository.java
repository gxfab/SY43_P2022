package com.example.zeroday.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.Budget;
import com.example.zeroday.models.Frequency;

import java.util.ArrayList;
import java.util.List;

public class BudgetRepository extends ZeroBaseRepository<Budget> {


    public BudgetRepository(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
        this.tableName = DbHelper.TABLE_BUDGET;
        this.primaryKeyColumn = DbHelper.KEY_ID_BUDGET;
    }

    @Override
    public List<Budget> fromCursor(Cursor cursor) {
        if(cursor == null) {
            return null;
        }
        if(cursor.getCount() == 0) {
            return null;
        }
        //verify cursor data integrity
        if(cursor.getColumnCount() != 5) {
            throw new IllegalStateException("Budget table has not 3 columns");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_ID_BUDGET) == -1) {
            throw new IllegalStateException("Budget table has not idBudget column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_CODE_BUDGET) == -1) {
            throw new IllegalStateException("Budget table has not codeBudget column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_START_DATE_BUDGET) == -1) {
            throw new IllegalStateException("Budget table has not start date column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_END_DATE_BUDGET) == -1) {
            throw new IllegalStateException("Budget table has not end date column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_FREQUENCY_BUDGET) == -1) {
            throw new IllegalStateException("Budget table has not frequence column");
        }
        try {
            List<Budget> budgets = new ArrayList<>();
            while(cursor.moveToNext()) {
                budgets.add(new Budget(
                        cursor.getLong(DbHelper.COL_ID_BUDGET_INDEX),
                        cursor.getString(DbHelper.COL_CODE_BUDGET_INDEX),
                        cursor.getString(DbHelper.COL_START_DATE_BUDGET_INDEX),
                        cursor.getString(DbHelper.COL_END_DATE_BUDGET_INDEX),
                        Frequency.valueOf(cursor.getString(DbHelper.COL_FREQUENCY_BUDGET_INDEX))
                ));
            }
            return budgets;
        } catch (Exception e) {
            Log.e("BudgetRepository", "fromCursor: ", e);
            return null;
        }
    }


    @Override
    public ContentValues toContentValues(Budget budget) {
        try{
            ContentValues contentValues = new ContentValues();
            // contentValues.put(DbHelper.KEY_ID_BUDGET, budget.getId());
            contentValues.put(DbHelper.KEY_CODE_BUDGET, budget.getCodeBudget());
            contentValues.put(DbHelper.KEY_START_DATE_BUDGET, budget.getStartDateBuget());
            contentValues.put(DbHelper.KEY_END_DATE_BUDGET, budget.getEndDateBuget());
            contentValues.put(DbHelper.KEY_FREQUENCY_BUDGET, budget.getFrequence().toString());
            return contentValues;
        } catch (Exception e) {
            Log.e("BudgetRepository", "toContentValues: ", e);
            return null;
        }
    }


}
