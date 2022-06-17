package com.example.budgetzeroapp.tool;

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
    public static final String EXP_COL_IS_STABLE = "is_stable"; //Debt: stable, Savings: instable
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
    public static final String REQ_CAT_NAME = "cat_name";

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
                        EXP_CAT_COL_BUDGET + " real default 0, " +
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
                        EXP_COL_DAY + " integer not null, " +
                        EXP_COL_MONTH + " integer not null, " +
                        EXP_COL_YEAR + " integer not null, " +
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
        //Example expense categories - id 1->6
        db.execSQL(
                "insert into "+EXP_CAT_TABLE_NAME+"("+EXP_CAT_COL_NAME+","+EXP_CAT_COL_BUDGET+")"+
                        " values ('Shopping',400),('Vehicle',800),('Leisure',150),('Health',300),('Bills',700),('Miscellaneous',100);"
        );
        //Example expense subcategories - id 7->11
        db.execSQL(
                "insert into "+EXP_CAT_TABLE_NAME+"("+EXP_CAT_COL_NAME+","+EXP_CAT_COL_BUDGET+","+EXP_CAT_COL_IS_SUB+", "+EXP_CAT_COL_ID_PARENT+")"+
                        " values ('Food',200,1,1),('Other',200,1,1),('Sport',50,1,3),('Party',40,1,3),('Other',60,1,3);"
        );
        //Example income category - id 12->13
        db.execSQL(
                "insert into "+INC_CAT_TABLE_NAME+"("+INC_CAT_COL_NAME+")"+
                        " values ('Salary'),('Other');"
        );
        //Example debt category - id 14->16
        db.execSQL(
                "insert into "+DEBT_TABLE_NAME+"("+DEBT_COL_NAME+","+DEBT_COL_TOTAL_AMOUNT+","+DEBT_COL_MONTH_LEFT+")" +
                        "values ('House',100000,63),('Presidential Campaign',5000000,34),('Last weeks pizza',7,1);"
        );
        //Example savings category - id 17->20
        db.execSQL(
                "insert into "+SAV_CAT_TABLE_NAME+"("+SAV_CAT_COL_NAME+","+SAV_CAT_COL_MAX_AMOUNT+","+SAV_CAT_COL_CURRENT_AMOUNT+")" +
                        "values ('New PC',1200,340),('Trip to Iceland',840,121),('Swimming Pool',5000,245),('Spaceship',100000,3739);"
        );
        //Example expenses
        db.execSQL(
                "insert into "+EXP_TABLE_NAME+
                        "("+EXP_COL_LABEL+","+EXP_COL_AMOUNT+","+EXP_COL_TYPE+","+EXP_COL_ID_EXP+
                        ", "+EXP_COL_DAY+", "+EXP_COL_MONTH+", "+EXP_COL_YEAR+","+EXP_COL_IS_STABLE+")"+
                        " values ('Party shopping', -120, 1, 8, 14, 6, 2022,0)," +
                        "('Weekly shopping', -70, 1, 7, 9, 6, 2022,0)," +
                        "('Weekly shopping', -57, 1, 7, 16, 6, 2022,0)," +
                        "('Electricity',-307, 1, 5, 1, 6, 2022,0)," +
                        "('Loan',-450,1,5,5,6,2022,1)," +
                        "('Netflix',-20,1,3,5,6,2022,1);"
        );
        //Example incomes
        db.execSQL(
                "insert into "+EXP_TABLE_NAME+
                        "("+EXP_COL_LABEL+","+EXP_COL_AMOUNT+","+EXP_COL_TYPE+","+EXP_COL_ID_EXP+
                        ", "+EXP_COL_DAY+", "+EXP_COL_MONTH+", "+EXP_COL_YEAR+","+EXP_COL_IS_STABLE+")"+
                        " values ('Salary', 4500, 2, 12, 1, 6, 2022,1)," +
                        "('Birthday present (grandma)', 100, 2, 13, 13, 6, 2022,0)," +
                        "('Birthday present (uncle)', 20, 2, 13, 13, 6, 2022,0);"
        );
        //Example debt reimbursements
        db.execSQL(
                "insert into "+EXP_TABLE_NAME+
                        "("+EXP_COL_LABEL+","+EXP_COL_AMOUNT+","+EXP_COL_TYPE+","+EXP_COL_ID_EXP+
                        ", "+EXP_COL_DAY+", "+EXP_COL_MONTH+", "+EXP_COL_YEAR+","+EXP_COL_IS_STABLE+")"+
                        " values ('House', -509, 3, 14, 30, 4, 2022,1)," +
                        "('House', -546, 3, 14, 31, 5, 2022,1);"
        );
        //Example savings
        db.execSQL(
                "insert into "+EXP_TABLE_NAME+
                        "("+EXP_COL_LABEL+","+EXP_COL_AMOUNT+","+EXP_COL_TYPE+","+EXP_COL_ID_EXP+
                        ", "+EXP_COL_DAY+", "+EXP_COL_MONTH+", "+EXP_COL_YEAR+","+EXP_COL_IS_STABLE+")"+
                        " values ('New PC', -33, 4, 17, 31, 5, 2022,0)," +
                        "('Iceland', -45, 4, 18, 31, 5, 2022,0);"
        );
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

    public ContentValues expenseCV(float amount, String label, int type, int catID, boolean isStable, int day, int month, int year) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXP_COL_AMOUNT, amount);
        contentValues.put(EXP_COL_DAY, day);
        contentValues.put(EXP_COL_MONTH, month);
        contentValues.put(EXP_COL_YEAR, year);
        contentValues.put(EXP_COL_LABEL, label);
        switch (type) {
            case TYPE_EXP: contentValues.put(EXP_COL_ID_EXP, catID);
                break;
            case TYPE_INC: contentValues.put(EXP_COL_ID_INC, catID);
                break;
            case TYPE_DEBT: contentValues.put(EXP_COL_ID_DEBT, catID);
                break;
            case TYPE_SAV: contentValues.put(EXP_COL_ID_SAV, catID);
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

    public void insertExpense(float amount, String label, int type, int catID,
                              boolean isStable, int day, int month, int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(EXP_TABLE_NAME, null, expenseCV(amount, label, type, catID, isStable, day, month, year));
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

    public void updateExpense(int id, float amount, String label, int type, int catID, boolean isStable, int day, int month, int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = expenseCV(amount, label, type, catID, isStable, day, month, year);
        db.update(EXP_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(id)});
    }

    public void updateSavingsCurrentAmount(int id, float amount) {
        ContentValues contVal = new ContentValues();
        contVal.put(SAV_CAT_COL_CURRENT_AMOUNT, amount);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(SAV_CAT_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(id)});
    }
    public void updateExpenseCatBudget(int id, float amount) {
        ContentValues contVal = new ContentValues();
        contVal.put(EXP_CAT_COL_BUDGET, amount);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(EXP_CAT_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(id)});
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

    public Cursor getExpensesFromCat(int idCat, int type){
        return getData("select * from "+EXP_TABLE_NAME+
                " where " +getCatColName(type)+ " = "+idCat+" and " +
                EXP_COL_TYPE +" = "+type);
    }

    public float getSumFromCat(int idCat, int type){
        Cursor res = getData("select sum("+EXP_COL_AMOUNT+") as "+REQ_SUM+
                " from "+EXP_TABLE_NAME+
                " where " +getCatColName(type)+ " = "+idCat);
        res.moveToFirst();
        return res.getFloat(res.getColumnIndexOrThrow(REQ_SUM));
    }

    public float getAccountMoneyAmount(){
        Cursor res = getData("select sum("+EXP_COL_AMOUNT+") as "+
                REQ_SUM+ " from "+EXP_TABLE_NAME);
        res.moveToFirst();
        return res.getFloat(res.getColumnIndexOrThrow(REQ_SUM));
    }

    public Cursor getCatFromType(int idCat, int type){
        return getData("select * from "+getTableName(type)+
                " where id = "+idCat);
    }

    public Cursor getSubCat(int idCat){
        return getData("select * from "+EXP_CAT_TABLE_NAME+
                " where "+EXP_CAT_COL_IS_SUB+" = 1 and "+
                EXP_CAT_COL_ID_PARENT+" = "+idCat);
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

    public Cursor getExpCatExpAndSub(int idCat) {
        return getData("select * "+
                " from " + EXP_TABLE_NAME +
                " where " + EXP_COL_TYPE + "=" + TYPE_EXP + " and " +
                "(" +
                EXP_COL_ID_EXP + "=" + idCat +
                " or " + EXP_COL_ID_EXP + " in " +
                "(select " + EXP_CAT_COL_ID + " from " + EXP_CAT_TABLE_NAME +
                " where " + EXP_CAT_COL_IS_SUB + " = 1 and " +
                EXP_CAT_COL_ID_PARENT + " = " + idCat +
                ")" +
                ")"
        );
    }



    public float getSumCatExp(int idCat) {
    Cursor res = getData("select sum(" + EXP_COL_AMOUNT + ") as " + REQ_SUM +
            " from " + EXP_TABLE_NAME +
            " where " + EXP_COL_TYPE + "=" + TYPE_EXP + " and " +
            "(" +
                EXP_COL_ID_EXP + "=" + idCat+
                " or "+EXP_COL_ID_EXP+ " in " +
                    "(select "+EXP_CAT_COL_ID+" from "+EXP_CAT_TABLE_NAME+
                    " where "+EXP_CAT_COL_IS_SUB+" = 1 and "+
                    EXP_CAT_COL_ID_PARENT+" = "+idCat+
                    ")"+
            ")"
            );

        res.moveToFirst();
        return res.getFloat(res.getColumnIndexOrThrow(REQ_SUM));
    }

    public static String getTableName(int type){
        switch(type){
            case TYPE_EXP: return EXP_CAT_TABLE_NAME;
            case TYPE_INC: return INC_CAT_TABLE_NAME;
            case TYPE_SAV: return SAV_CAT_TABLE_NAME;
            case TYPE_DEBT: return DEBT_TABLE_NAME;
            default : return EXP_TABLE_NAME;
        }
    }

    public static String getCatColName(int type){
        switch(type){
            default:
            case TYPE_EXP: return EXP_COL_ID_EXP;
            case TYPE_INC: return EXP_COL_ID_INC;
            case TYPE_SAV: return EXP_COL_ID_SAV;
            case TYPE_DEBT: return EXP_COL_ID_DEBT;
        }
    }

    public String getNameFromEXPCursor(Cursor c, int type) {
        String table;
        int id;
        switch(type){
            case TYPE_EXP:
                id = c.getInt(c.getColumnIndexOrThrow(EXP_COL_ID_EXP));
                table = EXP_CAT_TABLE_NAME;
                break;
            case TYPE_INC:
                id = c.getInt(c.getColumnIndexOrThrow(EXP_COL_ID_INC));
                table = INC_CAT_TABLE_NAME;
                break;
            case TYPE_SAV:
                id = c.getInt(c.getColumnIndexOrThrow(EXP_COL_ID_SAV));
                table = SAV_CAT_TABLE_NAME;
                break;
            case TYPE_DEBT:
                id = c.getInt(c.getColumnIndexOrThrow(EXP_COL_ID_DEBT));
                table = DEBT_TABLE_NAME;
                break;
            default : return "";
        }
        return getNameFromID(id, table);
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
        return getData(
                "select * from " + SAV_CAT_TABLE_NAME +
                        " where " + SAV_CAT_COL_PRIORITY_ORDER + "=" + priority);
    }

    public Cursor getAllSavingsCat() {
        return getData("select * from " + SAV_CAT_TABLE_NAME);
    }

    public Cursor getAllExpenseCat() {
        return getData("select * from " + EXP_CAT_TABLE_NAME);
    }

    public Cursor getAllDebts() {
        return getData("select * from " + DEBT_TABLE_NAME);
    }

    public Cursor getAllIncomeCat() {
        return getData("select * from " + INC_CAT_TABLE_NAME);
    }

    public Cursor getAllExpenses() {
        return getData("select * from " + EXP_TABLE_NAME +
                " order by " + EXP_COL_YEAR + " desc, " +
                EXP_COL_MONTH + " desc, " +
                EXP_COL_DAY + " desc");
    }

    public Cursor getLastMonthStable(int type){
        String operator;
        if(type == TYPE_INC) operator = " = ";
        else operator = " <> ";
        return getData("select * from "+EXP_TABLE_NAME+
                " where "+EXP_COL_IS_STABLE+"= 1 and "+
                EXP_COL_TYPE +operator+TYPE_INC+
                " and " +isLastMonthReq());
    }

    public String isLastMonthReq(){
        int day = DateManager.dateToDay(new Date());
        int month = DateManager.dateToMonth(new Date());
        int year = DateManager.dateToYear(new Date());
        int prevMonth = DateManager.previousMonth(month);
        int prevMonthYear = DateManager.previousMonthYear(month, year);

        return "(("+EXP_COL_DAY+" > "+day+
                " and "+EXP_COL_MONTH+" = "+prevMonth+
                " and "+EXP_COL_YEAR+" = "+prevMonthYear+
                ") or ("+EXP_COL_DAY+" <= "+day+
                " and "+EXP_COL_MONTH+" = "+month+
                " and "+EXP_COL_YEAR+" = "+year+"))";
    }

    public Cursor getDateExpenses(int year, int month, int day) {
        return getData("select * from " + EXP_TABLE_NAME +
                " where " + EXP_COL_DAY + " = " + day +
                " and " + EXP_COL_MONTH + " = " + month +
                " and " + EXP_COL_YEAR + " = " + year);
    }

    public Cursor getEndMonthExpenses(int year, int month, int day) {
        return getData("select * from " + EXP_TABLE_NAME +
                " where " + EXP_COL_DAY + " <= " + day +
                " and " + EXP_COL_MONTH + " = " + month +
                " and " + EXP_COL_YEAR + " = " + year);
    }

    public float getSumExpCatMonth(int idCat, int month){
        Cursor res = getData("select sum(" + EXP_COL_AMOUNT + ") as " + REQ_SUM +
                " from " + EXP_TABLE_NAME +
                " where " + EXP_COL_TYPE + "=" + TYPE_EXP + " and " +
                EXP_COL_MONTH+" = " + month + " and "+
                "(" +
                    EXP_COL_ID_EXP + "=" + idCat+
                    " or "+EXP_COL_ID_EXP+ " in " +
                    "(select "+EXP_CAT_COL_ID+" from "+EXP_CAT_TABLE_NAME+
                        " where "+EXP_CAT_COL_IS_SUB+" = 1 and "+
                        EXP_CAT_COL_ID_PARENT+" = "+idCat+
                    ")"+
                ")"
        );

        res.moveToFirst();
        return res.getFloat(res.getColumnIndexOrThrow(REQ_SUM));
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, EXP_TABLE_NAME);
    }
}