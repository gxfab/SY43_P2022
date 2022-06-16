package com.example.zeroday.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    // Database object
    // Database Version
    private static final int DATABASE_VERSION = 11;

    // Database Name
    private static final String DATABASE_NAME = "ZERO_DAY_DB";

    // Table Names
    public static final String TABLE_INCOME_CATEGORY = "INCOME_CATEGORY";
    public static final String TABLE_EXPENSE_CATEGORY = "EXPENSE_CATEGORY";
    public static final String TABLE_BUDGET = "BUDGET";
    public static final String TABLE_INCOME = "INCOME";
    public static final String TABLE_EXPENSE = "EXPENSE";
    public static final String TABLE_PREVISION = "PREVISION";
    public static final String TABLE_PREVISION_EXPENSE = "PREVISION_EXPENSE";


    //INCOMES_CATEGORY Table - column names
    public static final String KEY_ID_INCOME_CATEGORY = "ID";
    public static final String KEY_CODE_INCOME_CATEGORY = "CODE_INCOME_CATEGORY";
    public static final String KEY_LABEL_INCOME_CATEGORY = "NAME_INCOME_CATEGORY";

    //EXPENSE_CATEGORY Table - column names
    public static final String KEY_ID_EXPENSE_CATEGORY = "ID";
    public static final String KEY_CODE_EXPENSE_CATEGORY = "CODE_EXPENSE_CATEGORY";
    public static final String KEY_LABEL_EXPENSE_CATEGORY = "LABEL_EXPENSE_CATEGORY";

    //BUDGET Table - column names
    public static final String KEY_ID_BUDGET = "ID";
    public static final String KEY_CODE_BUDGET = "CODE_BUDGET";
    public static final String KEY_START_DATE_BUDGET = "START_DATE_BUDGET";
    public static final String KEY_END_DATE_BUDGET = "END_DATE_BUDGET";
    public static final String KEY_FREQUENCY_BUDGET = "FREQUENCY_BUDGET";

    //INCOME Table - column names
    public static final String KEY_ID_INCOME = "ID";
    public static final String KEY_LABEL_INCOME = "LABEL_INCOME";
    public static final String KEY_AMOUNT_INCOME = "AMOUNT_INCOME";
    public static final String KEY_FREQUENCY_INCOME = "FREQUENCY_INCOME";
    public static final String KEY_FK_ID_INCOME_CATEGORY = "ID_INCOME_CATEGORY";

    //EXPENSE Table - column names
    public static final String KEY_ID_EXPENSE = "ID";
    public static final String KEY_LABEL_EXPENSE = "LABEL_EXPENSE";
    public static final String KEY_AMOUNT_EXPENSE = "AMOUNT_EXPENSE";
    public static final String KEY_FREQUENCY_EXPENSE = "FREQUENCY_EXPENSE";
    public static final String KEY_FK_ID_EXPENSE_CATEGORY = "ID_EXPENSE_CATEGORY";
    public static final String KEY_DATE_EXPENSE = "DATE_EXPENSE";
    public static final String KEY_COMMENT_EXPENSE = "COMMENT_EXPENSE";

    //PREVISION Table - column names
    public static final String KEY_ID_PREVISION = "ID";
    public static final String KEY_FK_ID_BUDGET_PREVISION = "ID_BUDGET_PREVISION";
    public static final String KEY_AMOUNT_PREVISION = "AMOUNT_PREVISION";
    public static final String KEY_DATE_PREVISION = "DATE_PREVISION";
    public static final String KEY_TIMESTAP_PREVISION_START = "TIMESTAP_PREVISION_START";
    public static final String KEY_TIMESTAP_PREVISION_END = "TIMESTAP_PREVISION_END";


    //PREVISION_EXPENSE Table - column names
    public static final String KEY_ID_PREVISION_EXPENSE = "ID";
    public static final String KEY_LABEL_PREVISION_EXPENSE = "LABEL_PREVISION_EXPENSE";
    public static final String KEY_AMOUNT_PREVISION_EXPENSE = "AMOUNT_PREVISION_EXPENSE";
    public static final String KEY_ID_PREVISION_EXPENSE_CATEGORY = "ID_PREVISION_EXPENSE_CATEGORY";
    public static final String KEY_COMMENT_PREVISION_EXPENSE = "COMMENT_PREVISION_EXPENSE";

    //Table column indexes
    public static final int COL_ID_INCOME_CATEGORY_INDEX = 0;
    public static final int COL_CODE_INCOME_CATEGORY_INDEX = 1;
    public static final int COL_LABEL_INCOME_CATEGORY_INDEX = 2;

    public static final int COL_ID_EXPENSE_CATEGORY_INDEX = 0;
    public static final int COL_CODE_EXPENSE_CATEGORY_INDEX = 1;
    public static final int COL_LABEL_EXPENSE_CATEGORY_INDEX = 2;

    public static final int COL_ID_BUDGET_INDEX = 0;
    public static final int COL_CODE_BUDGET_INDEX = 1;
    public static final int COL_START_DATE_BUDGET_INDEX = 2;
    public static final int COL_END_DATE_BUDGET_INDEX = 3;
    public static final int COL_FREQUENCY_BUDGET_INDEX = 4;

    public static final int COL_ID_INCOME_INDEX = 0;
    public static final int COL_LABEL_INCOME_INDEX = 1;
    public static final int COL_AMOUNT_INCOME_INDEX = 2;
    public static final int COL_FREQUENCY_INCOME_INDEX = 3;
    public static final int COL_FK_ID_INCOME_CATEGORY_INDEX = 4;

    public static final int COL_ID_EXPENSE_INDEX = 0;
    public static final int COL_LABEL_EXPENSE_INDEX = 1;
    public static final int COL_AMOUNT_EXPENSE_INDEX = 2;
    public static final int COL_FREQUENCY_EXPENSE_INDEX = 3;
    public static final int COL_FK_ID_EXPENSE_CATEGORY_INDEX = 4;
    public static final int COL_DATE_EXPENSE_INDEX = 5;
    public static final int COL_COMMENT_EXPENSE_INDEX = 6;

    public static final int COL_ID_PREVISION_INDEX = 0;
    public static final int COL_FK_ID_BUDGET_PREVISION_INDEX = 1;
    public static final int COL_AMOUNT_PREVISION_INDEX = 2;
    public static final int COL_DATE_PREVISION_INDEX = 3;
    public static final int COL_TIMESTAP_PREVISION_START_INDEX = 4;
    public static final int COL_TIMESTAP_PREVISION_END_INDEX = 5;

    public static final int COL_ID_PREVISION_EXPENSE_INDEX = 0;
    public static final int COL_LABEL_PREVISION_EXPENSE_INDEX = 1;
    public static final int COL_AMOUNT_PREVISION_EXPENSE_INDEX = 2;
    public static final int COL_FK_ID_PREVISION_EXPENSE_CATEGORY_INDEX = 3;
    public static final int COL_COMMENT_PREVISION_EXPENSE_INDEX = 4;



    // Table Create Statements
    // INCOMES_CATEGORY table create statement
    private static final String CREATE_TABLE_INCOME_CATEGORY = "CREATE TABLE "
            + TABLE_INCOME_CATEGORY + "(" + KEY_ID_INCOME_CATEGORY + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CODE_INCOME_CATEGORY
            + " TEXT," + KEY_LABEL_INCOME_CATEGORY + " TEXT" + ")";

    // EXPENSE_CATEGORY table create statement
    private static final String CREATE_TABLE_EXPENSE_CATEGORY = "CREATE TABLE "
            + TABLE_EXPENSE_CATEGORY + "(" + KEY_ID_EXPENSE_CATEGORY + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CODE_EXPENSE_CATEGORY
            + " TEXT," + KEY_LABEL_EXPENSE_CATEGORY + " TEXT" + ")";

    // BUDGET table create statement
    private static final String CREATE_TABLE_BUDGET = "CREATE TABLE "
            + TABLE_BUDGET + "(" + KEY_ID_BUDGET + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CODE_BUDGET
            + " TEXT," + KEY_START_DATE_BUDGET + " TEXT," + KEY_END_DATE_BUDGET + " TEXT," + KEY_FREQUENCY_BUDGET + " TEXT" + ")";
    
    // INCOME table create statement
    private static final String CREATE_TABLE_INCOME = "CREATE TABLE "
            + TABLE_INCOME + "(" + KEY_ID_INCOME + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_LABEL_INCOME
            + " TEXT," + KEY_AMOUNT_INCOME + " TEXT," + KEY_FREQUENCY_INCOME + " TEXT," + KEY_FK_ID_INCOME_CATEGORY + " INTEGER" + ")";

    // EXPENSE table create statement
    private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE "
            + TABLE_EXPENSE + "(" + KEY_ID_EXPENSE + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_LABEL_EXPENSE
            + " TEXT," + KEY_AMOUNT_EXPENSE + " TEXT," + KEY_FREQUENCY_EXPENSE + " TEXT," + KEY_FK_ID_EXPENSE_CATEGORY + " INTEGER," + KEY_DATE_EXPENSE + " TEXT," + KEY_COMMENT_EXPENSE + " TEXT" + ")";

    // PREVISION table create statement
    private static final String CREATE_TABLE_PREVISION = "CREATE TABLE "
            + TABLE_PREVISION + "(" + KEY_ID_PREVISION + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FK_ID_BUDGET_PREVISION + " INTEGER," + KEY_AMOUNT_PREVISION + " TEXT," + KEY_DATE_PREVISION + " TEXT" + ")";

    // PREVISION_EXPENSE table create statement
    private static final String CREATE_TABLE_PREVISION_EXPENSE = "CREATE TABLE "
            + TABLE_PREVISION_EXPENSE + "(" + KEY_ID_PREVISION_EXPENSE + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_LABEL_PREVISION_EXPENSE
            + " TEXT," + KEY_AMOUNT_PREVISION_EXPENSE + " TEXT," + KEY_ID_PREVISION_EXPENSE_CATEGORY + " INTEGER," + KEY_COMMENT_PREVISION_EXPENSE + " TEXT" + ")";

    //Drop table statement
    //Drop incomes category table
    private static final String DROP_TABLE_INCOME_CATEGORY = "DROP TABLE IF EXISTS " + TABLE_INCOME_CATEGORY;

    //Drop expense category table
    private static final String DROP_TABLE_EXPENSE_CATEGORY = "DROP TABLE IF EXISTS " + TABLE_EXPENSE_CATEGORY;

    //Drop budget table
    private static final String DROP_TABLE_BUDGET = "DROP TABLE IF EXISTS " + TABLE_BUDGET;

    //Drop income table
    private static final String DROP_TABLE_INCOME = "DROP TABLE IF EXISTS " + TABLE_INCOME;

    //Drop expense table
    private static final String DROP_TABLE_EXPENSE = "DROP TABLE IF EXISTS " + TABLE_EXPENSE;

    //Drop prevision table
    private static final String DROP_TABLE_PREVISION = "DROP TABLE IF EXISTS " + TABLE_PREVISION;

    //Drop prevision expense table
    private static final String DROP_TABLE_PREVISION_EXPENSE = "DROP TABLE IF EXISTS " + TABLE_PREVISION_EXPENSE;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // creating required tables
        //IncomeCategory table create statement
        sqLiteDatabase.execSQL(CREATE_TABLE_INCOME_CATEGORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPENSE_CATEGORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_BUDGET);
        sqLiteDatabase.execSQL(CREATE_TABLE_INCOME);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPENSE);
        sqLiteDatabase.execSQL(CREATE_TABLE_PREVISION);
        sqLiteDatabase.execSQL(CREATE_TABLE_PREVISION_EXPENSE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //Drop table if exist
        sqLiteDatabase.execSQL(DROP_TABLE_INCOME);
        sqLiteDatabase.execSQL(DROP_TABLE_EXPENSE);
        sqLiteDatabase.execSQL(DROP_TABLE_PREVISION_EXPENSE);
        sqLiteDatabase.execSQL(DROP_TABLE_PREVISION);
        sqLiteDatabase.execSQL(DROP_TABLE_BUDGET);
        sqLiteDatabase.execSQL(DROP_TABLE_INCOME_CATEGORY);
        sqLiteDatabase.execSQL(DROP_TABLE_EXPENSE_CATEGORY);
        onCreate(sqLiteDatabase);
        
    }



    }
