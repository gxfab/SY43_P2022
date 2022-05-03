package fr.sy43.studzero.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

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

    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_CATEGORY + "("
            + CATEGORY_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + CATEGORY_COLUMN_THEORETICAL_AMOUNT + " NUMERIC,"
            + CATEGORY_COLUMN_REAL_AMOUNT + " NUMERIC,"
            + CATEGORY_COLUMN_TYPE + " INTEGER REFERENCES " + TABLE_CATEGORY_TYPE + ","
            + CATEGORY_COLUMN_BUDGET + " INTEGER REFERENCES "+ TABLE_BUDGET +")";

    private static final String CREATE_TABLE_CATEGORY_TYPE = "CREATE TABLE "
            + TABLE_CATEGORY_TYPE + "("
            + CATEGORY_TYPE_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + CATEGORY_TYPE_COLUMN_NAME + " TEXT)";

    private static final String CREATE_TABLE_PAYMENT = "CREATE TABLE "
            + TABLE_PAYMENT + "("
            + PAYMENT_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + PAYMENT_COLUMN_AMOUNT + " NUMERIC,"
            + PAYMENT_COLUMN_DATE_PAYMENT + " DATE,"
            + PAYMENT_COLUMN_CATEGORY + " INT REFERENCES "+ TABLE_CATEGORY +")";

    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "("
            + USER_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + USER_COLUMN_DATE_NEXT_BUDGET + " NUMERIC,"
            + USER_COLUMN_CURRENT_BUDGET + " INTEGER REFERENCES "+ TABLE_BUDGET +")";


    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BUDGET);
        db.execSQL(CREATE_TABLE_CATEGORY_TYPE);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_PAYMENT);
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE+TABLE_PAYMENT);
        db.execSQL(DROP_TABLE+TABLE_CATEGORY);
        db.execSQL(DROP_TABLE+TABLE_USER);
        db.execSQL(DROP_TABLE+TABLE_CATEGORY_TYPE);
        db.execSQL(DROP_TABLE+TABLE_BUDGET);

        onCreate(db);
    }

    public boolean addBudget(Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BUDGET_COLUMN_DATE_START, budget.getDateStart().getTime());
        cv.put(BUDGET_COLUMN_DATE_END, budget.getDateEnd().getTime());
        cv.put(BUDGET_COLUMN_BUDGET_AMOUNT, budget.getBudgetAmount());
        long id = db.insert(TABLE_BUDGET, null, cv);

        if(id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CATEGORY_COLUMN_THEORETICAL_AMOUNT, category.getTheoreticalAmount());
        cv.put(CATEGORY_COLUMN_REAL_AMOUNT, category.getRealAmount());
        cv.put(CATEGORY_COLUMN_TYPE, category.getType());
        cv.put(CATEGORY_COLUMN_BUDGET, category.getBudget());
        long id = db.insert(TABLE_CATEGORY, null, cv);

        if(id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addCategoryType(CategoryType categoryType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CATEGORY_TYPE_COLUMN_NAME, categoryType.getNameCategory());

        long id = db.insert(TABLE_CATEGORY_TYPE, null, cv);

        if(id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addPayment(Payment payment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PAYMENT_COLUMN_AMOUNT, payment.getAmount());
        cv.put(PAYMENT_COLUMN_DATE_PAYMENT, payment.getDatePayment().getTime());
        cv.put(PAYMENT_COLUMN_CATEGORY, payment.getCategory());
        long id = db.insert(TABLE_PAYMENT, null, cv);

        if(id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_COLUMN_DATE_NEXT_BUDGET, user.getDateNextBudget().getTime());
        cv.put(USER_COLUMN_CURRENT_BUDGET, user.getCurrentBudget());
        long id = db.insert(TABLE_BUDGET, null, cv);

        if(id == -1) {
            return false;
        } else {
            return true;
        }
    }
}
