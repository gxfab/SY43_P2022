package com.example.zerobudbanker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.zerobudbanker.models.TransactionModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TRANSACTION_TABLE = "TRANSACTION_TABLE";
    public static final String COLUMN_ID_TRANSACTION = "ID_TRANSACTION";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_AMOUNT = "AMOUNT";
    public static final String COLUMN_CREATION_DATE = "CREATION_DATE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_CATEGORY = "CATEGORY";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TRANSACTION_TABLE + " (" + COLUMN_ID_TRANSACTION + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_AMOUNT + " INT, " + COLUMN_CREATION_DATE + " INT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_CATEGORY + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(TransactionModel transactionModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, transactionModel.getName());
        cv.put(COLUMN_AMOUNT, transactionModel.getAmount());
        cv.put(COLUMN_CREATION_DATE, transactionModel.getCreationDate());
        cv.put(COLUMN_DESCRIPTION, transactionModel.getDescription());
        cv.put(COLUMN_CATEGORY, transactionModel.getCategory());

        long insert = db.insert(TRANSACTION_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteOne(TransactionModel transactionModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TRANSACTION_TABLE + " WHERE " + COLUMN_ID_TRANSACTION + " = " + transactionModel.getId_transaction();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public List<TransactionModel> getEveryone() {

        List<TransactionModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TRANSACTION_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
               int transactionID = cursor.getInt(0);
               String transactionName = cursor.getString(1);
               int transactionAmount = cursor.getInt(2);
               int transactionCreationDate = cursor.getInt(3);
               String transactionDescription = cursor.getString(4);
               String transactionCategory = cursor.getString(5);

               TransactionModel newTransaction = new TransactionModel(transactionID, transactionName, transactionAmount, transactionCreationDate, transactionDescription, transactionCategory, 1, 1);
               returnList.add(newTransaction);
            } while (cursor.moveToNext());
        } else {
            //noting to do
        }

        cursor.close();
        db.close();
        return returnList;
    }
}
