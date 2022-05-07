package net.yolopix.moneyz.model

import androidx.room.*
import net.yolopix.moneyz.model.database.*

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}