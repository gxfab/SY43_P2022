package com.example.zeroday.repositories;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.Expense;
import com.example.zeroday.models.ExpenseCategory;
import com.example.zeroday.models.Frequency;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository extends ZeroBaseRepository<Expense> {


    public ExpenseRepository(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
        this.tableName = DbHelper.TABLE_EXPENSE;
        this.primaryKeyColumn = DbHelper.KEY_ID_EXPENSE;
    }

    @Override
    public List<Expense> fromCursor(Cursor cursor) {
        if(cursor == null) {
            return null;
        }
        if(cursor.getCount() == 0) {
            return null;
        }
        //verify cursor data integrity
        if(cursor.getColumnCount() != 7) {
            throw new IllegalStateException("Expense table has not 5 columns");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_ID_EXPENSE) == -1) {
            throw new IllegalStateException("Expense table has not idExpense column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_ID_EXPENSE_CATEGORY) == -1) {
            throw new IllegalStateException("Expense table has not idExpenseCategory column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_LABEL_EXPENSE) == -1) {
            throw new IllegalStateException("Expense table has not  labelExpense column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_AMOUNT_EXPENSE) == -1) {
            throw new IllegalStateException("Expense table has not amountExpense column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_DATE_EXPENSE) == -1) {
            throw new IllegalStateException("Expense table has not dateExpense column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_FREQUENCY_EXPENSE) == -1) {
            throw new IllegalStateException("Expense table has not frequencyExpense column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_FK_ID_EXPENSE_CATEGORY) == -1) {
            throw new IllegalStateException("Expense table has not fkIdExpenseCategory column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_COMMENT_EXPENSE) == -1) {
            throw new IllegalStateException("Expense table has not commentExpense column");
        }
        try{
            List<Expense> expenses = new ArrayList<>();
            while(cursor.moveToNext()) {
                expenses.add(new Expense(
                        cursor.getLong(DbHelper.COL_ID_EXPENSE_INDEX),
                        cursor.getString(DbHelper.COL_LABEL_EXPENSE_INDEX),
                        cursor.getDouble(DbHelper.COL_AMOUNT_EXPENSE_INDEX),
                        cursor.getString(DbHelper.COL_DATE_EXPENSE_INDEX),
                        Frequency.valueOf(cursor.getString(DbHelper.COL_FREQUENCY_EXPENSE_INDEX)),
                        new ExpenseCategoryRepository(this.sqLiteDatabase).findOneById(cursor.getLong(DbHelper.COL_FK_ID_EXPENSE_CATEGORY_INDEX)),
                        cursor.getString(DbHelper.COL_COMMENT_EXPENSE_INDEX)
                ));
            }
            return expenses;
        }catch(Exception e) {
            Log.e(TAG, "Error while parsing cursor to Expense", e);
            return null;
        }
    }

    @Override
    public ContentValues toContentValues(Expense expense) {
        try{
            ContentValues contentValues = new ContentValues();
//            contentValues.put(DbHelper.KEY_ID_EXPENSE_CATEGORY, expense.getCategoryExpense().getId());
            contentValues.put(DbHelper.KEY_LABEL_EXPENSE, expense.getLabelExpense());
            contentValues.put(DbHelper.KEY_AMOUNT_EXPENSE, expense.getAmountExpense());
            contentValues.put(DbHelper.KEY_DATE_EXPENSE, expense.getDateExpense());
            contentValues.put(DbHelper.KEY_FREQUENCY_EXPENSE, expense.getFrequenceExpense().toString());
            contentValues.put(DbHelper.KEY_FK_ID_EXPENSE_CATEGORY, expense.getCategoryExpense().getId());
            contentValues.put(DbHelper.KEY_COMMENT_EXPENSE, expense.getCommentExpense());
            return contentValues;
        }catch(Exception e) {
            Log.e(TAG, "Error while parsing Expense to ContentValues", e);
            return null;
        }
    }
}
