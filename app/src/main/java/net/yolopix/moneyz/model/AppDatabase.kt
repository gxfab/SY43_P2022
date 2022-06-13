package net.yolopix.moneyz.model

import androidx.room.Database
import androidx.room.RoomDatabase
import net.yolopix.moneyz.model.dao.*
import net.yolopix.moneyz.model.entities.*

/**
 * This class is an Android Jetpack Room database.
 * It stores all entities necessary for the application
 */
@Database(
    entities = [Account::class, Category::class, Expense::class, Month::class],
    version = 15,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun monthDao(): MonthDao
}