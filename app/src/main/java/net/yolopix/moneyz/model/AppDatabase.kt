package net.yolopix.moneyz.model

import androidx.room.*
import net.yolopix.moneyz.model.entities.*

@Database(entities = [Account::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}