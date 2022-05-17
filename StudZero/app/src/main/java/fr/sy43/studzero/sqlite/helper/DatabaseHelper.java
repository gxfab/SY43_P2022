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

/**
 * This class is used to access the database of the app
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * Database name
     */

    private static final String DATABASE_NAME = "studzero.db";

    /**
     * Database version
     */
    private static final int DATABASE_VERSION = 1;

    // Table names
    /**
     * Name of the budget table
     */
    private static final String TABLE_BUDGET = "budget";
    /**
     * Name of the category table
     */
    private static final String TABLE_CATEGORY = "category";
    /**
     * Name of the category type table
     */
    private static final String TABLE_CATEGORY_TYPE = "category_type";
    /**
     * Name of the payment table
     */
    private static final String TABLE_PAYMENT = "payment";
    /**
     * Name of the user table
     */
    private static final String TABLE_USER = "user";


    // -------------------- Columns of tables -------------------
    // Columns of the budget table
    /**
     * Name of the id column of the budget table
     */
    private static final String BUDGET_COLUMN_ID = "id_budget";
    /**
     * Name of the start date column of the budget table
     */
    private static final String BUDGET_COLUMN_DATE_START = "date_start";
    /**
     * Name of the end date column of the budget table
     */
    private static final String BUDGET_COLUMN_DATE_END = "date_end";
    /**
     * Name of the id column of the budget table
     */
    private static final String BUDGET_COLUMN_BUDGET_AMOUNT = "budget_amount";


    // Columns of the category table
    /**
     * Name of the id column of the category table
     */
    private static final String CATEGORY_COLUMN_ID = "id_category";
    /**
     * Name of the theoretical amount column of the category table
     */
    private static final String CATEGORY_COLUMN_THEORETICAL_AMOUNT = "theoretical_amount";
    /**
     * Name of the real amount column of the category table
     */
    private static final String CATEGORY_COLUMN_REAL_AMOUNT = "real_amount";
    /**
     * Name of the type column of the category table
     */
    private static final String CATEGORY_COLUMN_TYPE = "type";
    /**
     * Name of the budget column of the category table
     */
    private static final String CATEGORY_COLUMN_BUDGET = "budget";


    // Columns of the category type table
    /**
     * Name of the id column of the category type table
     */
    private static final String CATEGORY_TYPE_COLUMN_ID = "id_category_type";
    /**
     * Name of the name column of the category type table
     */
    private static final String CATEGORY_TYPE_COLUMN_NAME = "name_category";


    // Columns of the payment table
    /**
     * Name of the id column of the payment table
     */
    private static final String PAYMENT_COLUMN_ID = "id_payment";
    /**
     * Name of the amount column of the payment table
     */
    private static final String PAYMENT_COLUMN_AMOUNT = "amount";
    /**
     * Name of the payment date of the payment table
     */
    private static final String PAYMENT_COLUMN_DATE_PAYMENT = "date_payment";
    /**
     * Name of the category of the payment table
     */
    private static final String PAYMENT_COLUMN_CATEGORY = "category";


    // Columns of the user table
    /**
     * Name of the id column of the user table
     */
    private static final String USER_COLUMN_ID = "id_user";
    /**
     * Name of the next budget date column of the user table
     */
    private static final String USER_COLUMN_DATE_NEXT_BUDGET = "date_next_budget";
    /**
     * Name of the current budget column of the user table
     */
    private static final String USER_COLUMN_CURRENT_BUDGET = "current_budget";


    // ----------------- Create table statements -----------------
    /**
     * Budget create table statement
     */
    private static final String CREATE_TABLE_BUDGET = "CREATE TABLE "
            + TABLE_BUDGET + "("
            + BUDGET_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + BUDGET_COLUMN_DATE_START + " DATE,"
            + BUDGET_COLUMN_DATE_END + " DATE,"
            + BUDGET_COLUMN_BUDGET_AMOUNT + " NUMERIC)";

    /**
     * Category create table statement
     */
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_CATEGORY + "("
            + CATEGORY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + CATEGORY_COLUMN_THEORETICAL_AMOUNT + " NUMERIC,"
            + CATEGORY_COLUMN_REAL_AMOUNT + " NUMERIC,"
            + CATEGORY_COLUMN_TYPE + " INTEGER REFERENCES " + TABLE_CATEGORY_TYPE + ","
            + CATEGORY_COLUMN_BUDGET + " INTEGER REFERENCES "+ TABLE_BUDGET +")";

    /**
     * Category type create table statement
     */
    private static final String CREATE_TABLE_CATEGORY_TYPE = "CREATE TABLE "
            + TABLE_CATEGORY_TYPE + "("
            + CATEGORY_TYPE_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + CATEGORY_TYPE_COLUMN_NAME + " TEXT)";

    /**
     * Payment create table statement
     */
    private static final String CREATE_TABLE_PAYMENT = "CREATE TABLE "
            + TABLE_PAYMENT + "("
            + PAYMENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + PAYMENT_COLUMN_AMOUNT + " NUMERIC,"
            + PAYMENT_COLUMN_DATE_PAYMENT + " DATE,"
            + PAYMENT_COLUMN_CATEGORY + " INT REFERENCES "+ TABLE_CATEGORY +")";

    /**
     * User create table statement
     */
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "("
            + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + USER_COLUMN_DATE_NEXT_BUDGET + " NUMERIC,"
            + USER_COLUMN_CURRENT_BUDGET + " INTEGER REFERENCES "+ TABLE_BUDGET +")";


    /**
     * Drop table statement
     */
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the database.
     * Called when the database file hasn't been created yet
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BUDGET);
        db.execSQL(CREATE_TABLE_CATEGORY_TYPE);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_PAYMENT);
        db.execSQL(CREATE_TABLE_USER);

        ContentValues cv = new ContentValues();
        cv.put(BUDGET_COLUMN_ID, 1);
        cv.put(BUDGET_COLUMN_DATE_END, 1);
        cv.put(BUDGET_COLUMN_BUDGET_AMOUNT, 2500);
        db.insert(TABLE_BUDGET, null, cv);

        ContentValues cv2 = new ContentValues();
        cv2.put(CATEGORY_TYPE_COLUMN_ID, 1);
        cv2.put(CATEGORY_TYPE_COLUMN_NAME, "Loyer");
        db.insert(TABLE_CATEGORY_TYPE, null, cv2);

        ContentValues cv8 = new ContentValues();
        cv8.put(CATEGORY_TYPE_COLUMN_ID, 2);
        cv8.put(CATEGORY_TYPE_COLUMN_NAME, "Courses");
        db.insert(TABLE_CATEGORY_TYPE, null, cv8);

        ContentValues cv3 = new ContentValues();
        cv3.put(CATEGORY_COLUMN_TYPE, 1);
        cv3.put(CATEGORY_COLUMN_THEORETICAL_AMOUNT, 800);
        cv3.put(CATEGORY_COLUMN_REAL_AMOUNT, 600);
        cv3.put(CATEGORY_COLUMN_BUDGET, 1);
        db.insert(TABLE_CATEGORY, null, cv3);

        ContentValues cv9 = new ContentValues();
        cv9.put(CATEGORY_COLUMN_TYPE, 2);
        cv9.put(CATEGORY_COLUMN_THEORETICAL_AMOUNT, 500);
        cv9.put(CATEGORY_COLUMN_REAL_AMOUNT, 300);
        cv9.put(CATEGORY_COLUMN_BUDGET, 1);
        db.insert(TABLE_CATEGORY, null, cv9);

        ContentValues cv4 = new ContentValues();
        cv4.put(USER_COLUMN_ID, 1);
        cv4.put(USER_COLUMN_DATE_NEXT_BUDGET, 300000000);
        cv4.put(USER_COLUMN_CURRENT_BUDGET, 1);
        db.insert(TABLE_USER, null, cv4);

        ContentValues cv5 = new ContentValues();
        cv5.put(PAYMENT_COLUMN_CATEGORY, 1);
        cv5.put(PAYMENT_COLUMN_AMOUNT, 50.0f);
        cv5.put(PAYMENT_COLUMN_DATE_PAYMENT, 2);
        db.insert(TABLE_PAYMENT, null, cv5);

        ContentValues cv6 = new ContentValues();
        cv6.put(PAYMENT_COLUMN_CATEGORY, 1);
        cv6.put(PAYMENT_COLUMN_AMOUNT, 123.45f);
        cv6.put(PAYMENT_COLUMN_DATE_PAYMENT, 3);
        db.insert(TABLE_PAYMENT, null, cv6);

        ContentValues cv7 = new ContentValues();
        cv7.put(PAYMENT_COLUMN_CATEGORY, 1);
        cv7.put(PAYMENT_COLUMN_AMOUNT, 123.45f);
        cv7.put(PAYMENT_COLUMN_DATE_PAYMENT, 1);
        db.insert(TABLE_PAYMENT, null, cv7);
    }

    // Called when the database is upgraded

    /**
     * Updates the data base.
     * Called when the data base is upgraded.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
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

    /**
     * Add a budget to the database
     * @param budget
     * @return true if the budget has successfully been added to the database, otherwise false
     */
    public boolean addBudget(Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BUDGET_COLUMN_DATE_START, budget.getDateStart().getTime());
        cv.put(BUDGET_COLUMN_DATE_END, budget.getDateEnd().getTime());
        cv.put(BUDGET_COLUMN_BUDGET_AMOUNT, budget.getBudgetAmount());
        long id = db.insert(TABLE_BUDGET, null, cv);

        return id != -1;
    }

    /**
     * Add a category to the database
     * @param category
     * @return true if the category has successfully been added to the database, otherwise false
     */
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

    /**
     * Add a category type to the database
     * @param categoryType
     * @return true if the category type has successfully been added to the database, otherwise false
     */
    public boolean addCategoryType(CategoryType categoryType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CATEGORY_TYPE_COLUMN_NAME, categoryType.getNameCategory());

        long id = db.insert(TABLE_CATEGORY_TYPE, null, cv);

        return id != -1;
    }

    // Add a new payment to the payment table
    /**
     * Add a payment to the database
     * @param payment
     * @return true if the payment has successfully been added to the database, otherwise false
     */
    public boolean addPayment(Payment payment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PAYMENT_COLUMN_AMOUNT, payment.getAmount());
        cv.put(PAYMENT_COLUMN_DATE_PAYMENT, payment.getDatePayment().getTime());
        cv.put(PAYMENT_COLUMN_CATEGORY, payment.getCategory());
        long id = db.insert(TABLE_PAYMENT, null, cv);

        return id != -1;
    }

    /**
     * Add a category to the database
     * @param user
     * @return true if the user has successfully been added to the database, otherwise false
     */
    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_COLUMN_DATE_NEXT_BUDGET, user.getDateNextBudget().getTime());
        cv.put(USER_COLUMN_CURRENT_BUDGET, user.getCurrentBudget());
        long id = db.insert(TABLE_BUDGET, null, cv);

        return id != -1;
    }

    /**
     * get a budget from the database
     * @param idBudget id of the budget to get
     * @return the budget that corresponds to the give id, null is returned if no budget corresponds to the id
     */
    @SuppressLint("Range")
    public Budget getBudget(int idBudget) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_BUDGET + " where " + BUDGET_COLUMN_ID + "="+idBudget;

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

    /**
     * get a category from the database
     * @param idCategory id of the budget to get
     * @return the category that corresponds to the give id, null is returned if no category corresponds to the id
     */
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

    /**
     * get a category type from the database
     * @param idCategoryType id of the budget to get
     * @return the category type that corresponds to the give id, null is returned if no category type corresponds to the id
     */
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

    /**
     * get a payment from the database
     * @param idPayment id of the budget to get
     * @return the payment that corresponds to the give id, null is returned if no payment corresponds to the id
     */
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

    /**
     * get the user from the database
     * @return the user from the databse, null is returned if no users exist
     */
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

    /**
     * get all budgets from the database
     * @return all budgets from the database, null is returned if no budgets exist
     */
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
         do {
            budgets.add(new Budget(
                        c.getInt(c.getColumnIndex(BUDGET_COLUMN_ID)),
                        new Date(c.getInt(c.getColumnIndex(BUDGET_COLUMN_DATE_START))),
                        new Date(c.getInt(c.getColumnIndex(BUDGET_COLUMN_DATE_END))),
                        c.getFloat(c.getColumnIndex(BUDGET_COLUMN_BUDGET_AMOUNT))
                    ));
        }while(c.moveToNext());

        return budgets;
    }

    /**
     * get the current budget
     * @return the current budget, null is returned if no current budget exists
     */
    @SuppressLint("Range")
    public Budget getCurrentBudget() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = new StringBuilder().append("Select * from ").append(TABLE_BUDGET).append(" where ").
                append(BUDGET_COLUMN_ID).append(" in (Select ").append(USER_COLUMN_ID).append(" from ").
                append(TABLE_USER).append(" where ").append(USER_COLUMN_ID).append("=1)").toString();

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

    /**
     * get all categories that are linked to a budget
     * @param idBudget id of the budget
     * @return categories that are linked to the given budget, null is returned if no categories are linked to the id
     */
    @SuppressLint("Range")
    public List<Category> getAllCategories(int idBudget) {
        List<Category> categories = new LinkedList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_CATEGORY + " where " + CATEGORY_COLUMN_BUDGET + "=" + idBudget;

        Cursor c = db.rawQuery(query, null);

        if(c == null) {
            return null;
        }

        c.moveToFirst();
         do {
            categories.add(new Category(
                    c.getInt(c.getColumnIndex(CATEGORY_COLUMN_ID)),
                    c.getFloat(c.getColumnIndex(CATEGORY_COLUMN_THEORETICAL_AMOUNT)),
                    c.getFloat(c.getColumnIndex(CATEGORY_COLUMN_REAL_AMOUNT)),
                    c.getInt(c.getColumnIndex(CATEGORY_COLUMN_BUDGET)),
                    c.getInt(c.getColumnIndex(CATEGORY_COLUMN_TYPE))
            ));
        } while(c.moveToNext());

        return categories;
    }

    /**
     * get all payments that are linked to a budget
     * @param idBudget id of the budget
     * @return payments that are linked to the given budget, null is returned if no categories are linked to the id
     */
    @SuppressLint("Range")
    public List<Payment> getAllPaymentsOfBudget(int idBudget) {
        List<Payment> payments = new LinkedList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_PAYMENT +" INNER JOIN " + TABLE_CATEGORY  +
                " ON "+ PAYMENT_COLUMN_CATEGORY+ "=" + CATEGORY_COLUMN_ID +
                " where " + CATEGORY_COLUMN_BUDGET + "=" + idBudget + " ORDER BY " + PAYMENT_COLUMN_DATE_PAYMENT + " DESC";

        Cursor c = db.rawQuery(query, null);

        if(c == null) {
            return null;
        }

        c.moveToFirst();
        do {
            payments.add(new Payment(
                    c.getInt(c.getColumnIndex(PAYMENT_COLUMN_ID)),
                    c.getFloat(c.getColumnIndex(PAYMENT_COLUMN_AMOUNT)),
                    new Date(c.getInt(c.getColumnIndex(PAYMENT_COLUMN_DATE_PAYMENT))),
                    c.getInt(c.getColumnIndex(PAYMENT_COLUMN_CATEGORY))
            ));
        } while(c.moveToNext());

        return payments;
    }

    /**
     * get all category types from the database
     * @return all category types from the database, null is returned if no category types exist
     */
    @SuppressLint("Range")
    public List<CategoryType> getAllCategoriesTypes() {
        List<CategoryType> categorieTypes = new LinkedList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_CATEGORY_TYPE;

        Cursor c = db.rawQuery(query, null);

        if(c == null) {
            return null;
        }

        c.moveToFirst();
        do {
            categorieTypes.add(new CategoryType(
                    c.getInt(c.getColumnIndex(CATEGORY_TYPE_COLUMN_ID)),
                    c.getString(c.getColumnIndex(CATEGORY_TYPE_COLUMN_NAME))
            ));
        } while(c.moveToNext());

        return categorieTypes;
    }

    /**
     * Get the category of the current budget of a category type
     * @param categoryTypeName name of the category type
     * @return category of the current budget that is linked to the given name of a category type, null is returned if no category of the current budget is linked to the name
     */
    @SuppressLint("Range")
    public Category getCurrentCategoryOfCategoryType(String categoryTypeName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int currentBudgetId = getCurrentBudget().getIdBudget();

        String query = "Select * from " + TABLE_CATEGORY +" INNER JOIN " + TABLE_CATEGORY_TYPE  +
                " ON "+ CATEGORY_COLUMN_TYPE+ "=" + CATEGORY_TYPE_COLUMN_ID +
                " where " + CATEGORY_TYPE_COLUMN_NAME + "=\"" + categoryTypeName + "\" AND " + CATEGORY_COLUMN_BUDGET + "=" + currentBudgetId;

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

    @SuppressLint("Range")
    public Float getRemainingAmountOfCurrentBudget() {
        SQLiteDatabase db = this.getReadableDatabase();
        Budget currentBudget = getCurrentBudget();
        String query = "Select sum(" + CATEGORY_COLUMN_REAL_AMOUNT + ") as sum from "+ TABLE_CATEGORY
                + " where " + CATEGORY_COLUMN_BUDGET + "=" + currentBudget.getIdBudget();

        Cursor c = db.rawQuery(query, null);

        if(c == null) {
            return null;
        }
        c.moveToFirst();
        return  currentBudget.getBudgetAmount() - c.getFloat(c.getColumnIndex("sum"));
    }

    public int getRemainingDaysOfCurrentBudget() {
        User user = getUser();
        Budget currentBudget = getCurrentBudget();
        int day1 = (int) Math.floor(user.getDateNextBudget().getTime() / 86400000);
        int day2 = (int) Math.floor(currentBudget.getDateEnd().getTime() / 86400000);
        return day1 - day2;
    }

    /**
     * Update the real amount of money spend of a category
     * @param idCategory id of the category to update
     * @param amount amount used for the update
     * @return true if the update was successful, otherwise false
     */
    public boolean updateCategoryRealAmount(int idCategory, float amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CATEGORY_COLUMN_REAL_AMOUNT, amount);
        int id = db.update(TABLE_CATEGORY, cv, ""+CATEGORY_COLUMN_ID+"=?", new String[]{""+idCategory});
        return id != -1;
    }

    /**
     * Update the date of the next budget
     * @param nextBudget date of the next budget
     * @return true if the update was successful, otherwise false
     */
    public boolean updateUserDateNextBudget(Date nextBudget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_COLUMN_DATE_NEXT_BUDGET, nextBudget.getTime());
        int id = db.update(TABLE_USER, cv, ""+USER_COLUMN_ID+"=?", new String[]{"1"});
        return id != -1;
    }

    /**
     * Update the id of the current budget
     * @param idBudget id of the budget
     * @return true if the update was successful, otherwise false
     */
    public boolean updateUserCurrentBudget(int idBudget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_COLUMN_CURRENT_BUDGET, idBudget);
        int id = db.update(TABLE_USER, cv, ""+USER_COLUMN_ID+"=?", new String[]{"1"});
        return id != -1;
    }



    /**
     * Closes the access to the database
     */
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
