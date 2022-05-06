package fr.sy43.studzero.sqlite.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import fr.sy43.studzero.sqlite.model.Budget;
import fr.sy43.studzero.sqlite.model.Category;
import fr.sy43.studzero.sqlite.model.CategoryType;
import fr.sy43.studzero.sqlite.model.Payment;
import fr.sy43.studzero.sqlite.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database name
    private static final String DATABASE_NAME = "studzero.db";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_BUDGET = "budget";
    private static final String TABLE_CATEGORY = "category";
    private static final String TABLE_CATEGORY_TYPE = "category_type";
    private static final String TABLE_PAYMENT = "payment";
    private static final String TABLE_USER = "user";

    // Columns of the budget table
    private static final String BUDGET_COLUMN_ID = "id_budget";
    private static final String BUDGET_COLUMN_DATE_START = "date_start";
    private static final String BUDGET_COLUMN_DATE_END = "date_end";
    private static final String BUDGET_COLUMN_BUDGET_AMOUNT = "budget_amount";

    // Columns of the category table
    private static final String CATEGORY_COLUMN_ID = "id_category";
    private static final String CATEGORY_COLUMN_THEORETICAL_AMOUNT = "theoretical_amount";
    private static final String CATEGORY_COLUMN_REAL_AMOUNT = "real_amount";
    private static final String CATEGORY_COLUMN_TYPE = "type";
    private static final String CATEGORY_COLUMN_BUDGET = "budget";

    // -------------------- Columns of tables -------------------
    // Columns of the category type table
    private static final String CATEGORY_TYPE_COLUMN_ID = "id_category_type";
    private static final String CATEGORY_TYPE_COLUMN_NAME = "name_category";

    // Columns of the payment table
    private static final String PAYMENT_COLUMN_ID = "id_payment";
    private static final String PAYMENT_COLUMN_AMOUNT = "amount";
    private static final String PAYMENT_COLUMN_DATE_PAYMENT = "date_payment";
    private static final String PAYMENT_COLUMN_CATEGORY = "category";

    // Columns of the user table
    private static final String USER_COLUMN_ID = "id_user";
    private static final String USER_COLUMN_DATE_NEXT_BUDGET = "date_next_budget";
    private static final String USER_COLUMN_CURRENT_BUDGET = "current_budget";

    // ----------------- Create table statements -----------------
    // Budget create table statement
    private static final String CREATE_TABLE_BUDGET = "CREATE TABLE "
            + TABLE_BUDGET + "("
            + BUDGET_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + BUDGET_COLUMN_DATE_START + " DATE,"
            + BUDGET_COLUMN_DATE_END + " DATE,"
            + BUDGET_COLUMN_BUDGET_AMOUNT + " NUMERIC)";

    // Category create table statement
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_CATEGORY + "("
            + CATEGORY_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + CATEGORY_COLUMN_THEORETICAL_AMOUNT + " NUMERIC,"
            + CATEGORY_COLUMN_REAL_AMOUNT + " NUMERIC,"
            + CATEGORY_COLUMN_TYPE + " INTEGER REFERENCES " + TABLE_CATEGORY_TYPE + ","
            + CATEGORY_COLUMN_BUDGET + " INTEGER REFERENCES "+ TABLE_BUDGET +")";

    // Category type create table statement
    private static final String CREATE_TABLE_CATEGORY_TYPE = "CREATE TABLE "
            + TABLE_CATEGORY_TYPE + "("
            + CATEGORY_TYPE_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + CATEGORY_TYPE_COLUMN_NAME + " TEXT)";

    // Payment create table statement
    private static final String CREATE_TABLE_PAYMENT = "CREATE TABLE "
            + TABLE_PAYMENT + "("
            + PAYMENT_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + PAYMENT_COLUMN_AMOUNT + " NUMERIC,"
            + PAYMENT_COLUMN_DATE_PAYMENT + " DATE,"
            + PAYMENT_COLUMN_CATEGORY + " INT REFERENCES "+ TABLE_CATEGORY +")";

    // User create table statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "("
            + USER_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + USER_COLUMN_DATE_NEXT_BUDGET + " NUMERIC,"
            + USER_COLUMN_CURRENT_BUDGET + " INTEGER REFERENCES "+ TABLE_BUDGET +")";


    // Drop table statement
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creates the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BUDGET);
        db.execSQL(CREATE_TABLE_CATEGORY_TYPE);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_PAYMENT);
        db.execSQL(CREATE_TABLE_USER);

        ContentValues cv = new ContentValues();
        cv.put(USER_COLUMN_ID, 1);
        db.insert(TABLE_USER, null, cv);
    }

    // Called when the database is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE+TABLE_PAYMENT);
        db.execSQL(DROP_TABLE+TABLE_CATEGORY);
        db.execSQL(DROP_TABLE+TABLE_USER);
        db.execSQL(DROP_TABLE+TABLE_CATEGORY_TYPE);
        db.execSQL(DROP_TABLE+TABLE_BUDGET);

        onCreate(db);
    }

    // Add a new budget to the budget table
    public boolean addBudget(Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BUDGET_COLUMN_DATE_START, budget.getDateStart().getTime());
        cv.put(BUDGET_COLUMN_DATE_END, budget.getDateEnd().getTime());
        cv.put(BUDGET_COLUMN_BUDGET_AMOUNT, budget.getBudgetAmount());
        long id = db.insert(TABLE_BUDGET, null, cv);

        return id != -1;
    }

    // Add a new category to the category table
    public boolean addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CATEGORY_COLUMN_THEORETICAL_AMOUNT, category.getTheoreticalAmount());
        cv.put(CATEGORY_COLUMN_REAL_AMOUNT, category.getRealAmount());
        cv.put(CATEGORY_COLUMN_TYPE, category.getType());
        cv.put(CATEGORY_COLUMN_BUDGET, category.getBudget());
        long id = db.insert(TABLE_CATEGORY, null, cv);

        return id != -1;
    }

    // Add a new category type to the category type table
    public boolean addCategoryType(CategoryType categoryType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CATEGORY_TYPE_COLUMN_NAME, categoryType.getNameCategory());

        long id = db.insert(TABLE_CATEGORY_TYPE, null, cv);

        return id != -1;
    }

    // Add a new payment to the payment table
    public boolean addPayment(Payment payment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PAYMENT_COLUMN_AMOUNT, payment.getAmount());
        cv.put(PAYMENT_COLUMN_DATE_PAYMENT, payment.getDatePayment().getTime());
        cv.put(PAYMENT_COLUMN_CATEGORY, payment.getCategory());
        long id = db.insert(TABLE_PAYMENT, null, cv);

        return id != -1;
    }

    // Add a new user to the user table
    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_COLUMN_DATE_NEXT_BUDGET, user.getDateNextBudget().getTime());
        cv.put(USER_COLUMN_CURRENT_BUDGET, user.getCurrentBudget());
        long id = db.insert(TABLE_BUDGET, null, cv);

        return id != -1;
    }

    // Get a budget from the budget table
    @SuppressLint("Range")
    public Budget getBudget(int idBudget) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_PAYMENT + " where " + PAYMENT_COLUMN_ID + "="+idBudget;

        Cursor c = db.rawQuery(query, null);

        if(c == null) {
            return null;
        }
        c.moveToFirst();

        return new Budget(
                c.getInt(c.getColumnIndex(BUDGET_COLUMN_ID)),
                new Date(c.getInt(c.getColumnIndex(BUDGET_COLUMN_DATE_START))),
                new Date(c.getInt(c.getColumnIndex(BUDGET_COLUMN_DATE_END))),
                c.getFloat(c.getColumnIndex(BUDGET_COLUMN_BUDGET_AMOUNT))
        );
    }

    // Get a category from the category table
    @SuppressLint("Range")
    public Category getCategory(int idCategory) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_CATEGORY + " where " + CATEGORY_COLUMN_ID + "="+idCategory;

        Cursor c = db.rawQuery(query, null);

        if(c == null) {
            return null;
        }
        c.moveToFirst();

        return new Category(
                c.getInt(c.getColumnIndex(CATEGORY_COLUMN_ID)),
                c.getFloat(c.getColumnIndex(CATEGORY_COLUMN_THEORETICAL_AMOUNT)),
                c.getFloat(c.getColumnIndex(CATEGORY_COLUMN_REAL_AMOUNT)),
                c.getInt(c.getColumnIndex(CATEGORY_COLUMN_BUDGET)),
                c.getInt(c.getColumnIndex(CATEGORY_COLUMN_TYPE))
        );
    }

    // Get a type category type from the category type table
    @SuppressLint("Range")
    public CategoryType getTypeCategory(int idCategoryType) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_CATEGORY_TYPE + " where " + CATEGORY_TYPE_COLUMN_ID + "="+idCategoryType;

        Cursor c = db.rawQuery(query, null);

        if(c == null) {
            return null;
        }
        c.moveToFirst();

        return new CategoryType(
                c.getInt(c.getColumnIndex(CATEGORY_TYPE_COLUMN_ID)),
                c.getString(c.getColumnIndex(CATEGORY_TYPE_COLUMN_NAME))
        );
    }

    // Get a payment from the budget payment
    @SuppressLint("Range")
    public Payment getPayment(int idPayment) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_PAYMENT + " where " + PAYMENT_COLUMN_ID + "="+idPayment;

        Cursor c = db.rawQuery(query, null);

        if(c == null) {
            return null;
        }
        c.moveToFirst();

        return new Payment(
                c.getInt(c.getColumnIndex(PAYMENT_COLUMN_ID)),
                c.getFloat(c.getColumnIndex(PAYMENT_COLUMN_AMOUNT)),
                new Date(c.getInt(c.getColumnIndex(PAYMENT_COLUMN_DATE_PAYMENT))),
                c.getInt(c.getColumnIndex(PAYMENT_COLUMN_CATEGORY))
        );
    }

    // Get the only user of the user table
    @SuppressLint("Range")
    public User getUser() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_USER + " where " + USER_COLUMN_ID + "=1";

        Cursor c = db.rawQuery(query, null);

        if(c == null) {
            return null;
        }
        c.moveToFirst();

        return new User(c.getInt(c.getColumnIndex(USER_COLUMN_ID)),
                new Date(c.getInt(c.getColumnIndex(USER_COLUMN_DATE_NEXT_BUDGET))),
                c.getInt(c.getColumnIndex(USER_COLUMN_CURRENT_BUDGET))
        );
    }

    // Get all budgets
    @SuppressLint("Range")
    public List<Budget> getAllBudgets() {
        List<Budget> budgets = new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_BUDGET;

        Cursor c = db.rawQuery(query, null);

        if(c == null) {
            return null;
        }

        c.moveToFirst();
        while(c.moveToNext()) {
            budgets.add(new Budget(
                        c.getInt(c.getColumnIndex(BUDGET_COLUMN_ID)),
                        new Date(c.getInt(c.getColumnIndex(BUDGET_COLUMN_DATE_START))),
                        new Date(c.getInt(c.getColumnIndex(BUDGET_COLUMN_DATE_END))),
                        c.getFloat(c.getColumnIndex(BUDGET_COLUMN_BUDGET_AMOUNT))
                    ));
        }

        return budgets;
    }

    // Close database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
