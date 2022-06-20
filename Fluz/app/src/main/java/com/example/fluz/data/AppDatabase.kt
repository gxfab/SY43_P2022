package com.example.fluz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fluz.data.dao.*
import com.example.fluz.data.entities.*

/**
 * Represents an instance of the database
 *
 */
@Database(
    entities = [Budget::class, BudgetItem::class, Category::class, SubCategory::class, Transaction::class, User::class, UserCategory::class],
    version = 6
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao
    abstract fun BudgetDao(): BudgetDao
    abstract fun BudgetItemDao(): BudgetItemDao
    abstract fun CategoryDao(): CategoryDao
    abstract fun SubCategoryDao(): SubCategoryDao
    abstract fun TransactionDao(): TransactionDao
    abstract fun UserCategoryDao(): UserCategoryDao

    /**
     * Singleton to only get one instance of the database
     */
    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "todo-list.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}