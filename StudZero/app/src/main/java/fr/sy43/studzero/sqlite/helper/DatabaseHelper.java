package fr.sy43.studzero.sqlite.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

    /**
     * Constructor of the class
     * @param context
     */
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

        // Insert a user in the database
        ContentValues cv = new ContentValues();
        cv.put(USER_COLUMN_ID, 1);
        db.insert(TABLE_USER, null, cv);

        // Insert a default category type
        ContentValues cv2 = new ContentValues();
        cv2.put(CATEGORY_TYPE_COLUMN_ID, 1);
        cv2.put(CATEGORY_TYPE_COLUMN_NAME, "Rent");
        db.insert(TABLE_CATEGORY_TYPE, null, cv2);

    }

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

    /**
     * Add a budget to the database
     * @param budget
     * @return id of new budget
     */
    public long addBudget(Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BUDGET_COLUMN_DATE_START, getStringFromDate(budget.getDateStart()));
        cv.put(BUDGET_COLUMN_DATE_END, getStringFromDate(budget.getDateEnd()));
        cv.put(BUDGET_COLUMN_BUDGET_AMOUNT, budget.getBudgetAmount());
        long id = db.insert(TABLE_BUDGET, null, cv);

        return id;
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
     * @return id of new category typz
     */
    public int addCategoryType(CategoryType categoryType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CATEGORY_TYPE_COLUMN_NAME, categoryType.getNameCategory());

        return (int) db.insert(TABLE_CATEGORY_TYPE, null, cv);
    }

    /**
     * Add a payment to the database
     * @param payment
     * @return true if the payment has successfully been added to the database, otherwise false
     */
    public boolean addPayment(Payment payment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PAYMENT_COLUMN_AMOUNT, payment.getAmount());
        cv.put(PAYMENT_COLUMN_DATE_PAYMENT, getStringFromDate(payment.getDatePayment()));
        cv.put(PAYMENT_COLUMN_CATEGORY, payment.getCategory());
        long id = db.insert(TABLE_PAYMENT, null, cv);
        updateCategoryRealAmount(payment.getCategory(), payment.getAmount());

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

        cv.put(USER_COLUMN_DATE_NEXT_BUDGET, getStringFromDate(user.getDateNextBudget()));
        cv.put(USER_COLUMN_CURRENT_BUDGET, user.getCurrentBudget());
        long id = db.insert(TABLE_BUDGET, null, cv);

        return id != -1;
    }

    /**
     * get a budget from the database
     * @param idBudget id of the budget to get
     * @return the budget that corresponds to the given id, null is returned if no budget corresponds to the id
     */
    @SuppressLint("Range")
    public Budget getBudget(int idBudget) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_BUDGET + " where " + BUDGET_COLUMN_ID + "="+idBudget;

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return null;
        }

        return new Budget(
                c.getInt(c.getColumnIndex(BUDGET_COLUMN_ID)),
                getDateFromString(c.getString(c.getColumnIndex(BUDGET_COLUMN_DATE_START))),
                getDateFromString(c.getString(c.getColumnIndex(BUDGET_COLUMN_DATE_END))),
                c.getFloat(c.getColumnIndex(BUDGET_COLUMN_BUDGET_AMOUNT))
        );
    }

    /**
     * get a category from the database
     * @param idCategory id of the budget to get
     * @return the category that corresponds to the given id, null is returned if no category corresponds to the id
     */
    @SuppressLint("Range")
    public Category getCategory(int idCategory) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_CATEGORY + " where " + CATEGORY_COLUMN_ID + "="+idCategory;

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return null;
        }

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
     * @param idCategoryType id of the category type to get
     * @return the category type that corresponds to the given id, null is returned if no category type corresponds to the id
     */
    @SuppressLint("Range")
    public CategoryType getTypeCategory(int idCategoryType) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_CATEGORY_TYPE + " where " + CATEGORY_TYPE_COLUMN_ID + "="+idCategoryType;

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return null;
        }

        return new CategoryType(
                c.getInt(c.getColumnIndex(CATEGORY_TYPE_COLUMN_ID)),
                c.getString(c.getColumnIndex(CATEGORY_TYPE_COLUMN_NAME))
        );
    }

    /**
     * get a category type from the database
     * @param nameCategoryType name of the category type to get
     * @return the category type that corresponds to the given name, null is returned if no category type corresponds to the id
     */
    @SuppressLint("Range")
    public CategoryType getTypeCategory(String nameCategoryType) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_CATEGORY_TYPE + " where " + CATEGORY_TYPE_COLUMN_NAME + "=\""+nameCategoryType + "\"";

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return null;
        }

        return new CategoryType(
                c.getInt(c.getColumnIndex(CATEGORY_TYPE_COLUMN_ID)),
                c.getString(c.getColumnIndex(CATEGORY_TYPE_COLUMN_NAME))
        );
    }

    /**
     * get a payment from the database
     * @param idPayment id of the budget to get
     * @return the payment that corresponds to the given id, null is returned if no payment corresponds to the id
     */
    @SuppressLint("Range")
    public Payment getPayment(int idPayment) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_PAYMENT + " where " + PAYMENT_COLUMN_ID + "="+idPayment;

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return null;
        }

        return new Payment(
                c.getInt(c.getColumnIndex(PAYMENT_COLUMN_ID)),
                c.getFloat(c.getColumnIndex(PAYMENT_COLUMN_AMOUNT)),
                getDateFromString(c.getString(c.getColumnIndex(PAYMENT_COLUMN_DATE_PAYMENT))),
                c.getInt(c.getColumnIndex(PAYMENT_COLUMN_CATEGORY))
        );
    }

    /**
     * get the user from the database
     * @return the user from the database, null is returned if no users exist
     */
    @SuppressLint("Range")
    public User getUser() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_USER + " where " + USER_COLUMN_ID + "=1";

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return null;
        }

        return new User(c.getInt(c.getColumnIndex(USER_COLUMN_ID)),
                getDateFromString(c.getString(c.getColumnIndex(USER_COLUMN_DATE_NEXT_BUDGET))),
                c.getInt(c.getColumnIndex(USER_COLUMN_CURRENT_BUDGET))
        );
    }

    /**
     * get all budgets from the database by reverse id order
     * @return all budgets from the database, null is returned if no budgets exist
     */
    @SuppressLint("Range")
    public List<Budget> getAllBudgets() {
        List<Budget> budgets = new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_BUDGET + " order by " + BUDGET_COLUMN_ID + " desc";

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return new LinkedList<Budget>();
        }

         do {
            budgets.add(new Budget(
                        c.getInt(c.getColumnIndex(BUDGET_COLUMN_ID)),
                        getDateFromString(c.getString(c.getColumnIndex(BUDGET_COLUMN_DATE_START))),
                        getDateFromString(c.getString(c.getColumnIndex(BUDGET_COLUMN_DATE_END))),
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
                append(BUDGET_COLUMN_ID).append(" in (Select ").append(USER_COLUMN_CURRENT_BUDGET).append(" from ").
                append(TABLE_USER).append(" where ").append(USER_COLUMN_ID).append("=1)").toString();

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return null;
        }

        return new Budget(
                c.getInt(c.getColumnIndex(BUDGET_COLUMN_ID)),
                getDateFromString(c.getString(c.getColumnIndex(BUDGET_COLUMN_DATE_START))),
                getDateFromString(c.getString(c.getColumnIndex(BUDGET_COLUMN_DATE_END))),
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

        if(c == null || !c.moveToFirst()) {
            return new LinkedList<Category>();
        }

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
     * @return payments that are linked to the given budget, null is returned if no payments are linked to the id
     */
    @SuppressLint("Range")
    public List<Payment> getAllPaymentsOfBudget(int idBudget) {
        List<Payment> payments = new LinkedList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_PAYMENT +" INNER JOIN " + TABLE_CATEGORY  +
                " ON "+ PAYMENT_COLUMN_CATEGORY+ "=" + CATEGORY_COLUMN_ID +
                " where " + CATEGORY_COLUMN_BUDGET + "=" + idBudget + " ORDER BY " + PAYMENT_COLUMN_DATE_PAYMENT + " DESC";

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return new LinkedList<Payment>();
        }

        do {
            payments.add(new Payment(
                    c.getInt(c.getColumnIndex(PAYMENT_COLUMN_ID)),
                    c.getFloat(c.getColumnIndex(PAYMENT_COLUMN_AMOUNT)),
                    getDateFromString(c.getString(c.getColumnIndex(PAYMENT_COLUMN_DATE_PAYMENT))),
                    c.getInt(c.getColumnIndex(PAYMENT_COLUMN_CATEGORY))
            ));
        } while(c.moveToNext());

        return payments;
    }

    /**
     * get all payments that are linked to a category
     * @param idCategory id of the category
     * @return payments that are linked to the given category, null is returned if no payments are linked to the id
     */
    @SuppressLint("Range")
    public List<Payment> getAllPaymentsOfCategory(int idCategory) {
        List<Payment> payments = new LinkedList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_PAYMENT +
                " where " + PAYMENT_COLUMN_CATEGORY + "=" + idCategory + " ORDER BY " + PAYMENT_COLUMN_DATE_PAYMENT + " DESC";

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return new LinkedList<Payment>();
        }

        do {
            payments.add(new Payment(
                    c.getInt(c.getColumnIndex(PAYMENT_COLUMN_ID)),
                    c.getFloat(c.getColumnIndex(PAYMENT_COLUMN_AMOUNT)),
                    getDateFromString(c.getString(c.getColumnIndex(PAYMENT_COLUMN_DATE_PAYMENT))),
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
        List<CategoryType> categoryTypes = new LinkedList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_CATEGORY_TYPE;

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return new LinkedList<CategoryType>();
        }

        do {
            categoryTypes.add(new CategoryType(
                    c.getInt(c.getColumnIndex(CATEGORY_TYPE_COLUMN_ID)),
                    c.getString(c.getColumnIndex(CATEGORY_TYPE_COLUMN_NAME))
            ));
        } while(c.moveToNext());

        return categoryTypes;
    }

    /**
     * get all category types from the database
     * @return all category types from the database, null is returned if no category types exist
     */
    @SuppressLint("Range")
    public List<CategoryType> getAllCategoriesTypesOfBudget(int idBudget) {
        List<CategoryType> categoryTypes = new LinkedList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = new StringBuilder().append("Select * from ").append(TABLE_CATEGORY_TYPE).append(" where ").
                append(CATEGORY_TYPE_COLUMN_ID).append(" in (Select ").append(CATEGORY_COLUMN_TYPE).append(" from ").
                append(TABLE_CATEGORY).append(" where ").append(CATEGORY_COLUMN_BUDGET).append("=").append(idBudget).append(")").toString();

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return new LinkedList<CategoryType>();
        }

        do {
            categoryTypes.add(new CategoryType(
                    c.getInt(c.getColumnIndex(CATEGORY_TYPE_COLUMN_ID)),
                    c.getString(c.getColumnIndex(CATEGORY_TYPE_COLUMN_NAME))
            ));
        } while(c.moveToNext());

        return categoryTypes;
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

        if(c == null || !c.moveToFirst()) {
            return null;
        }

        return new Category(
                c.getInt(c.getColumnIndex(CATEGORY_COLUMN_ID)),
                c.getFloat(c.getColumnIndex(CATEGORY_COLUMN_THEORETICAL_AMOUNT)),
                c.getFloat(c.getColumnIndex(CATEGORY_COLUMN_REAL_AMOUNT)),
                c.getInt(c.getColumnIndex(CATEGORY_COLUMN_BUDGET)),
                c.getInt(c.getColumnIndex(CATEGORY_COLUMN_TYPE))
        );
    }

    /**
     * Get the category of budget of a category type
     * @param idBudget id of the budget
     * @param categoryTypeName name of the category type
     * @return category of the budget that is linked to the given name of a category type, null is returned if no category of the current budget is linked to the name
     */
    @SuppressLint("Range")
    public Category getCategoryOfCategoryType(int idBudget, String categoryTypeName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_CATEGORY +" INNER JOIN " + TABLE_CATEGORY_TYPE  +
                " ON "+ CATEGORY_COLUMN_TYPE+ "=" + CATEGORY_TYPE_COLUMN_ID +
                " where " + CATEGORY_TYPE_COLUMN_NAME + "=\"" + categoryTypeName + "\" AND " + CATEGORY_COLUMN_BUDGET + "=" + idBudget;

        Cursor c = db.rawQuery(query, null);

        if(c == null || !c.moveToFirst()) {
            return null;
        }

        return new Category(
                c.getInt(c.getColumnIndex(CATEGORY_COLUMN_ID)),
                c.getFloat(c.getColumnIndex(CATEGORY_COLUMN_THEORETICAL_AMOUNT)),
                c.getFloat(c.getColumnIndex(CATEGORY_COLUMN_REAL_AMOUNT)),
                c.getInt(c.getColumnIndex(CATEGORY_COLUMN_BUDGET)),
                c.getInt(c.getColumnIndex(CATEGORY_COLUMN_TYPE))
        );
    }

    /**
     * @return amount of money that is still to be used by the current budget
     */
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

    /**
     * @return the number of days left for the current budget
     */
    public long getRemainingDaysOfCurrentBudget() {
        User user = getUser();
        long diff = user.getDateNextBudget().getTime() - (new Date()).getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * Update the real amount of money spend of a category
     * @param idCategory id of the category to update
     * @param amount amount used for the update
     * @return true if the update was successful, otherwise false
     */
    private boolean updateCategoryRealAmount(int idCategory, float amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        float updatesRealAmount = getCategory(idCategory).getRealAmount() + amount;
        cv.put(CATEGORY_COLUMN_REAL_AMOUNT, updatesRealAmount);
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

        cv.put(USER_COLUMN_DATE_NEXT_BUDGET, getStringFromDate(nextBudget));
        int id = db.update(TABLE_USER, cv, ""+USER_COLUMN_ID+"=?", new String[]{"1"});
        return id != -1;
    }

    /**
     * Update the id of the current budget + the date of the next budget
     * @param idBudget id of the budget
     * @return true if the update was successful, otherwise false
     */
    public boolean updateUserCurrentBudget(int idBudget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_COLUMN_CURRENT_BUDGET, idBudget);
        cv.put(USER_COLUMN_DATE_NEXT_BUDGET, getStringFromDate(getBudget(idBudget).getDateNextBudget()));
        int id = db.update(TABLE_USER, cv, ""+USER_COLUMN_ID+"=?", new String[]{"1"});
        return id != -1;
    }

    /**
     * Update a budget
     * @param budget budget to update
     * @return true if the update was successful, otherwise false
     */
    public boolean updateBudget(Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BUDGET_COLUMN_DATE_START, getStringFromDate(budget.getDateStart()));
        cv.put(BUDGET_COLUMN_DATE_END, getStringFromDate(budget.getDateEnd()));
        cv.put(BUDGET_COLUMN_BUDGET_AMOUNT, budget.getBudgetAmount());

        int id = db.update(TABLE_BUDGET, cv, ""+BUDGET_COLUMN_ID+"=?", new String[]{""+budget.getIdBudget()});
        return id != -1;
    }

    /**
     * Update a category
     * @param category category to update
     * @return true if the update was successful, otherwise false
     */
    public boolean updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CATEGORY_COLUMN_THEORETICAL_AMOUNT, category.getTheoreticalAmount());
        cv.put(CATEGORY_COLUMN_REAL_AMOUNT, category.getRealAmount());
        cv.put(CATEGORY_COLUMN_TYPE, category.getType());
        cv.put(CATEGORY_COLUMN_BUDGET, category.getBudget());

        int id = db.update(TABLE_CATEGORY, cv, ""+CATEGORY_COLUMN_ID+"=?", new String[]{""+category.getIdCategory()});
        return id != -1;
    }

    /**
     * Delete all the budget categories of a budget
     * @param idBudget id of the budget
     */
    public void deleteBudgetCategories(int idBudget) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORY, CATEGORY_COLUMN_BUDGET+"=?", new String[]{""+idBudget});
        db.close();
    }

    /**
     * Delete a budget
     * @param idBudget id of the budget
     */
    public void deleteBudget(int idBudget) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BUDGET, BUDGET_COLUMN_ID+"=?", new String[]{""+idBudget});
        db.close();
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

    /**
     * Convert a Date to a ISO8601 string format
     * @param d date to convert
     * @return String
     */
    public static String getStringFromDate(Date d) {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS.SSS", Locale.FRANCE);
        return f.format(d);
    }

    /**
     * Convert a Date to a ISO8601 string format with only the day, the month and the year
     * @param d date to convert
     * @return String
     */
    public static String getStringWithoutTimeFromDate(Date d) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        return f.format(d);
    }

    /**
     * Convert a ISO8601 string format to a Date
     * @param s
     * @return Date
     */
    public static Date getDateFromString(String s) {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS.SSS", Locale.FRANCE);
        try {
            return f.parse(s);
        } catch (Exception e) {
            return new Date();
        }
    }
}
