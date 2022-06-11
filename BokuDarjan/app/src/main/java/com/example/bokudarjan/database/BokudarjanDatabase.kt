package com.example.bokudarjan.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bokudarjan.expense.ExpenseDAO;
import com.example.bokudarjan.expense.Expense;
import com.example.bokudarjan.category.CategoryDAO
import com.example.bokudarjan.category.Category
import com.example.bokudarjan.envelope.Envelope
import com.example.bokudarjan.envelope.EnvelopeDAO

import kotlin.jvm.Volatile;

//Build a singleton of the Database
@Database(version = 2, entities = [Expense::class, Category::class, Envelope::class], exportSchema = false)
abstract class BokudarjanDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDAO
    abstract fun categoryDao() : CategoryDAO
    abstract fun envelopeDao() : EnvelopeDAO

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
                    "bokudarkan_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}