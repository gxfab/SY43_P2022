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
        db.execSQL("CREATE TABLE IF NOT EXISTS DEPENSES (ID_DEPENSES INTEGER PRIMARY KEY AUTOINCREMENT, ID_CAT INTEGER, DATE TEXT, NOM TEXT, MONTANT DECIMAL, FOREIGN KEY (ID_CAT) REFERENCES CATEGORIES(ID));");
        db.execSQL("CREATE TABLE IF NOT EXISTS REVENUS (ID_REVENUS INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, NOM TEXT, MONTANT DECIMAL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS DETTES (ID_DETTES INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, NOM TEXT, MONTANT DECIMAL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS EXTRA (ID_EXTRA INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, NOM TEXT, MONTANT DECIMAL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS PROJETS (ID_PROJETS INTEGER PRIMARY KEY AUTOINCREMENT, NOM TEXT, MONTANT DECIMAL);");
    }

    public void addNewRevenus(Integer id_cat, String date_revenus, String nom, Double montant) {
        // ajoute une nouvelle entrée à la table REVENUS
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_CAT", id_cat);
        values.put("DATE", date_revenus);
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
        values.put("DATE", date_depenses);
        values.put("NOM", nom);
        values.put("MONTANT", montant);

        db.insert("DEPENSES", null, values);
        db.close();
    }

    public void addNewDette(String date_dette, String nom, Double montant) {
        // ajoute une nouvelle entrée a la table DETTES
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DATE", date_dette);
        values.put("NOM", nom);
        values.put("MONTANT", montant);

        db.insert("DETTES", null, values);
        db.close();
    }

    public void addNewExtra(int id_cat, String date_extra, String nom, Double montant) {
        // ajoute une nouvelle entrée a la table EXTRA
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DATE", date_extra);
        values.put("NOM", nom);
        values.put("MONTANT", montant);

        db.insert("EXTRA", null, values);
        db.close();
    }

    public void addNewProjet(String nom, Double montant) {
        // ajoute une nouvelle entrée a la table PROJETS
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOM", nom);
        values.put("MONTANT", montant);

        db.insert("PROJETS", null, values);
        db.close();
    }




    public ArrayList<StorageDepenses> getDepenses() {
        // Retourne une liste d'objets représentat une ligne de la table DEPENSES
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select cat.NOM, dep.DATE, dep.NOM, dep.MONTANT from DEPENSES dep inner join CATEGORIE cat on cat.ID = dep.ID_CAT;", null);
        ArrayList<StorageDepenses> depensesList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                depensesList.add(new StorageDepenses(cursor.getString(1), cursor.getString(2), cursor.getString(3), Double.parseDouble(cursor.getString(4))));
            }
        }
        cursor.close();
        return depensesList;
    }

    public ArrayList<StorageRevenus> getRevenus() {
        // Retourne une liste d'objets représentat une ligne de la table REVENUS
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM REVENUS;", null);
        ArrayList<StorageRevenus> revenusList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                revenusList.add(new StorageRevenus(cursor.getString(1), cursor.getString(2), Double.parseDouble(cursor.getString(3))));
            }
        }
        cursor.close();
        return revenusList;
    }

    public ArrayList<StorageCategories> getCategories() {
        // Retourne une liste d'objets représentat une ligne de la table CATEGORIES
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

    public ArrayList<StorageDettes> getDettes() {
        // Retourne une liste d'objets représentat une ligne de la table DETTES
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DETTES;", null);
        ArrayList<StorageDettes> Liste = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                Liste.add(new StorageDettes(cursor.getString(1), cursor.getString(2), cursor.getDouble(3)));
            }
        }
        cursor.close();
        return Liste;
    }

    public ArrayList<StorageExtra> getExtra() {
        // Retourne une liste d'objets représentant une ligne de la table EXTRA.
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM EXTRA;", null);
        ArrayList<StorageExtra> Liste = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                Liste.add(new StorageExtra(cursor.getString(1), cursor.getString(2), cursor.getDouble(3)));
            }
        }
        cursor.close();
        return Liste;
    }

    public ArrayList<StorageProjets> getProjets() {
        // Retourne une lite d'objets représentant une ligne de la table PROJETS.
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PROJETS;", null);
        ArrayList<StorageProjets> Liste = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                Liste.add(new StorageProjets(cursor.getString(2), cursor.getDouble(3)));
            }
        }
        cursor.close();
        return Liste;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
