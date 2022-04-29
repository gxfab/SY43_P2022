package com.example.sy43.db.mainDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Typeconverter;
import android.content.Context;

//lien converter
//lien dao
import com.example.sy43.entity.Transaction;
import com.example.sy43.entity.Category;
import com.example.sy43.entity.SubCategory;

@Database(entities = {Transaction.class, Category.class, SubCategory.class}, version =1)
public abstract class DepenseDatabase extends RoomDatabase{
    
    private static DepenseDatabase INSTANCE;

    //public abstract DAONAME funcdao();

    public static DepenseDatabase getAppDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationsContext(), DepenseDatabase.class, "Depense - database")
                //add passage dasn thread
                .build();
        }
        return INSTANCE;
    }
    public static void destroyInstance(){
        INSTANCE = null;
    }
}