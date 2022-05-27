package net.yolopix.moneyz

import android.content.Context
import androidx.room.Room
import net.yolopix.moneyz.model.AppDatabase

class DatabaseFactory {
    companion object {
        fun getDB(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "moneyz_db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}