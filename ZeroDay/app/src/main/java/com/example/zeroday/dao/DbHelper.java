package com.example.zeroday.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    // Database object
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ZERO_DAY_DB";

    // Table Names
    public static final String TABLE_INCOMES_CATEGORY = "INCOMES_CATEGORY";

    //INCOMES_CATEGORY Table - column names
    public static final String KEY_ID_CATEGORY_TYPE = "ID_CATEGORY_TYPE";
    public static final String KEY_CODE_CATEGORY_TYPE = "CODE_CATEGORY_TYPE";
    public static final String KEY_LABEL_CATEGORY_TYPE = "LABEL_CATEGORY_TYPE";


    //Table column indexes
    public static final int COL_ID_CATEGORY_TYPE_INDEX = 0;
    public static final int COL_CODE_CATEGORY_TYPE_INDEX = 1;
    public static final int COL_LABEL_CATEGORY_TYPE_INDEX = 2;

    // Table Create Statements
    // INCOMES_CATEGORY table create statement
    private static final String CREATE_TABLE_INCOMES_CATEGORY = "CREATE TABLE "
            + TABLE_INCOMES_CATEGORY + "(" + KEY_ID_CATEGORY_TYPE + " TEXT PRIMARY KEY," + KEY_CODE_CATEGORY_TYPE
            + " TEXT," + KEY_LABEL_CATEGORY_TYPE + " TEXT" + ")";

    //Drop table statement
    //Drop incomes category table
    private static final String DROP_TABLE_INCOMES_CATEGORY = "DROP TABLE IF EXISTS " + TABLE_INCOMES_CATEGORY;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // creating required tables
        //IncomesCategory table create statement
        sqLiteDatabase.execSQL(CREATE_TABLE_INCOMES_CATEGORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //Drop table if exist
        sqLiteDatabase.execSQL(DROP_TABLE_INCOMES_CATEGORY);

        onCreate(sqLiteDatabase);
        
    }



    }
