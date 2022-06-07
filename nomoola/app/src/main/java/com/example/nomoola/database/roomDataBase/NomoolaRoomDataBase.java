package com.example.nomoola.database.roomDataBase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.nomoola.database.converter.Converters;
import com.example.nomoola.database.dao.CategoryDAO;
import com.example.nomoola.database.dao.InOutComeDAO;
import com.example.nomoola.database.dao.SubCategoryDAO;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.database.entity.SubCategory;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, SubCategory.class, InOutCome.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class NomoolaRoomDataBase extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();
    public abstract SubCategoryDAO subCategoryDAO();
    public abstract InOutComeDAO inOutComeDAO();

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
                populateSubCategory();
                populateInOutCome();
            });
        }

        private void populateCategory(){
            CategoryDAO dao = INSTANCE.categoryDAO();

            dao.insertCategory(new Category("Food", 150));
            dao.insertCategory(new Category("Car", 250));
            dao.insertCategory(new Category("Health", 50));
            dao.insertCategory(new Category("Hobby", 50));
            dao.insertCategory(new Category("Party", 100));
            dao.insertCategory(new Category("Subscription", 35));
            dao.insertCategory(new Category("School", 20));
        }

        private void populateSubCategory(){
            SubCategoryDAO dao = INSTANCE.subCategoryDAO();

            SubCategory undCat = new SubCategory(1, "Groceries");
            dao.insertSubCategory(undCat);
            undCat = new SubCategory(1, "Restaurants");
            dao.insertSubCategory(undCat);

            undCat = new SubCategory(2, "Reparations");
            dao.insertSubCategory(undCat);
            undCat = new SubCategory(2, "Gaz");
            dao.insertSubCategory(undCat);
        }

        private void populateInOutCome(){
            InOutComeDAO dao = INSTANCE.inOutComeDAO();

            InOutCome come = new InOutCome("week grocery at SuperU", "Food", "Groceries", 56.89, LocalDate.now());
            dao.insertInOutCome(come);
            come = new InOutCome("BK with my friends", "Food", "Restaurants", 13.50, LocalDate.now());
            dao.insertInOutCome(come);
            come = new InOutCome("motor reparation", "Car", "Reparations", 207.90, LocalDate.now());
            dao.insertInOutCome(come);
            come = new InOutCome("weekly fuel", "Car", "Gaz", 60.00, LocalDate.now());
            dao.insertInOutCome(come);

        }
    };


}
