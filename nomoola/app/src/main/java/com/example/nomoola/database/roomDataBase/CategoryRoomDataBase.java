package com.example.nomoola.database.roomDataBase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.nomoola.database.dao.CategoryDAO;
import com.example.nomoola.database.entity.Category;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class}, version = 1, exportSchema = false)
public abstract class CategoryRoomDataBase extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();

    private static volatile CategoryRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CategoryRoomDataBase getDatabase(final Context context) {
        Log.d("CREATION", "getting database");
        if (INSTANCE == null) {
            synchronized (CategoryRoomDataBase.class) {
                if (INSTANCE == null) {
                    Log.d("CREATION", "building database");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CategoryRoomDataBase.class, "DB_CATEGORY")
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
            });
        }

        private void populateCategory(){
            CategoryDAO dao = INSTANCE.categoryDAO();

            Category category = new Category("Food");
            dao.insertCategory(category);
            category = new Category("Car");
            dao.insertCategory(category);
        }
    };


}
