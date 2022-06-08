package com.example.lafo_cheuse.database

import android.content.ClipData.Item
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lafo_cheuse.database.dao.*
import com.example.lafo_cheuse.models.*
import java.util.concurrent.Executors

@Database(entities = [Budget::class, Category::class, Income::class, Expense::class, Option::class, OptionField::class, ExpensesBudget::class, IncomesBudget::class ], version = 1, exportSchema = false)
abstract class LafoCheuseDatabase : RoomDatabase(){

    // --- DAO ---
    //abstract fun budgetDao(): BudgetDao?
    abstract fun categoryDao(): CategoryDao?
    abstract fun expensesBudgetDao(): ExpensesBudgetDao?
    abstract fun incomesBudgetDao(): IncomesBudgetDao?
    //abstract fun expenseDao(): ExpenseDao?
    abstract fun incomeDao(): IncomeDao?
    //abstract fun optionDao(): OptionDao?
    //abstract fun optionFieldDao(): OptionFieldDao?

    companion object {
        // --- SINGLETON ---
        @Volatile
        private var INSTANCE: LafoCheuseDatabase? = null

        // --- INSTANCE ---
        fun getInstance(context: Context): LafoCheuseDatabase? {
            if (INSTANCE == null) {
                synchronized(LafoCheuseDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            LafoCheuseDatabase::class.java, "LafoCheuseDatabase.db"
                        )
                            .addCallback(prepopulateDatabase())
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        private fun prepopulateDatabase(): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadExecutor().execute {
                        INSTANCE!!.categoryDao()?.createCategory(
                            Category("extras","❔")
                        )
                        INSTANCE!!.categoryDao()?.createCategory(
                            Category("Courses","\uD83D\uDED2")
                        )
                        INSTANCE!!.categoryDao()?.createCategory(
                            Category("Bourses","\uD83D\uDCB0")
                        )
                    }
                }
            }
        }
    }
}