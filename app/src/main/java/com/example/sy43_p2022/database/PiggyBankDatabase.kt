package com.example.sy43_p2022.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sy43_p2022.database.dao.*
import com.example.sy43_p2022.database.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [(Category::class), (SubCategory::class)], version = 1)
abstract class PiggyBankDatabase : RoomDatabase(){
    // DAO initialization
    abstract fun categoryDAO(): CategoryDAO
    abstract fun subcategoryDAO(): SubCategoryDAO

    private class DatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Log.d("DATABASE", "Populate create")
            INSTANCE?.let { database ->
                scope.launch {
                    Log.d("DATABASE", "Populate starts")

                    val categoryDAO = database.categoryDAO()
                    val subCategoryDAO = database.subcategoryDAO()

                    categoryDAO.nukeTable()
                    subCategoryDAO.nukeTable()

                    val mainCategories = arrayOf("Spending", "Saving Goal")
                    val sub1Categories = arrayOf("Food", "Services", "Car", "Lodging", "Social Security", "Health", "Hobbies", "Personal", "Various", "Savings", "Events", "Holidays", "Debts")
                    val sub2Categories = arrayOf(
                        arrayOf("Grocery Shopping", "Restaurants", "Bars", "Uber Eats/Deliveroo"),
                        arrayOf("Mobile Phone", "Internet", "Streaming"),
                        arrayOf("Insurance", "Gasoline", "Reparations", "Parking"),
                        arrayOf("Rent", "Insurance", "Electricity", "Local Taxes"),
                        arrayOf("Life Insurance", "Disability Insurance", "Retirement", "Pension"),
                        arrayOf("Doctor", "Drugstore"),
                        arrayOf("Sports", "Movie Theater"),
                        arrayOf("Books", "Video Games"),
                        arrayOf("Unexpected Expenses"),
                        arrayOf("Christmas", "Gifts"),
                        arrayOf("Birthday", "Parties"),
                        arrayOf("Travel", "Week-ends"),
                        arrayOf("Loans")
                    )

                    // spending & saving goals categories
                    for (mainName in mainCategories) {
                        val mainCategory = Category(mainName)
                        categoryDAO.insertCategory(mainCategory)

                        // categories
                        for ((index, sub1CategoryName) in sub1Categories.withIndex()) {
                            val sub1Category = SubCategory(sub1CategoryName, mainCategory.id)
                            subCategoryDAO.insertSubCategory(sub1Category)

                            // sub-categories
                            for (sub2CategoryName in sub2Categories[index]) {
                                val sub2Category = SubCategory(sub2CategoryName, sub1Category.id)
                                subCategoryDAO.insertSubCategory(sub2Category)
                            }
                        }
                    }

                    Log.d("DATABASE", "Populate ended")
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PiggyBankDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PiggyBankDatabase {
            Log.d("DATABASE", "Database requested")

            // if the INSTANCE is not null, return it
            // if it is, instantiate the database
            return INSTANCE ?: synchronized(this) {
                Log.d("DATABASE", "Database first request")

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PiggyBankDatabase::class.java,
                    "piggy_bank_database_22" // modify this line to force regenerate the db on launch
                )
                    .fallbackToDestructiveMigration()
                    .build()

                DatabaseCallback(scope)

                INSTANCE = instance
                return instance
            }
        }
    }
}