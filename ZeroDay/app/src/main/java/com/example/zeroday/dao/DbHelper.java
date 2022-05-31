package com.example.zeroday.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    // Database object
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "ZERO_DAY_DB";

    // Table Names
    public static final String TABLE_INCOMES_CATEGORY = "INCOMES_CATEGORY";
    public static final String TABLE_EXPENSE_CATEGORY = "EXPENSE_CATEGORY";
    public static final String TABLE_BUDGET = "BUDGET";
    public static final String TABLE_INCOME = "INCOME";

    //INCOMES_CATEGORY Table - column names
    public static final String KEY_ID_CATEGORY_TYPE = "ID_CATEGORY_TYPE";
    public static final String KEY_CODE_CATEGORY_TYPE = "CODE_CATEGORY_TYPE";
    public static final String KEY_LABEL_CATEGORY_TYPE = "LABEL_CATEGORY_TYPE";

    //EXPENSE_CATEGORY Table - column names
    public static final String KEY_ID_EXPENSE_CATEGORY = "ID_EXPENSE_CATEGORY";
    public static final String KEY_CODE_EXPENSE_CATEGORY = "CODE_EXPENSE_CATEGORY";
    public static final String KEY_LABEL_EXPENSE_CATEGORY = "LABEL_EXPENSE_CATEGORY";

    //BUDGET Table - column names
    public static final String KEY_ID_BUDGET = "ID_BUDGET";
    public static final String KEY_CODE_BUDGET = "CODE_BUDGET";
    public static final String KEY_START_DATE_BUDGET = "START_DATE_BUDGET";
    public static final String KEY_END_DATE_BUDGET = "END_DATE_BUDGET";
    public static final String KEY_FREQUENCY_BUDGET = "FREQUENCY_BUDGET";

    //INCOME Table - column names
    public static final String KEY_ID_INCOME = "ID_INCOME";
    public static final String KEY_LABEL_INCOME = "LABEL_INCOME";
    public static final String KEY_AMOUNT_INCOME = "AMOUNT_INCOME";
    public static final String KEY_FREQUENCY_INCOME = "FREQUENCY_INCOME";
    public static final String KEY_ID_INCOME_CATEGORY = "ID_INCOME_CATEGORY";


    //Table column indexes
    public static final int COL_ID_CATEGORY_TYPE_INDEX = 0;
    public static final int COL_CODE_CATEGORY_TYPE_INDEX = 1;
    public static final int COL_LABEL_CATEGORY_TYPE_INDEX = 2;

    public static final int COL_ID_EXPENSE_CATEGORY_INDEX = 0;
    public static final int COL_CODE_EXPENSE_CATEGORY_INDEX = 1;
    public static final int COL_LABEL_EXPENSE_CATEGORY_INDEX = 2;

    public static final int COL_ID_BUDGET_INDEX = 0;
    public static final int COL_CODE_BUDGET_INDEX = 1;
    public static final int COL_START_DATE_BUDGET_INDEX = 2;
    public static final int COL_END_DATE_BUDGET_INDEX = 3;
    public static final int COL_FRQUENCE_BUDGET_INDEX = 4;

    public static final int COL_ID_INCOME_INDEX = 0;
    public static final int COL_LABEL_INCOME_INDEX = 1;
    public static final int COL_AMOUNT_INCOME_INDEX = 2;
    public static final int COL_FRQUENCE_INCOME_INDEX = 3;
    public static final int COL_ID_INCOME_CATEGORY_INDEX = 4;

    // Table Create Statements
    // INCOMES_CATEGORY table create statement
    private static final String CREATE_TABLE_INCOMES_CATEGORY = "CREATE TABLE "
            + TABLE_INCOMES_CATEGORY + "(" + KEY_ID_CATEGORY_TYPE + " INTEGER PRIMARY KEY," + KEY_CODE_CATEGORY_TYPE
            + " TEXT," + KEY_LABEL_CATEGORY_TYPE + " TEXT" + ")";

    // EXPENSE_CATEGORY table create statement
    private static final String CREATE_TABLE_EXPENSE_CATEGORY = "CREATE TABLE "
            + TABLE_EXPENSE_CATEGORY + "(" + KEY_ID_EXPENSE_CATEGORY + " INTEGER PRIMARY KEY," + KEY_CODE_EXPENSE_CATEGORY
            + " TEXT," + KEY_LABEL_EXPENSE_CATEGORY + " TEXT" + ")";

    // BUDGET table create statement
    private static final String CREATE_TABLE_BUDGET = "CREATE TABLE "
            + TABLE_BUDGET + "(" + KEY_ID_BUDGET + " INTEGER PRIMARY KEY," + KEY_CODE_BUDGET
            + " TEXT," + KEY_START_DATE_BUDGET + " TEXT," + KEY_END_DATE_BUDGET + " TEXT," + KEY_FREQUENCY_BUDGET + " TEXT" + ")";
    
    // INCOME table create statement
    private static final String CREATE_TABLE_INCOME = "CREATE TABLE "
            + TABLE_INCOME + "(" + KEY_ID_INCOME + " INTEGER PRIMARY KEY," + KEY_LABEL_INCOME
            + " TEXT," + KEY_AMOUNT_INCOME + " TEXT," + KEY_FREQUENCY_INCOME + " TEXT," + KEY_ID_INCOME_CATEGORY + " INTEGER" + ")";

    //Drop table statement
    //Drop incomes category table
    private static final String DROP_TABLE_INCOMES_CATEGORY = "DROP TABLE IF EXISTS " + TABLE_INCOMES_CATEGORY;

    //Drop expense category table
    private static final String DROP_TABLE_EXPENSE_CATEGORY = "DROP TABLE IF EXISTS " + TABLE_EXPENSE_CATEGORY;

    //Drop budget table
    private static final String DROP_TABLE_BUDGET = "DROP TABLE IF EXISTS " + TABLE_BUDGET;

    //Drop income table
    private static final String DROP_TABLE_INCOME = "DROP TABLE IF EXISTS " + TABLE_INCOME;

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
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPENSE_CATEGORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_BUDGET);
        sqLiteDatabase.execSQL(CREATE_TABLE_INCOME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //Drop table if exist
        sqLiteDatabase.execSQL(DROP_TABLE_INCOMES_CATEGORY);
        sqLiteDatabase.execSQL(DROP_TABLE_EXPENSE_CATEGORY);
        sqLiteDatabase.execSQL(DROP_TABLE_BUDGET);
        sqLiteDatabase.execSQL(DROP_TABLE_INCOME);
        onCreate(sqLiteDatabase);
        
    }



    }
