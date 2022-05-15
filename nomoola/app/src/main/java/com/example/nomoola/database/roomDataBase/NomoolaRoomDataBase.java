package com.example.nomoola.database.roomDataBase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.nomoola.database.dao.CategoryDAO;
import com.example.nomoola.database.dao.UnderCategoryDAO;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.UnderCategory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, UnderCategory.class}, version = 1, exportSchema = false)
public abstract class NomoolaRoomDataBase extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();
    public abstract UnderCategoryDAO underCategoryDAO();

    private static volatile NomoolaRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NomoolaRoomDataBase getDatabase(final Context context) {
        Log.d("CREATION", "getting database");
        if (INSTANCE == null) {
            synchronized (NomoolaRoomDataBase.class) {
                if (INSTANCE == null) {
                    Log.d("CREATION", "building database");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NomoolaRoomDataBase.class, "DB_CATEGORY")
                            .addCallback(sRoomDataBaseCallback)
                            .build();
                }
            }
        }

        //Dummy query to force the method onCreate() to be call
        INSTANCE.query("select 1", null);

        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDataBaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.d("CREATION", "onCreate() from sRoomDataBaseCallBack is being execute");
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                populateCategory();
                populateUnderCategory();
            });
        }

        private void populateCategory(){
            CategoryDAO dao = INSTANCE.categoryDAO();

            Category cat = new Category("Food");
            dao.insertCategory(cat);
            cat = new Category("Car");
            dao.insertCategory(cat);
        }

        private void populateUnderCategory(){
            UnderCategoryDAO dao = INSTANCE.underCategoryDAO();

            UnderCategory undCat = new UnderCategory("Food", "Groceries");
            dao.insertUnderCategory(undCat);
            undCat = new UnderCategory("Food", "Restaurants");
            dao.insertUnderCategory(undCat);

            undCat = new UnderCategory("Car", "Reparations");
            dao.insertUnderCategory(undCat);
            undCat = new UnderCategory("Car", "Gaz");
            dao.insertUnderCategory(undCat);
        }
    };


}
