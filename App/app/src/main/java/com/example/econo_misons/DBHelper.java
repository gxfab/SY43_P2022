package com.example.econo_misons;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context) {
        super(context, "MoneyData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table User(ID INT primary key, NAME_USER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists User");
    }

    public Boolean addUser(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("NAME_USER", name);
        long result = db.insert("User", null, contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateUser(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME_USER", name);
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

    public Boolean deleteUser(int id, String name){
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

    public Cursor getUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User", null);
        return cursor;


    }
}
