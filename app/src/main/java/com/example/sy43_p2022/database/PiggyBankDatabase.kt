package com.example.sy43_p2022.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteCompat
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sy43_p2022.database.dao.*
import com.example.sy43_p2022.database.entities.*

@Database(entities = [(Category::class), (SubCategory::class)], version = 1)
abstract class PiggyBankDatabase : RoomDatabase(){
    // DAO initialization
    abstract fun piggybankDAO(): PiggyBankDAO

    companion object {
        var INSTANCE: PiggyBankDatabase? = null
        fun getDatabase(context: Context): PiggyBankDatabase {
            Log.d("DATABASE", "Database requested")
            if(INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    PiggyBankDatabase::class.java,
                    "PiggyBank.db" // modify this line to force regenerate the db on launch
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE!!
        }
    }
}