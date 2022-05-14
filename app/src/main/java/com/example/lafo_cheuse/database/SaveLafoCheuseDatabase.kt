package com.example.lafo_cheuse.database

import android.content.ClipData.Item
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lafo_cheuse.database.dao.BudgetDao
import com.example.lafo_cheuse.models.Budget
import java.util.concurrent.Executors

@Database(entities = [Budget::class], version = 1, exportSchema = false)
abstract class SaveLafoCheuseDatabase : RoomDatabase(){

    // --- DAO ---
    abstract fun budgetDao(): BudgetDao?

    companion object {
        // --- SINGLETON ---
        @Volatile
        private var INSTANCE: SaveLafoCheuseDatabase? = null

        // --- INSTANCE ---
        fun getInstance(context: Context): SaveLafoCheuseDatabase? {
            if (INSTANCE == null) {
                synchronized(SaveLafoCheuseDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            SaveLafoCheuseDatabase::class.java, "LafoCheuseDatabase.db"
                        )
                            .addCallback(prepopulateDatabase())
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        private fun prepopulateDatabase(): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadExecutor().execute {
                        /*
                        INSTANCE!!.budgetDao().createBudget(
                            User(
                                1,
                                "Philippe",
                                "https://oc-user.imgix.net/users/avatars/15175844164713_frame_523.jpg?auto=compress,format&q=80&h=100&dpr=2"
                            )
                        )
                         */
                    }
                }
            }
        }
    }
}