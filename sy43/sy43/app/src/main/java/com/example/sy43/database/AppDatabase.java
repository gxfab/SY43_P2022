package com.example.sy43.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Category.class,SubCategory.class, ExtraDebts.class,MonthlyRevenue.class,Expenses.class,Projects.class,Bills.class,Income.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CategoryDao categoryDao();
    public abstract SubCategoryDao subCategoryDao();
    public abstract ExtraDebtsDao extraDebtDao();
    public abstract MonthlyRevenueDao monthlyRevenueDao();
    public abstract ExpensesDao expensesDao();
    public abstract ProjectsDao projectDao();
    public abstract BillsDao billsDao();
    public abstract IncomeDao incomeDao();

    private static AppDatabase instance;
//    private static final int NUMBER_OF_THREADS = 4;
//    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Padnom").allowMainThreadQueries().build();
        }
        return instance;
    }


    //AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "Padnom").build();

    //CategoryDao categoryDao = db.categoryDao();
}
