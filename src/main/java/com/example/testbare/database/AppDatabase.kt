package com.example.testbare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testbare.database.dao.CategorieDao
import com.example.testbare.database.dao.DepenseDao
import com.example.testbare.database.entities.Categorie
import com.example.testbare.database.entities.Depense

@Database(
    entities = [
        Depense::class,
        Categorie::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CategorieDao() : CategorieDao
    abstract fun DepenseDao() : DepenseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            context.deleteDatabase("app_database4")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}