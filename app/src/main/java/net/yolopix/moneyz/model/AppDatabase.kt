package net.yolopix.moneyz.model

import androidx.room.Database
import androidx.room.RoomDatabase
import net.yolopix.moneyz.model.entities.Account
import net.yolopix.moneyz.model.entities.AccountDao
import net.yolopix.moneyz.model.entities.Category
import net.yolopix.moneyz.model.entities.CategoryDao

@Database(entities = [Account::class, Category::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao() : CategoryDao
}