package com.sy43_p22.piggy_bank.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.sy43_p22.piggy_bank.database.dao.*
import com.sy43_p22.piggy_bank.database.entities.*

@Database(entities = [(Category::class), (SubCategory::class)], version = 2)
/**
 * @description Room Database to interface between the real Database & the APP
 */
abstract class PiggyBankDatabase : RoomDatabase(){
    // DAO initialization
    abstract fun piggyBankDAO(): PiggyBankDAO

    // Companion object for the DB
    companion object {
        // Init INSTANCE as null
        var INSTANCE: PiggyBankDatabase? = null

        // Called to negotiate with the DB
        fun getDatabase(context: Context): PiggyBankDatabase {
            Log.d("DATABASE", "Database requested")

            // if the instance has never been called (== null)
            // -> then build it
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    PiggyBankDatabase::class.java,
                    "PiggyBank.db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            // -> otherwise return the INSTANCE
            return INSTANCE!!
        }

    }


}