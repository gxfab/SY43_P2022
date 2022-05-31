package com.example.zeroday.repositories;

import android.database.sqlite.SQLiteDatabase;

import com.example.zeroday.dao.DbHelper;

public class ZeroBaseRepository {

    // Select All Query
    private static final String SELECT_ALL_INCOME = "SELECT * FROM " + DbHelper.TABLE_INCOME;

    private SQLiteDatabase sqLiteDatabase;

    public ZeroBaseRepository(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    


    // TODO: Create Zero base model, all model inherit form base model and all repository from thos class
}
