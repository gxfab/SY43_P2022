package net.yolopix.moneyz.model

import androidx.room.Database
import androidx.room.RoomDatabase
import net.yolopix.moneyz.model.dao.AccountDao
import net.yolopix.moneyz.model.dao.CategoryDao
import net.yolopix.moneyz.model.dao.ExpenseDao
import net.yolopix.moneyz.model.dao.MonthDao
import net.yolopix.moneyz.model.entities.Account
import net.yolopix.moneyz.model.entities.Category
import net.yolopix.moneyz.model.entities.Expense
import net.yolopix.moneyz.model.entities.Month

/**
 * This class is an Android Jetpack Room database.
 * It stores all entities necessary for the application
 */
@Database(
    entities = [Account::class, Category::class, Expense::class, Month::class],
    version = 16,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun monthDao(): MonthDao
}