package net.yolopix.moneyz

import android.content.Context
import androidx.room.Room
import net.yolopix.moneyz.model.AppDatabase

/**
 * This class contains static methods to quickly instanciate an AppDatabase object
 */
class DatabaseFactory {
    companion object {
        /**
         * Instanciate a new database for the app
         * @param context The context of the activity
         * @return The database object
         */
        fun getDB(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "moneyz_db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}