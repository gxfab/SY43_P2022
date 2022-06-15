package com.example.zeroday.repositories;


import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.content.ContentValues;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.Prevision;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class PrevisionRepository extends ZeroBaseRepository<Prevision> {

    public PrevisionRepository(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
        this.tableName = DbHelper.TABLE_PREVISION;
        this.primaryKeyColumn = DbHelper.KEY_ID_PREVISION;
    }

    @Override
    public List<Prevision> fromCursor(Cursor cursor) {
        if(cursor == null) {
            return null;
        }
        if(cursor.getCount() == 0) {
            return null;
        }
        //verify cursor data integrity
        if(cursor.getColumnCount() != 4) {
            throw new IllegalStateException("Prevision table has not 3 columns");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_ID_PREVISION) == -1) {
            throw new IllegalStateException("Prevision table has not idPrevision column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_FK_ID_BUDGET_PREVISION) == -1) {
            throw new IllegalStateException("Prevision table has not codePrevision column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_AMOUNT_PREVISION) == -1) {
            throw new IllegalStateException("Prevision table has not labelPrevision column");
        }
        if(cursor.getColumnIndex(DbHelper.KEY_DATE_PREVISION) == -1) {
            throw new IllegalStateException("Prevision table has not labelPrevision column");
        }
        try {
            List<Prevision> previsions = new ArrayList<>();
            while(cursor.moveToNext()) {
                previsions.add(new Prevision(
                        cursor.getLong(DbHelper.COL_ID_PREVISION_INDEX),
                        new BudgetRepository(this.sqLiteDatabase).findOneById(cursor.getLong(DbHelper.COL_FK_ID_BUDGET_PREVISION_INDEX)),
                        cursor.getString(DbHelper.COL_AMOUNT_PREVISION_INDEX),
                        cursor.getString(DbHelper.COL_DATE_PREVISION_INDEX),
                        cursor.getString(DbHelper.COL_TIMESTAP_PREVISION_START_INDEX),
                        cursor.getString(DbHelper.COL_TIMESTAP_PREVISION_END_INDEX)
                ));
            }
            return previsions;
        } catch (Exception e) {
            Log.e("PrevisionRepository", "fromCursor: ", e);
            return null;
        }
    }

    @Override
    public ContentValues toContentValues(Prevision prevision) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbHelper.KEY_ID_PREVISION, prevision.getId());
            contentValues.put(DbHelper.KEY_FK_ID_BUDGET_PREVISION, prevision.getBudgetPrevison().getCodeBudget());
            contentValues.put(DbHelper.KEY_AMOUNT_PREVISION, prevision.getAmountPrevision());
            contentValues.put(DbHelper.KEY_DATE_PREVISION, prevision.getDatePrevision());
            contentValues.put(DbHelper.KEY_TIMESTAP_PREVISION_START, prevision.getTimestampPrevisionStartDate());
            contentValues.put(DbHelper.KEY_TIMESTAP_PREVISION_END, prevision.getTimestampPrevisionStartDate());
            return contentValues;
        } catch (Exception e) {
            Log.e("PrevisionRepository", "toContentValues: ", e);
            return null;
        }
    }
}
