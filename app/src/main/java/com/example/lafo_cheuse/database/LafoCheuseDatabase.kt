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

/**
 * A Room database which will contain all the useful data of this application.
 *
 * It has a companion object which is a singleton object to avoid duplicating it
 *
 * @property categoryDao - DAO of the [Category] entity
 * @property expensesBudgetDao - DAO of the [ExpensesBudget] entity
 * @property incomesBudgetDao - DAO of the [IncomesBudget] entity
 * @property expenseDao - DAO of the [Expense] entity
 * @property incomeDao - DAO of the [Income] entity
 * @property optionDao - DAO of the [Option] entity
 * @property optionFieldDao - DAO of the [OptionField] entity
 */
@Database(entities = [Budget::class, Category::class, Income::class, Expense::class, Option::class, OptionField::class, ExpensesBudget::class, IncomesBudget::class ], version = 1, exportSchema = false)
abstract class LafoCheuseDatabase : RoomDatabase(){

    // --- DAO ---
    //abstract fun budgetDao(): BudgetDao?
    abstract fun categoryDao(): CategoryDao?
    @Deprecated("It is no longer useful")
    abstract fun expensesBudgetDao(): ExpensesBudgetDao?
    @Deprecated("It is no longer useful")
    abstract fun incomesBudgetDao(): IncomesBudgetDao?
    abstract fun expenseDao(): ExpenseDao?
    abstract fun incomeDao(): IncomeDao?
    abstract fun optionDao(): OptionDao?
    abstract fun optionFieldDao(): OptionFieldDao?

    companion object {
        // --- SINGLETON ---
        @Volatile
        private var INSTANCE: LafoCheuseDatabase? = null

        // --- INSTANCE ---
        /**
         * function to get the database instance
         *
         * @param context - [Context] context of the application
         * @return an instance of [LafoCheuseDatabase]
         */
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

        /**
         * A function to prepopulate the database. When the user download the application, these values will be automatically created.
         *
         * We create some elements :
         * - The default categories, which avoid the user to type all his categories
         * - The option that we just want to modify and not create inside the functioning of the application
         *
         * @return
         */
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
                        val optionTheme = Option("option_theme",OptionType.RADIOBUTTON)
                        val optionNotifications = Option("option_notifications",OptionType.CHECKBOX)
                        val optionNotificationsSum = Option("option_notification_sum",OptionType.TEXT_EDIT)
                        val optionBudget = Option("option_budget",OptionType.SPINNER)
                        val optionPopulate = Option("option_populate",OptionType.RADIOBUTTON)

                        INSTANCE!!.optionDao()?.insertOption(optionTheme)
                        INSTANCE!!.optionDao()?.insertOption(optionNotifications)
                        INSTANCE!!.optionDao()?.insertOption(optionNotificationsSum)
                        INSTANCE!!.optionDao()?.insertOption(optionBudget)
                        INSTANCE!!.optionDao()?.insertOption(optionPopulate)

                        INSTANCE!!.optionFieldDao()?.insertOptionField(
                            OptionField("light_theme",optionTheme,false)
                        )
                        INSTANCE!!.optionFieldDao()?.insertOptionField(
                            OptionField("dark_theme",optionTheme,false)
                        )
                        INSTANCE!!.optionFieldDao()?.insertOptionField(
                            OptionField("system_theme",optionTheme,true)
                        )

                        INSTANCE!!.optionFieldDao()?.insertOptionField(
                            OptionField("next_income_alert",optionNotifications,false)
                        )
                        INSTANCE!!.optionFieldDao()?.insertOptionField(
                            OptionField("below_sum_alert",optionNotifications,false)
                        )
                        INSTANCE!!.optionFieldDao()?.insertOptionField(
                            OptionField("sum_alert",optionNotificationsSum,false)
                        )

                        INSTANCE!!.optionFieldDao()?.insertOptionField(
                            OptionField(1.toString(),optionBudget,false)
                        )

                        INSTANCE!!.optionFieldDao()?.insertOptionField(
                            OptionField("isPopulated",optionPopulate,false)
                        )
                    }
                }
            }
        }
    }
}