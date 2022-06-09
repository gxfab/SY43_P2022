package fr.sy.lebudgetduzero.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.sy.lebudgetduzero.item.IncomeItem
import fr.sy.lebudgetduzero.database.dao.*
import fr.sy.lebudgetduzero.item.SpentItem
import fr.sy.lebudgetduzero.item.TypeItem
import kotlinx.coroutines.CoroutineScope

/**
 * App database to manage local database
 *
 * @constructor Create empty App database
 */
@Database(entities = [IncomeItem::class, SpentItem::class, TypeItem::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase(){
    abstract fun incomeDao(): IncomeDao
    abstract fun spentDao(): SpentDao
    abstract fun balanceDao(): BalanceDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Function which permite to get the database to do some request
         *
         * @param context
         * @return
         */
        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}