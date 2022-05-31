package com.example.zeroday.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.Budget;
import com.example.zeroday.models.Frequence;

import java.util.ArrayList;
import java.util.List;

public class BudgetRepository {

    // Select All Query
    private static final String SELECT_ALL_BUDGET = "SELECT * FROM " + DbHelper.TABLE_BUDGET;

    private SQLiteDatabase sqLiteDatabase;

    public BudgetRepository(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

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
                        Frequence.valueOf(cursor.getString(DbHelper.COL_FRQUENCE_BUDGET_INDEX))
                ));
            }
            return budgets;
        } catch (Exception e) {
            Log.e("BudgetRepository", "fromCursor: ", e);
            return null;
        }
    }

    public List<Budget> getAllBudgets() {
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_BUDGET, null);
        return fromCursor(cursor);
    }

    public Budget getBudgetById(Long idBudget) {
        Cursor cursor = sqLiteDatabase.query(
                DbHelper.TABLE_BUDGET,
                null,
                DbHelper.KEY_ID_BUDGET + " = ?",
                new String[] { idBudget.toString() },
                null,
                null,
                null
        );
        return fromCursor(cursor).get(0);
    }

    public Budget getBudgetByCode(String codeBudget) {
        Cursor cursor = sqLiteDatabase.query(
                DbHelper.TABLE_BUDGET,
                null,
                DbHelper.KEY_CODE_BUDGET + " = ?",
                new String[] { codeBudget },
                null,
                null,
                null
        );
        return fromCursor(cursor).get(0);
    }

    public ContentValues toContentValues(Budget budget) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.KEY_ID_BUDGET, budget.getIdBudget());
        contentValues.put(DbHelper.KEY_CODE_BUDGET, budget.getCodeBudget());
        contentValues.put(DbHelper.KEY_START_DATE_BUDGET, budget.getStartDateBuget());
        contentValues.put(DbHelper.KEY_END_DATE_BUDGET, budget.getEndDateBuget());
        contentValues.put(DbHelper.KEY_FREQUENCY_BUDGET, budget.getFrequence().toString());
        return contentValues;
    }

    public void save(Budget budget) {
        ContentValues contentValues = toContentValues(budget);
        sqLiteDatabase.insert(DbHelper.TABLE_BUDGET, null, contentValues);
    }

    public void update(Budget budget) {
        ContentValues contentValues = toContentValues(budget);
        sqLiteDatabase.update(DbHelper.TABLE_BUDGET, contentValues, DbHelper.KEY_ID_BUDGET + " = ?", new String[] { budget.getIdBudget().toString() });
    }

    public void delete(Budget budget) {
        sqLiteDatabase.delete(DbHelper.TABLE_BUDGET, DbHelper.KEY_ID_BUDGET + " = ?", new String[] { budget.getIdBudget().toString() });
    }

    //TODO: implement research method


}
