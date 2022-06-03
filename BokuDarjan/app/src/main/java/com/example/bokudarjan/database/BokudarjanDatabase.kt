package com.example.bokudarjan.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.bokudarjan.dao.ExpenseDAO;
import com.example.bokudarjan.data.Expense;
import androidx.room.RoomDatabase;

import kotlin.jvm.Volatile;

//Build a singleton of the Database
@Database(version = 1, entities = [Expense::class], exportSchema = false)
abstract class BokudarjanDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDAO

    companion object {
        @Volatile
        private var INSTANCE: BokudarjanDatabase? = null

        fun getDatabase(context: Context): BokudarjanDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BokudarjanDatabase::class.java,
                    "expense_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}