package com.example.sy43.db.mainDB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sy43.db.DAO.CategoryDAO;
import com.example.sy43.db.DAO.SubCategoryDAO;
import com.example.sy43.db.DAO.TransactionDAO;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.db.entity.Transaction;

@Database(entities = {Categorydb.class, SubCategory.class, Transaction.class}, version = 1, exportSchema = false)

public abstract class DB extends RoomDatabase{

    private static volatile DB INSTANCE;

    public abstract CategoryDAO CategoryDAO();
    public abstract SubCategoryDAO SubCategoryDAO();
    public abstract TransactionDAO TransactionDAO();

    /**
     * renvoie l'instance de la database
     * utilisé avec un processus singleton afin de n'avoir qu'une seul instance pour toute l'application
     * cela permet de créer la base de donnée si elle n'existe pas ou de retrouver l'existante si elle existe déjà
     *
     * @param  context  la localisation de l'utilisateur sur l'application
     * @return l'instance de la database
     */

    public static DB getAppDatabase(Context context){
        
        if (INSTANCE==null){
            synchronized (DB.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(), DB.class, "Database")
                                    .build();
                }
            }
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }
}