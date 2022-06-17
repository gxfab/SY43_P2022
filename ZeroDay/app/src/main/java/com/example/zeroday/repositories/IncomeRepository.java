package com.example.zeroday.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.Frequency;
import com.example.zeroday.models.Income;

import java.util.ArrayList;
import java.util.List;

public class IncomeRepository extends ZeroBaseRepository<Income> {


    public IncomeRepository(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
        this.tableName = DbHelper.TABLE_INCOME;
        this.primaryKeyColumn = DbHelper.KEY_ID_INCOME;
    }

    @Override
    public List<Income> fromCursor(Cursor cursor) {
        if(cursor == null) {
            return null;
        }
        if(cursor.getCount() == 0) {
            return null;
        }
        //verify cursor data integrity
        if(cursor.getColumnCount() != 6) {
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
        if(cursor.getColumnIndex(DbHelper.KEY_FK_ID_BUDJET_INCOME ) == -1) {
            throw new IllegalStateException("Income table has not fkIdBudgetIncome column");
        }
        try {
            List<Income> incomes = new ArrayList<>();
            while(cursor.moveToNext()) {
                incomes.add(new Income(
                        cursor.getLong(DbHelper.COL_ID_INCOME_INDEX),
                        Frequency.valueOf( cursor.getString(DbHelper.COL_FREQUENCY_INCOME_INDEX)),
                        cursor.getLong(DbHelper.COL_AMOUNT_INCOME_INDEX),
                        cursor.getString(DbHelper.COL_LABEL_INCOME_INDEX),
                        new IncomeCategoryRepository(this.sqLiteDatabase).findOneById(cursor.getLong(DbHelper.COL_ID_INCOME_CATEGORY_INDEX)),
                        new BudgetRepository(this.sqLiteDatabase).findOneById(cursor.getLong(DbHelper.COL_FK_ID_BUDJET_INCOME_INDEX))
                ));
            }
            return incomes;
        } catch (Exception e) {
            Log.e("IncomeRepository", "fromCursor: ", e);
            return null;
        }
}

    @Override
    public ContentValues toContentValues(Income income) {
        try{
            ContentValues contentValues = new ContentValues();
//            contentValues.put(DbHelper.KEY_ID_INCOME, income.getId());
            contentValues.put(DbHelper.KEY_LABEL_INCOME, income.getLabelIncome());
            contentValues.put(DbHelper.KEY_AMOUNT_INCOME, income.getAmountIncome());
            contentValues.put(DbHelper.KEY_ID_INCOME_CATEGORY, income.getIncomeCategories().getId());
            contentValues.put(DbHelper.KEY_FREQUENCY_INCOME, income.getFrequencyIncome().toString());
            contentValues.put(DbHelper.KEY_FK_ID_BUDJET_INCOME, income.getBudget().getId());
            return contentValues;
        } catch (Exception e) {
            Log.e("IncomeRepository", "toContentValues: ", e);
            return null;
        }
    }
}
