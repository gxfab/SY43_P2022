package com.example.sy43.db.mainDB;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sy43.db.DAO.CategoryDAO;
import com.example.sy43.db.DAO.SubCategoryDAO;
import com.example.sy43.db.DAO.TransactionDAO;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.db.entity.Transaction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {Categorydb.class, SubCategory.class, Transaction.class}, version = 1, exportSchema = false)

public abstract class DB extends RoomDatabase{

    private static volatile DB INSTANCE;

    public abstract CategoryDAO CategoryDAO();
    public abstract SubCategoryDAO SubCategoryDAO();
    public abstract TransactionDAO TransactionDAO();

    public static DB getAppDatabase(Context context){
        if (INSTANCE==null){
            synchronized (DB.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(), DB.class, "Database")
                                    // allow queries on the main thread.
                                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                                    //.allowMainThreadQueries()
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