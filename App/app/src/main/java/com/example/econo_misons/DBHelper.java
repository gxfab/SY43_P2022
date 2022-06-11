package com.example.econo_misons;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context) {
        super(context, "MoneyData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table User(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME_USER TEXT NOT NULL)");
        db.execSQL("create Table Budget(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME_BUD TEXT NOT NULL)");
        db.execSQL("create Table Budget_User(BUD_ID INTEGER, USER_ID INTEGER, FOREIGN KEY(BUD_ID) REFERENCES Budget(ID), FOREIGN KEY(USER_ID) REFERENCES User(ID))");
        db.execSQL("create Table Category(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME_CAT TEXT NOT NULL)");
        db.execSQL("create Table PrevBudget(MONTH_YEAR TEXT PRIMARY KEY, NAME_BUDG TEXT NOT NULL)");
        db.execSQL("create Table Envelope(PREV_DATE TEXT PRIMARY KEY, CAT_ID INT NOT NULL, ENV_SUM REAL, FOREIGN KEY(PREV_DATE) REFERENCES PrevBudget(MONTH_YEAR),FOREIGN KEY(CAT_ID) REFERENCES Category(ID))");
        db.execSQL("create Table transact(" +
                "ID INTEGER PRIMARY KEY," +
                "BUD_ID INTEGER," +
                "NAME_TRANS TEXT," +
                "USER_ID INTEGER," +
                "CAT_ID INTEGER," +
                "AM_TRANS REAL," +
                "EXPENSE BOOL," +
                "DATE_TRANS TEXT," +
                "FOREIGN KEY(BUD_ID) REFERENCES Budget(ID)," +
                "FOREIGN KEY(USER_ID) REFERENCES User(ID)," +
                "FOREIGN KEY(CAT_ID) REFERENCES Category(ID))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists User");
        db.execSQL("drop Table if exists Budget");
        db.execSQL("drop Table if exists Budget_User");
        db.execSQL("drop Table if exists Category");
        db.execSQL("drop Table if exists PrevBudget");
        db.execSQL("drop Table if exists Envelope");
        db.execSQL("drop Table if exists transact");

    }

    //User Table methods
    public Boolean addUser(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME_USER", name);
        long result = db.insert("User", null, contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateUser(int id, String newName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME_USER", newName);
        Cursor cursor = db.rawQuery("Select * from User where ID = ?", new String[] {Integer.toString(id)});
        if (cursor.getCount()>0){
            long result = db.update("User", contentValues, "ID=?", new String[] {Integer.toString(id)});
            if (result==-1){
                return false;
            }else{
                return true;
            }
        }else {
            return false;
        }


    }

    public Boolean deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where ID = ?", new String[] {Integer.toString(id)});
        if (cursor.getCount()>0){
            long result = db.delete("User", "ID=?", new String[] {Integer.toString(id)});
            if (result==-1){
                return false;
            }else{
                return true;
            }
        }else {
            return false;
        }


    }

    public Cursor getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User", null);
        return cursor;


    }

    //Budget Table methods
    public Boolean addBudget(String name, int UserID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME_BUD", name);
        long result = db.insert("Budget", null, contentValues);
        if (result==-1){
            return false;
        }else{
            ContentValues CV = new ContentValues();
            Cursor res = db.rawQuery("Select NAME_BUD from Budget where Budget(NAME_BUD) = " + name, null);
            Log.d("DB", "RawQuery : " + res.toString());
            int BudID = res.getInt(0);
            CV.put("BUD_ID", BudID);
            CV.put("USER_ID", UserID);
            result = db.insert("Budget", null, contentValues);
            return true;
        }

    }

    public Boolean updateBudget(int id, String newName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME_BUD", newName);
        Cursor cursor = db.rawQuery("Select * from Budget where ID = ?", new String[] {Integer.toString(id)});
        if (cursor.getCount()>0){
            long result = db.update("Budget", contentValues, "ID=?", new String[] {Integer.toString(id)});
            if (result==-1){
                return false;
            }else{
                return true;
            }
        }else {
            return false;
        }


    }

    public Boolean deleteBudget(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Budget where ID = ?", new String[] {Integer.toString(id)});
        if (cursor.getCount()>0){
            long result = db.delete("Budget", "ID=?", new String[] {Integer.toString(id)});
            if (result==-1){
                return false;
            }else{
                return true;
            }
        }else {
            return false;
        }


    }

    public Cursor getBudget(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Budget", null);
        return cursor;


    }
}
