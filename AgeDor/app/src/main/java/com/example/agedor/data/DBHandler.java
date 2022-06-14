package com.example.agedor.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "agedor";
    private static final String TABLE_NAME = "mycourses";
    public DBHandler(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS CATEGORIES (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOM TEXT, MONTANT DECIMAL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS DEPENSES (ID_DEPENSES INTEGER PRIMARY KEY AUTOINCREMENT, ID_CAT INTEGER, DATE_DEPENSES TEXT, NOM TEXT, MONTANT DECIMAL, FOREIGN KEY (ID_CAT) REFERENCES CATEGORIES(ID));");
        db.execSQL("CREATE TABLE IF NOT EXISTS REVENUS (ID_REVENUS INTEGER PRIMARY KEY AUTOINCREMENT, ID_CAT INTEGER, DATE_REVENUS TEXT, NOM TEXT, MONTANT DECIMAL, FOREIGN KEY (ID_CAT) REFERENCES CATEGORIES(ID));");
    }

    public void addNewRevenus(Integer id_cat, String date_revenus, String nom, Double montant) {
        // ajoute une nouvelle entrée à la table REVENUS
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_CAT", id_cat);
        values.put("DATE_REVENUS", date_revenus);
        values.put("NOM", nom);
        values.put("MONTANT", montant);

        db.insert("REVENUS", null, values);
        db.close();
    }

    public void addNewCategorie(String nom, Double montant) {
        // ajoute une nouvelle entrée a la table CATEGORIES
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOM", nom);
        values.put("MONTANT", montant);

        db.insert("CATEGORIES", null, values);
        db.close();
    }

    public void addNewDepense(int id_cat, String date_depenses, String nom, Double montant) {
        // ajoute une nouvelle entrée a la table DEPENSES
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_CAT", id_cat);
        values.put("DATE_DEPENSES", date_depenses);
        values.put("NOM", nom);
        values.put("MONTANT", montant);

        db.insert("DEPENSES", null, values);
        db.close();
    }


    public ArrayList<StorageDepenses> getDepenses() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DEPENSES;", null);
        ArrayList<StorageDepenses> depensesList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                depensesList.add(new StorageDepenses(Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), Double.parseDouble(cursor.getString(4))));
            }
        }
        cursor.close();
        return depensesList;
    }

    public ArrayList<StorageRevenus> getRevenus() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM REVENUS;", null);
        ArrayList<StorageRevenus> revenusList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                revenusList.add(new StorageRevenus(Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), Double.parseDouble(cursor.getString(4))));
            }
        }
        cursor.close();
        return revenusList;
    }

    public ArrayList<StorageCategories> getCategories() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CATEGORIES;", null);
        ArrayList<StorageCategories> categoriesList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                categoriesList.add(new StorageCategories(cursor.getString(1), Double.parseDouble(cursor.getString(2))));
            }
        }
        cursor.close();
        return categoriesList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
