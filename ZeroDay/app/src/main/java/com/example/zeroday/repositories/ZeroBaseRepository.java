package com.example.zeroday.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zeroday.models.ExpenseCategory;
import com.example.zeroday.models.ZeroBaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ZeroBaseRepository<T extends ZeroBaseModel> {

    protected SQLiteDatabase sqLiteDatabase;
    protected String tableName;
    protected String primaryKeyColumn;

    private static final String SELECT_ALL_STRING = "SELECT * FROM ";

    public ZeroBaseRepository(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public String getTableName() {
        return tableName;
    }

    public abstract List<T> fromCursor(Cursor cursor);
    public abstract ContentValues toContentValues(T zeroBaseModelobject);

    public List<T> getAll(){
        try {
            Cursor cursor = this.sqLiteDatabase.rawQuery(SELECT_ALL_STRING + this.tableName, null);
            return fromCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public T findOneById(Long id){
        try {
            Cursor cursor = this.sqLiteDatabase.rawQuery(SELECT_ALL_STRING + this.tableName + " WHERE " + this.primaryKeyColumn + " = " + id, null);
            // get first
            if(cursor.moveToFirst()) {
                return (T) fromCursor(cursor).get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return null;
    }

    public T findOneByCode(String code){
        //new error not implemented
        throw new UnsupportedOperationException();
    }

    public Long insert(T zeroBaseModelobject){
        ContentValues contentValues = toContentValues(zeroBaseModelobject);
        return sqLiteDatabase.insert(this.tableName, "ID", contentValues);
    }

    public Long update(T zeroBaseModelobject){
        ContentValues contentValues = toContentValues(zeroBaseModelobject);
        String whereClause = this.primaryKeyColumn + " = ?";
        String[] whereArgs = {zeroBaseModelobject.getId().toString()};
        return (long) sqLiteDatabase.update(this.tableName, contentValues, whereClause, whereArgs);
    }

    public Long delete(T zeroBaseModelobject){
        String whereClause = this.primaryKeyColumn + " = ?";
        String[] whereArgs = {zeroBaseModelobject.getId().toString()};
        return (long) sqLiteDatabase.delete(this.tableName, whereClause, whereArgs);
    }

    public List<Long> insertBatch(List<T> zeroBaseModelobjects){
        List<Long> ids = new ArrayList<Long>();
        for (T zeroBaseModelobject : zeroBaseModelobjects) {
            ids.add(insert(zeroBaseModelobject));
        }
        return ids;
    }

    public List<Long> updateBatch(List<T> zeroBaseModelobjects){
        List<Long> ids = new ArrayList<Long>();
        for (T zeroBaseModelobject : zeroBaseModelobjects) {
            ids.add(update(zeroBaseModelobject));
        }
        return ids;
    }

    public int count(){
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_STRING + this.tableName, null);
        return cursor.getCount();
    }

    public void deleteAll(){
        sqLiteDatabase.delete(this.tableName, null, null);
    }

 
}
