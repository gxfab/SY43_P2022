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

import java.util.List;

@Database(entities = {Categorydb.class, SubCategory.class, Transaction.class}, version = 1)

public abstract class DB extends RoomDatabase{

    private static DB INSTANCE;

    public abstract CategoryDAO CategoryDAO();
    public abstract SubCategoryDAO SubCategoryDAO();
    public abstract TransactionDAO TransactionDAO();

    public static DB getAppDatabase(Context context){
        if (INSTANCE==null){
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), DB.class, "Database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            //.allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}