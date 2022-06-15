package com.example.zeroday.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.IncomeCategory;
import com.example.zeroday.models.IncomeCategory;
import com.example.zeroday.models.ZeroBaseModel;

import java.util.ArrayList;
import java.util.List;


public class IncomesCategoryRepository extends ZeroBaseRepository<IncomeCategory> {


    public IncomesCategoryRepository(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
        this.tableName = DbHelper.TABLE_INCOME_CATEGORY;
        this.primaryKeyColumn = DbHelper.KEY_ID_INCOME_CATEGORY;
    }

    @Override
    public List<IncomeCategory> fromCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        if (cursor.getCount() == 0) {
            return null;
        }
        //verify cursor data integrity
        if (cursor.getColumnCount() != 3) {
            throw new IllegalStateException("IncomeCategory table has not 3 columns");
        }
        if (cursor.getColumnIndex(DbHelper.KEY_ID_INCOME_CATEGORY) == -1) {
            throw new IllegalStateException("IncomeCategory table has not idIncomeCategory column");
        }
        if (cursor.getColumnIndex(DbHelper.KEY_CODE_INCOME_CATEGORY) == -1) {
            throw new IllegalStateException("IncomeCategory table has not codeIncomeCategory column");
        }
        if (cursor.getColumnIndex(DbHelper.KEY_LABEL_INCOME_CATEGORY) == -1) {
            throw new IllegalStateException("IncomeCategory table has not labelIncomeCategory column");
        }
        try {
            List<IncomeCategory> expenseCategories = new ArrayList<>();
            while (cursor.moveToNext()) {
                expenseCategories.add(new IncomeCategory(
                        cursor.getLong(DbHelper.COL_ID_INCOME_CATEGORY_INDEX),
                        cursor.getString(DbHelper.COL_CODE_INCOME_CATEGORY_INDEX),
                        cursor.getString(DbHelper.COL_LABEL_INCOME_INDEX)
                ));
            }
            return expenseCategories;
        } catch (Exception e) {
            Log.e("IncomeCategoryRepository", "fromCursor: ", e);
            return null;
        }
    }

    @Override
    public ContentValues toContentValues(IncomeCategory incomeCategory) {
        ContentValues contentValues = new ContentValues();
//        contentValues.put(DbHelper.KEY_ID_INCOME_CATEGORY, incomeCategory.getId());
        contentValues.put(DbHelper.KEY_CODE_INCOME_CATEGORY, incomeCategory.getCodeIncomeCategory());
        contentValues.put(DbHelper.KEY_LABEL_INCOME_CATEGORY, incomeCategory.getLabelIncomeCategory());
        return contentValues;
    }

}
