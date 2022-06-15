package com.example.zeroday.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.PrevisionExpense;

import java.util.ArrayList;
import java.util.List;

public class PrevisionExpenseRepository extends ZeroBaseRepository<PrevisionExpense> {

    public PrevisionExpenseRepository(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
        this.tableName = DbHelper.TABLE_PREVISION_EXPENSE;
        this.primaryKeyColumn = DbHelper.KEY_ID_PREVISION_EXPENSE;
    }

    @Override
    public List<PrevisionExpense> fromCursor(Cursor cursor) {
        if(cursor == null) {
            return null;
        }
        if(cursor.getCount() == 0) {
            return null;
        }
        //verify cursor data integrity
        if(cursor.getColumnCount() != 5) {
            throw new IllegalStateException("PrevisionExpense table has not 5 columns");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_ID_PREVISION_EXPENSE) == -1) {
            throw new IllegalStateException("PrevisionExpense table has not idPrevisionExpense column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_ID_EXPENSE_CATEGORY) == -1) {
            throw new IllegalStateException("PrevisionExpense table has not idExpenseCategory column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_LABEL_PREVISION_EXPENSE) == -1) {
            throw new IllegalStateException("PrevisionExpense table has not labelPrevisionExpense column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_AMOUNT_PREVISION_EXPENSE) == -1) {
            throw new IllegalStateException("PrevisionExpense table has not amountPrevisionExpense column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_COMMENT_PREVISION_EXPENSE) == -1) {
            throw new IllegalStateException("PrevisionExpense table has not commentPrevisionExpense column");
        }
        try{
            List<PrevisionExpense> previsionExpenses = new ArrayList<>();
            while(cursor.moveToNext()) {
                previsionExpenses.add(new PrevisionExpense(
                        cursor.getLong(DbHelper.COL_ID_PREVISION_EXPENSE_INDEX),
                        cursor.getString(DbHelper.COL_LABEL_PREVISION_EXPENSE_INDEX),
                        cursor.getDouble(DbHelper.COL_AMOUNT_PREVISION_EXPENSE_INDEX),
                        cursor.getString(DbHelper.COL_COMMENT_PREVISION_EXPENSE_INDEX),
                        new ExpenseCategoryRepository(this.sqLiteDatabase).findOneById(cursor.getLong(DbHelper.COL_FK_ID_PREVISION_EXPENSE_CATEGORY_INDEX))
                ));
            }
            return previsionExpenses;
        }catch(Exception e) {
            Log.e(this.getClass().getName(), "Error while parsing cursor to PrevisionExpense", e);
            return null;
        }

    }

    @Override
    public ContentValues toContentValues(PrevisionExpense previsionExpense) {
        try{
            ContentValues contentValues = new ContentValues();
            // contentValues.put(DbHelper.KEY_ID_PREVISION_EXPENSE, previsionExpense.getId());
            contentValues.put(DbHelper.KEY_ID_EXPENSE_CATEGORY, previsionExpense.getCategoryPrevisionExpense().getId());
            contentValues.put(DbHelper.KEY_LABEL_PREVISION_EXPENSE, previsionExpense.getLabelPrevisionExpense());
            contentValues.put(DbHelper.KEY_AMOUNT_PREVISION_EXPENSE, previsionExpense.getAmountPrevisionExpense());
            contentValues.put(DbHelper.KEY_COMMENT_PREVISION_EXPENSE, previsionExpense.getCommentPrevisionExpense());
            return contentValues;
        }catch(Exception e) {
            Log.e(this.getClass().getName(), "Error while parsing PrevisionExpense to ContentValues", e);
            return null;
        }
    }
}
