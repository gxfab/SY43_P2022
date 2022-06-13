package com.example.budgetzeroapp.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.example.budgetzeroapp.AppContext;


@SuppressWarnings({"UnusedDeclaration"})
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BudgetZero.db";

    public static final String EXP_TABLE_NAME = "expense";
    public static final String EXP_COL_ID = "id";
    public static final String EXP_COL_YEAR = "year";
    public static final String EXP_COL_MONTH = "month";
    public static final String EXP_COL_DAY = "day";
    public static final String EXP_COL_AMOUNT = "amount";
    public static final String EXP_COL_LABEL = "name";
    public static final String EXP_COL_TYPE = "type";
    public static final String EXP_COL_IS_STABLE = "is_stable";
    public static final String EXP_COL_ID_EXP = "id_expense";
    public static final String EXP_COL_ID_DEBT = "id_debt";
    public static final String EXP_COL_ID_SAV = "id_SAV";
    public static final String EXP_COL_ID_INC = "id_INC";

    public static final String EXP_CAT_TABLE_NAME = "expense_cat";
    public static final String EXP_CAT_COL_ID = "id";
    public static final String EXP_CAT_COL_NAME = "name";
    public static final String EXP_CAT_COL_BUDGET = "budget";
    public static final String EXP_CAT_COL_IS_SUB = "is_sub";
    public static final String EXP_CAT_COL_ID_PARENT = "id_parent";

    public static final String INC_CAT_TABLE_NAME = "income_cat";
    public static final String INC_CAT_COL_ID = "id";
    public static final String INC_CAT_COL_NAME = "name";

    public static final String DEBT_TABLE_NAME = "debt";
    public static final String DEBT_COL_ID = "id";
    public static final String DEBT_COL_NAME = "name";
    public static final String DEBT_COL_MONTH_LEFT = "month_left";
    public static final String DEBT_COL_TOTAL_AMOUNT = "total_amount";

    public static final String SAV_CAT_TABLE_NAME = "saving_cat";
    public static final String SAV_CAT_COL_ID = "id";
    public static final String SAV_CAT_COL_NAME = "name";
    public static final String SAV_CAT_COL_MAX_AMOUNT = "max_amount";
    public static final String SAV_CAT_COL_CURRENT_AMOUNT = "current_amount";
    public static final String SAV_CAT_COL_PERCENTAGE = "percentage";
    public static final String SAV_CAT_COL_PRIORITY_ORDER = "priority_order";

    public static final String REQ_SUM = "sum";

    public static final int TYPE_EXP = 1;
    public static final int TYPE_INC = 2;
    public static final int TYPE_DEBT = 3;
    public static final int TYPE_SAV = 4;

    public static final int TYPE_CASH_FLOW = 5;

    public static final int DEFAULT_SAV_ID = 1;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table " + EXP_CAT_TABLE_NAME +
                        "(" + EXP_CAT_COL_ID + " integer primary key autoincrement, " +
                        EXP_CAT_COL_NAME + " text, " +
                        EXP_CAT_COL_BUDGET + " real, " +
                        EXP_CAT_COL_IS_SUB + " integer default 0, " +
                        EXP_CAT_COL_ID_PARENT + " integer default null, " +
                        "foreign key(" + EXP_CAT_COL_ID_PARENT + ") references " + EXP_CAT_TABLE_NAME + "(" + EXP_CAT_COL_ID + ")" +
                        ");"
        );
        db.execSQL(
                "create table " + INC_CAT_TABLE_NAME +
                        "(" + INC_CAT_COL_ID + " integer primary key autoincrement, " +
                        INC_CAT_COL_NAME + " text);"
        );
        db.execSQL(
                "create table " + DEBT_TABLE_NAME +
                        "(" + DEBT_COL_ID + " integer primary key autoincrement, " +
                        DEBT_COL_NAME + " text not null, " +
                        DEBT_COL_MONTH_LEFT + " int not null, " +
                        DEBT_COL_TOTAL_AMOUNT + " real not null " +
                        ");"
        );
        db.execSQL(
                "create table " + SAV_CAT_TABLE_NAME +
                        "(" + SAV_CAT_COL_ID + " integer primary key autoincrement, " +
                        SAV_CAT_COL_NAME + " text not null, " +
                        SAV_CAT_COL_MAX_AMOUNT + " real default -1, " +
                        SAV_CAT_COL_CURRENT_AMOUNT + " real default 0, " +
                        SAV_CAT_COL_PERCENTAGE + " integer default null, " +
                        SAV_CAT_COL_PRIORITY_ORDER + " integer" +
                        ");"
        );
        db.execSQL(
             "create table " + EXP_TABLE_NAME +
             "(" + EXP_COL_ID + " integer primary key autoincrement, " +
             EXP_COL_DAY + " integer default " + DateManager.dateToDay(new Date()) + ", " +
             EXP_COL_MONTH + " integer default " + DateManager.dateToMonth(new Date()) + ", " +
             EXP_COL_YEAR + " integer default " + DateManager.dateToYear(new Date()) + ", " +
             EXP_COL_AMOUNT + " real not null, " +
             EXP_COL_LABEL + " text, " +
             EXP_COL_IS_STABLE + " integer default 0, " +
             EXP_COL_TYPE + " integer not null default 1, " +
             EXP_COL_ID_EXP + " integer default null, " +
             EXP_COL_ID_DEBT + " integer default null, " +
             EXP_COL_ID_INC + " integer default null, " +
             EXP_COL_ID_SAV + " integer default null," +
             "foreign key(" + EXP_COL_ID_EXP + ") references " + EXP_CAT_TABLE_NAME + "(id)," +
             "foreign key(" + EXP_COL_ID_DEBT + ") references " + DEBT_TABLE_NAME + "(id)," +
             "foreign key(" + EXP_COL_ID_INC + ") references " + INC_CAT_TABLE_NAME + "(id)," +
             "foreign key(" + EXP_COL_ID_SAV + ") references " + SAV_CAT_TABLE_NAME + "(id)" +
             ");"
         );
        /**insertExpenseCat("Shopping", 0, false, 0);
        insertExpenseCat("Vehicle", 0, false, 0);
        insertExpenseCat("Leisure", 0, false, 0);
        insertExpenseCat("Health", 0, false, 0);
        insertExpenseCat("Miscellaneous", 0, false, 0);**/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EXP_CAT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + INC_CAT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DEBT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SAV_CAT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EXP_TABLE_NAME);
        onCreate(db);
    }

    public ContentValues expenseCV(Date date, float amount, String label, int type, int catID, boolean isStable) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXP_COL_AMOUNT, amount);
        contentValues.put(EXP_COL_DAY, DateManager.dateToDay(date));
        contentValues.put(EXP_COL_MONTH, DateManager.dateToMonth(date));
        contentValues.put(EXP_COL_YEAR, DateManager.dateToYear(date));
        contentValues.put(EXP_COL_LABEL, label);
        switch (type) {
            case TYPE_EXP:
                contentValues.put(EXP_COL_ID_EXP, catID);
                break;
            case TYPE_INC:
                contentValues.put(EXP_COL_ID_INC, catID);
                break;
            case TYPE_DEBT:
                contentValues.put(EXP_COL_ID_DEBT, catID);
                break;
            case TYPE_SAV:
                contentValues.put(EXP_COL_ID_SAV, catID);
                break;
            default:
                type = TYPE_EXP;
                contentValues.put(EXP_COL_ID_EXP, catID);
        }
        contentValues.put(EXP_COL_TYPE, type);

        contentValues.put(EXP_COL_ID_DEBT, catID);
        contentValues.put(EXP_COL_ID_INC, catID);
        contentValues.put(EXP_COL_ID_SAV, catID);
        contentValues.put(EXP_COL_IS_STABLE, isStable);
        return contentValues;
    }

    public ContentValues expenseCatCV(String name, float budget, boolean isSub, int idParent) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXP_CAT_COL_NAME, name);
        contentValues.put(EXP_CAT_COL_BUDGET, budget);
        contentValues.put(EXP_CAT_COL_IS_SUB, isSub);
        if (isSub) contentValues.put(EXP_CAT_COL_ID_PARENT, idParent);
        return contentValues;
    }

    public ContentValues incomeCatCV(String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(INC_CAT_COL_NAME, name);
        return contentValues;
    }

    public ContentValues savingsCatCV(String name, float maxAmount, int percentage, int priorityOrder) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SAV_CAT_COL_NAME, name);
        contentValues.put(SAV_CAT_COL_MAX_AMOUNT, maxAmount);
        contentValues.put(SAV_CAT_COL_PERCENTAGE, percentage);
        contentValues.put(SAV_CAT_COL_PRIORITY_ORDER, priorityOrder);
        return contentValues;
    }

    public ContentValues debtCV(String name, int leftMonths, float totalAmount) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DEBT_COL_NAME, name);
        contentValues.put(DEBT_COL_MONTH_LEFT, leftMonths);
        contentValues.put(DEBT_COL_TOTAL_AMOUNT, totalAmount);
        return contentValues;
    }

    public void insertDebtCat(String name, int leftMonths, float totalAmount) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DEBT_TABLE_NAME, null, debtCV(name, leftMonths, totalAmount));
    }

    public void insertIncomeCat(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(INC_CAT_TABLE_NAME, null, incomeCatCV(name));
    }

    public void insertSavingsCat(String name, float maxAmount, int percentage, int priorityOrder) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(SAV_CAT_TABLE_NAME, null, savingsCatCV(name, maxAmount, percentage, priorityOrder));
    }

    public void insertExpenseCat(String name, float budget, boolean isSub, int idParent) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(EXP_CAT_TABLE_NAME, null, expenseCatCV(name, budget, isSub, idParent));
    }

    public void insertExpense(Date date, float amount, String label, int type, int catID, boolean isStable) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(EXP_TABLE_NAME, null, expenseCV(date, amount, label, type, catID, isStable));
    }

    public void updateDebtCat(int id, String name, int leftMonths, float totalAmount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = debtCV(name, leftMonths, totalAmount);
        db.update(DEBT_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(id)});
    }

    public void updateIncomeCat(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = incomeCatCV(name);
        db.update(INC_CAT_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(id)});
    }

    public void updateSavingsCat(int id, String name, float maxAmount, int percentage, int priorityOrder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = savingsCatCV(name, maxAmount, percentage, priorityOrder);
        db.update(SAV_CAT_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(id)});
    }

    public void updateExpenseCat(int id, String name, float budget, boolean isSub, int idParent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = expenseCatCV(name, budget, isSub, idParent);
        db.update(EXP_CAT_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(id)});
    }

    public void updateExpense(int id, Date date, float amount, String label, int type, int catID, boolean isStable, int dayNB) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = expenseCV(date, amount, label, type, catID, isStable);
        db.update(EXP_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(id)});
    }

    public void updateSavingsCurrentAmount(int id, float amount) {
        ContentValues contVal = new ContentValues();
        contVal.put(SAV_CAT_COL_CURRENT_AMOUNT, amount);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(SAV_CAT_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(id)});
    }

    public boolean decrementDebtMonthLeft(int idCat) {
        Cursor c = getData("select " + DEBT_COL_MONTH_LEFT +
                " from " + DEBT_TABLE_NAME + " where id = " + idCat);
        c.moveToFirst();
        int monthLeft = c.getInt(c.getColumnIndexOrThrow(DBHelper.DEBT_COL_MONTH_LEFT));
        if (monthLeft == 0) return false;
        monthLeft--;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = new ContentValues();
        contVal.put(DEBT_COL_MONTH_LEFT, monthLeft);
        db.update(SAV_CAT_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(idCat)});
        return true;
    }

    public void deleteById(Integer id, String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, "id = ? ", new String[]{Integer.toString(id)});
    }

    public Cursor getData(String request) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(request, null);
    }

    public void test() {
        SQLiteDatabase db = this.getReadableDatabase();
    }

    public Cursor getTableName(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
    }

    public boolean exists() {
        return AppContext.getContext().getDatabasePath(DATABASE_NAME).exists();
    }

    public Cursor getExpenseFromID(int id) {
        Cursor exp = getData(
                "select * from " + EXP_TABLE_NAME +
                        " where " + EXP_COL_ID + "=" + id);
        exp.moveToFirst();
        return exp;
    }

    public float getSumExp() {
        Cursor res = getData("select sum(" + EXP_COL_AMOUNT + ") as " + REQ_SUM +
                " from " + EXP_TABLE_NAME+
                " where " + EXP_COL_TYPE + "<>" + TYPE_INC);
        if(res.moveToFirst())return res.getFloat(res.getColumnIndexOrThrow(REQ_SUM));
        return 0;
    }

    public float getSumSav() {
        Cursor res = getData("select sum(" + EXP_COL_AMOUNT + ") as " + REQ_SUM +
                " from " + EXP_TABLE_NAME +
                " where " + EXP_COL_TYPE + "=" + TYPE_SAV);
        res.moveToFirst();
        return res.getFloat(res.getColumnIndexOrThrow(REQ_SUM));
    }

    public float getSumDebt() {
        Cursor res = getData("select sum(" + EXP_COL_AMOUNT + ") as " + REQ_SUM +
                " from " + EXP_TABLE_NAME +
                " where " + EXP_COL_TYPE + "=" + TYPE_DEBT);
        res.moveToFirst();
        return res.getFloat(res.getColumnIndexOrThrow(REQ_SUM));
    }

    public float getSumCatExp(int idCat) {
        Cursor res = getData("select sum(" + EXP_COL_AMOUNT + ") as " + REQ_SUM +
                " from " + EXP_TABLE_NAME +
                " where " + EXP_COL_TYPE + "=" + TYPE_EXP +
                " and (" + EXP_CAT_COL_ID + "=" + idCat +
                " or " + EXP_CAT_COL_ID_PARENT + "=" + idCat + ")");
        res.moveToFirst();
        return res.getFloat(res.getColumnIndexOrThrow(REQ_SUM));
    }

    public Cursor getMainExpCat() {
        return getData("select * from " + EXP_CAT_TABLE_NAME +
                " where " + EXP_CAT_COL_IS_SUB + " = 0");
    }

    public Cursor getExpenseCatFromID(int id) {
        Cursor exp = getData(
                "select * from " + EXP_CAT_TABLE_NAME +
                        " where " + EXP_CAT_COL_ID + "=" + id);
        exp.moveToFirst();
        return exp;
    }

    public String getExpCatName(int id) {
        Cursor exp = getData(
                "select " + EXP_CAT_COL_NAME + " from " + EXP_CAT_TABLE_NAME +
                        " where " + EXP_CAT_COL_ID + "=" + id);
        if (exp.moveToFirst()) return exp.getString(exp.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
        return "";
    }

    public String getNameFromID(int id, String table) {
        Cursor row = getData(
                "select name from " + table + " where id=" + id);
        if (row.moveToFirst()) return row.getString(row.getColumnIndexOrThrow("name"));
        return "";
    }

    public String getExpCat(int id) {
        Cursor exp = getData(
                "select " + EXP_CAT_COL_NAME + " from " + EXP_CAT_TABLE_NAME +
                        " where " + EXP_CAT_COL_ID + "=" + id);
        exp.moveToFirst();
        if (!exp.isAfterLast())
            return exp.getString(exp.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
        return "";
    }

    public Cursor getSavingsFromPriority(int priority) {
        Cursor saving = getData(
                "select * from " + SAV_CAT_TABLE_NAME +
                        " where " + SAV_CAT_COL_PRIORITY_ORDER + "=" + priority);
        saving.moveToFirst();
        return saving;
    }

    public Cursor getAllSavingsCat() {
        Cursor saving = getData("select * from " + SAV_CAT_TABLE_NAME);
        saving.moveToFirst();
        return saving;
    }

    public Cursor getAllExpenses() {
        Cursor saving = getData("select * from " + EXP_TABLE_NAME +
                "order by " + EXP_COL_YEAR + " desc, " +
                EXP_COL_MONTH + " desc, " +
                EXP_COL_DAY + " desc");
        saving.moveToFirst();
        return saving;
    }

    public Cursor getDateExpenses(int year, int month, int day) {
        Cursor saving = getData("select * from " + EXP_TABLE_NAME +
                " where " + EXP_COL_DAY + " = " + day +
                "and " + EXP_COL_MONTH + " = " + month +
                "and " + EXP_COL_YEAR + " = " + year);
        saving.moveToFirst();
        return saving;
    }

    public Cursor getEndMonthExpenses(int year, int month, int day) {
        Cursor saving = getData("select * from " + EXP_TABLE_NAME +
                " where " + EXP_COL_DAY + " <= " + day +
                "and " + EXP_COL_MONTH + " = " + month +
                "and " + EXP_COL_YEAR + " = " + year);
        saving.moveToFirst();
        return saving;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, EXP_TABLE_NAME);
    }
}