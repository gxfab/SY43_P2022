package com.sucelloztm.sucelloz.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sucelloztm.sucelloz.database.DAO.CategoriesDao;
import com.sucelloztm.sucelloz.database.DAO.InfrequentExpensesAndIncomeDao;
import com.sucelloztm.sucelloz.database.DAO.SavingsDao;
import com.sucelloztm.sucelloz.database.DAO.StableExpensesAndIncomeDao;
import com.sucelloztm.sucelloz.database.DAO.SubCategoriesDao;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.Savings;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentSum;


@Database(entities = {Categories.class,
        SubCategories.class,
        InfrequentExpensesAndIncome.class,
        Savings.class,
        StableExpensesAndIncome.class,
        SubCategoriesWithInfrequentSum.class
},
        version = 1
)
/**
 * Abstract Class of the Database following the singleton pattern
 */
public abstract class SucellozDatabase extends RoomDatabase {
    //SINGLETON https://en.wikipedia.org/wiki/Singleton_pattern because our application works only on one process
    private static volatile SucellozDatabase INSTANCE;

    //DAO
    public abstract CategoriesDao categoriesDao();

    public abstract SubCategoriesDao subCategoriesDao();

    public abstract InfrequentExpensesAndIncomeDao infrequentExpensesAndIncomeDao();

    public abstract StableExpensesAndIncomeDao stableExpensesAndIncomeDao();

    public abstract SavingsDao savingsDao();

    //INSTANCE

    /**
     * Get an instance of the current Database
     *
     * @param context the current context in which the method is called
     * @return an instance of the current Database
     */
    public static SucellozDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SucellozDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SucellozDatabase.class, "SucellozDatabase.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
