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
import com.example.nomoola.database.dao.ProfileDAO;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.database.entity.Profile;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, SubCategory.class, InOutCome.class, Profile.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class NomoolaRoomDataBase extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();
    public abstract SubCategoryDAO subCategoryDAO();
    public abstract InOutComeDAO inOutComeDAO();
    public abstract ProfileDAO profileDAO();

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
                            .allowMainThreadQueries()
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
                populateProfile();
            });
        }

        private void populateCategory(){
            CategoryDAO dao = INSTANCE.categoryDAO();

            dao.insertCategory(new Category("FOOD", 150, Category.CategoryType.OUTCOME, LocalDate.now()));
            dao.insertCategory(new Category("CAR", 250, Category.CategoryType.OUTCOME, LocalDate.now()));
            dao.insertCategory(new Category("SUBSCRIPTION", 50, Category.CategoryType.OUTCOME, LocalDate.now()));

            dao.insertCategory(new Category("SALARY", 2000, Category.CategoryType.INCOME, LocalDate.now()));
            dao.insertCategory(new Category("INVESTS", 500, Category.CategoryType.INCOME, LocalDate.now()));

            dao.insertCategory(new Category("HOUSE", 250000, Category.CategoryType.PROJECT, LocalDate.now()));
            dao.insertCategory(new Category("NEW CAR", 10000, Category.CategoryType.PROJECT, LocalDate.now()));
        }

        private void populateSubCategory(){
            SubCategoryDAO dao = INSTANCE.subCategoryDAO();

            SubCategory undCat = new SubCategory(1, "GROCERIES");
            dao.insertSubCategory(undCat);
            undCat = new SubCategory(1, "RESTAURANTS");
            dao.insertSubCategory(undCat);

            undCat = new SubCategory(2, "REPARATION");
            dao.insertSubCategory(undCat);
            undCat = new SubCategory(2, "GAZ");
            dao.insertSubCategory(undCat);
        }

        private void populateInOutCome(){
            InOutComeDAO dao = INSTANCE.inOutComeDAO();

            InOutCome come = new InOutCome("week grocery at SuperU", 1, 1, 56.89, LocalDate.now());
            dao.insertInOutCome(come);
            come = new InOutCome("BK with my friends", 1, 2, 13.50, LocalDate.now());
            dao.insertInOutCome(come);
            come = new InOutCome("motor reparation", 2, 3, 207.90, LocalDate.now());
            dao.insertInOutCome(come);
            come = new InOutCome("weekly fuel", 2, 4, 60.00, LocalDate.now());
            dao.insertInOutCome(come);

        }

        private void populateProfile(){
            ProfileDAO dao = INSTANCE.profileDAO();

            Profile profile = new Profile(1, "John", Profile.userLanguage.ENGLISH, Profile.userCurrency.USDOLLAR);
            dao.insertProfile(profile);
        }
    };


}
