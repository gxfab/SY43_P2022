package com.example.budgetzeroapp;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BudgetZero.db";

    public static final String EXP_TABLE_NAME = "expense";
    public static final String EXP_COL_ID = "id";
    public static final String EXP_COL_DATE = "date";
    public static final String EXP_COL_AMOUNT = "amount";
    public static final String EXP_COL_LABEL = "label";
    public static final String EXP_COL_TYPE = "type";
    public static final String EXP_COL_IS_STABLE = "is_stable";
    public static final String EXP_COL_DAY_NB = "day_nb";
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

    public static final String INC_CAT_TABLE_NAME = "INC_cat";
    public static final String INC_CAT_COL_ID = "id";
    public static final String INC_CAT_COL_NAME = "name";

    public static final String DEBT_TABLE_NAME = "debt";
    public static final String DEBT_COL_ID = "id";
    public static final String DEBT_COL_NAME = "name";
    public static final String DEBT_COL_MONTH_AMOUNT = "month_amount";
    public static final String DEBT_COL_TOTAL_AMOUNT = "total_amount";
    public static final String DEBT_COL_START_MONTH = "start_month";
    public static final String DEBT_COL_END_MONTH = "end_month";

    public static final String SAV_CAT_TABLE_NAME = "SAV_cat";
    public static final String SAV_CAT_COL_ID = "id";
    public static final String SAV_CAT_COL_NAME = "name";
    public static final String SAV_CAT_COL_MAX_AMOUNT = "max_amount";
    public static final String SAV_CAT_COL_PERCENTAGE = "percentage";
    public static final String SAV_CAT_COL_PRIORITY_ORDER = "priority_order";

    public static final int TYPE_EXP = 1;
    public static final int TYPE_INC = 2;
    public static final int TYPE_DEBT = 3;
    public static final int TYPE_SAV = 4;



    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    public int dateToInt(Date d){return parseInt(new SimpleDateFormat("yyyyMMdd", Locale.FRANCE).format(d)); }

    public String intDateToString(int date){
        return date%100+"/"+(date/100)%100+"/"+(date/10000);
    }

    public int dateToMonthInt(Date d){return parseInt(new SimpleDateFormat("yyyyMM", Locale.FRANCE).format(d)); }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+EXP_TABLE_NAME+
                        "("+EXP_COL_ID+" integer primary key autoincrement, " +
                        EXP_COL_DATE+" integer default "+dateToInt(new Date())+", " +
                        EXP_COL_AMOUNT+" real not null, " +
                        EXP_COL_LABEL+" text, "+
                        EXP_COL_IS_STABLE+" integer default 0, "+
                        EXP_COL_DAY_NB+" integer default 1, "+
                        EXP_COL_TYPE+" integer not null default 1, "+
                        EXP_COL_ID_EXP+" integer default null, "+
                        EXP_COL_ID_DEBT+" integer default null, "+
                        EXP_COL_ID_INC+" integer default null, "+
                        EXP_COL_ID_SAV+" integer default null," +
                        "foreign key("+EXP_COL_ID_EXP+") references "+EXP_CAT_TABLE_NAME+"(id),"+
                        "foreign key("+EXP_COL_ID_DEBT+") references "+DEBT_TABLE_NAME+"(id),"+
                        "foreign key("+EXP_COL_ID_INC+") references "+INC_CAT_TABLE_NAME+"(id),"+
                        "foreign key("+EXP_COL_ID_SAV+") references "+SAV_CAT_TABLE_NAME+"(id)"+
                        ")"
        );
        db.execSQL(
                "create table "+EXP_CAT_TABLE_NAME+
                        "("+EXP_CAT_COL_ID+" integer primary key autoincrement, " +
                        EXP_CAT_COL_NAME+" text, " +
                        EXP_CAT_COL_BUDGET+" real, " +
                        EXP_CAT_COL_IS_SUB+" integer default 0, "+
                        EXP_CAT_COL_ID_PARENT+" integer default null, "+
                        "foreign key("+EXP_CAT_COL_ID_PARENT+") references "+EXP_TABLE_NAME+"("+EXP_CAT_TABLE_NAME+")"+
                        ")"
        );
        db.execSQL(
                "create table "+INC_CAT_TABLE_NAME+
                        "("+INC_CAT_COL_ID+" integer primary key autoincrement, " +
                        INC_CAT_COL_NAME+" text)"
        );
        db.execSQL(
                "create table "+DEBT_TABLE_NAME+
                        "("+DEBT_COL_ID+" integer primary key autoincrement, " +
                        DEBT_COL_NAME+" text not null, " +
                        DEBT_COL_MONTH_AMOUNT+" real not null, " +
                        DEBT_COL_TOTAL_AMOUNT+" real not null, "+
                        DEBT_COL_START_MONTH+" integer default "+dateToMonthInt(new Date())+","+
                        DEBT_COL_END_MONTH+" integer not null "+
                        ")"
        );
        db.execSQL(
                "create table "+SAV_CAT_TABLE_NAME+
                        "("+SAV_CAT_COL_ID+" integer primary key autoincrement, " +
                        SAV_CAT_COL_NAME+" text not null, " +
                        SAV_CAT_COL_MAX_AMOUNT+" real default null, " +
                        SAV_CAT_COL_PERCENTAGE+" integer default null, "+
                        SAV_CAT_COL_PRIORITY_ORDER+" integer"+
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+EXP_CAT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+INC_CAT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DEBT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+SAV_CAT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+EXP_TABLE_NAME);
        onCreate(db);
    }

    public ContentValues expenseCV(int date,float amount, String label, int type, int catID, boolean isStable, int dayNB){
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXP_COL_AMOUNT, amount);
        contentValues.put(EXP_COL_DATE, date);
        contentValues.put(EXP_COL_DAY_NB, dayNB);
        contentValues.put(EXP_COL_LABEL, label);
        switch(type){
            case TYPE_EXP: contentValues.put(EXP_COL_ID_EXP, catID);
                break;
            case TYPE_INC: contentValues.put(EXP_COL_ID_INC, catID);
                break;
            case TYPE_DEBT: contentValues.put(EXP_COL_ID_DEBT, catID);
                break;
            case TYPE_SAV: contentValues.put(EXP_COL_ID_SAV, catID);
                break;
            default: type = TYPE_EXP;
                contentValues.put(EXP_COL_ID_EXP, catID);
        }
        contentValues.put(EXP_COL_TYPE, type);

        contentValues.put(EXP_COL_ID_DEBT, catID);
        contentValues.put(EXP_COL_ID_INC, catID);
        contentValues.put(EXP_COL_ID_SAV, catID);
        contentValues.put(EXP_COL_IS_STABLE, isStable);
        return contentValues;
    }

    public ContentValues expenseCatCV(String name, float budget, boolean isSub, int idParent){
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXP_CAT_COL_NAME, name);
        contentValues.put(EXP_CAT_COL_BUDGET, budget);
        contentValues.put(EXP_CAT_COL_IS_SUB, isSub);
        if(isSub) contentValues.put(EXP_CAT_COL_ID_PARENT, idParent);
        return contentValues;
    }

    public ContentValues incomeCatCV(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(INC_CAT_COL_NAME, name);
        return contentValues;
    }

    public ContentValues savingsCatCV(String name, float maxAmount, int percentage, int priorityOrder){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SAV_CAT_COL_NAME, name);
        contentValues.put(SAV_CAT_COL_MAX_AMOUNT, maxAmount);
        contentValues.put(SAV_CAT_COL_PERCENTAGE, percentage);
        contentValues.put(SAV_CAT_COL_PRIORITY_ORDER, priorityOrder);
        return contentValues;
    }

    public ContentValues debtCV(String name, float monthAmount, float totalAmount, int startMonth, int endMonth){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DEBT_COL_NAME, name);
        contentValues.put(DEBT_COL_MONTH_AMOUNT, monthAmount);
        contentValues.put(DEBT_COL_TOTAL_AMOUNT, totalAmount);
        contentValues.put(DEBT_COL_START_MONTH, startMonth);
        contentValues.put(DEBT_COL_END_MONTH, endMonth);
        return contentValues;
    }
    public void insertDebtCat(String name, float monthAmount, float totalAmount, int startMonth, int endMonth){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DEBT_TABLE_NAME, null, debtCV(name, monthAmount, totalAmount, startMonth, endMonth));
    }

    public void insertIncomeCat(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(INC_CAT_TABLE_NAME, null, incomeCatCV(name));
    }

    public void insertSavingsCat(String name, float maxAmount, int percentage, int priorityOrder){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(SAV_CAT_TABLE_NAME, null, savingsCatCV(name, maxAmount, percentage, priorityOrder));
    }

    public void insertExpenseCat(String name, float budget, boolean isSub, int idParent){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(EXP_CAT_TABLE_NAME, null, expenseCatCV(name, budget, isSub, idParent));
    }

    public void insertExpense(int date,float amount, String label, int type, int catID, boolean isStable, int dayNB){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(EXP_TABLE_NAME, null, expenseCV(date, amount, label, type, catID, isStable, dayNB));
    }

    public void updateDebtCat(int id, String name, float monthAmount, float totalAmount, int startMonth, int endMonth) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = debtCV(name, monthAmount, totalAmount, startMonth, endMonth);
        db.update(DEBT_TABLE_NAME, contVal, "id = ? ", new String[]{Integer.toString(id)});
    }

    public void updateIncomeCat(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = incomeCatCV(name);
        db.update(INC_CAT_TABLE_NAME,contVal,  "id = ? ", new String[] { Integer.toString(id) } );
    }

    public void updateSavingsCat(int id, String name, float maxAmount, int percentage, int priorityOrder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = savingsCatCV(name, maxAmount, percentage, priorityOrder);
        db.update(SAV_CAT_TABLE_NAME,contVal,  "id = ? ", new String[] { Integer.toString(id) } );
    }

    public void updateExpenseCat(int id, String name, float budget, boolean isSub, int idParent){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = expenseCatCV(name, budget, isSub, idParent);
        db.update(EXP_CAT_TABLE_NAME,contVal,  "id = ? ", new String[] { Integer.toString(id) } );
    }

    public void updateExpense(int id, int date,float amount, String label, int type, int catID, boolean isStable, int dayNB){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contVal = expenseCV(date, amount, label, type, catID, isStable, dayNB);
        db.update(EXP_TABLE_NAME,contVal,  "id = ? ", new String[] { Integer.toString(id) } );
    }

    public Integer deleteById (Integer id, String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table,"id = ? ", new String[] { Integer.toString(id) });
    }

    public Cursor getData(String request) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( request, null );
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, EXP_TABLE_NAME);
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public boolean insertContact (String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public ArrayList<String> getAllContacts() {
        ArrayList<String> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndexOrThrow(EXP_COL_ID_SAV)));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    public Cursor getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from contacts where id="+id+"", null );
    }
}
