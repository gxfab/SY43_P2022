package com.example.sy43_p2022.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sy43_p2022.database.dao.*
import com.example.sy43_p2022.database.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [(Balance::class), (Category::class), (SubCategory::class)], version = 7)
abstract class PiggyBankDatabase : RoomDatabase(){

    //DAO initialization
    abstract fun balanceDAO(): BalanceDAO
    abstract fun categoryDAO(): CategoryDAO
    abstract fun subcategoryDAO(): SubCategoryDAO

    companion object {
        private var INSTANCE: PiggyBankDatabase? = null

        internal fun getDatabase(context: Context, scope: CoroutineScope): PiggyBankDatabase? {
            if (INSTANCE == null) {
                synchronized(PiggyBankDatabase::class.java){
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<PiggyBankDatabase>(context.applicationContext,
                            PiggyBankDatabase::class.java, "piggybankroomdatabase").allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(PiggyBankDatabaseCallback(scope))
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

    private class PiggyBankDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.categoryDAO(), database.subcategoryDAO())
                }
            }
        }

        suspend fun populateDatabase(categoryDao: CategoryDAO, subCategoryDao: SubCategoryDAO) {
            // Delete all content here
            categoryDao.nukeTable()

            val spendingCategory = Category("Spending")
            val savingCategory = Category("Saving Goals")

            val categoriesNames = arrayOf("Food", "Services", "Car", "Lodging", "Social Security", "Health", "Hobbies", "Personal", "Various", "Savings", "Events", "Holidays", "Debts")
            val subCategoriesNames = arrayOf(
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

            // for all categories
            for (cat in categoriesNames) {
                val categorySP = SubCategory(cat, spendingCategory.id)
                val categorySA = SubCategory(cat, savingCategory.id)

                subCategoryDao.insertSubCategory(categorySP)
                subCategoryDao.insertSubCategory(categorySA)

                // for all sub-categories
                for (subs in subCategoriesNames) {
                    for (sub in subs) {
                        subCategoryDao.insertSubCategory(SubCategory(sub, categorySP.id))
                        subCategoryDao.insertSubCategory(SubCategory(sub, categorySA.id))
                    }
                }
            }

            categoryDao.insertCategory(spendingCategory);
            categoryDao.insertCategory(savingCategory);
        }
    }
}