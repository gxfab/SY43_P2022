package com.example.testbare.database

import android.content.Context
import androidx.room.*
import com.example.testbare.database.dao.*
import com.example.testbare.database.entities.*

@Database(
    entities = [
        Budget::class,
        Depense::class,
        Categorie::class,
        Mois::class,
        Revenu::class,
        Utilisateur::class
    ],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CategorieDao() : CategorieDao
    abstract fun DepenseDao() : DepenseDao
    abstract fun MoisDao() : MoisDao
    abstract fun BudgetDao(): BudgetDao
    abstract fun RevenuDao(): RevenuDao
    abstract fun UtilisateurDao(): UtilisateurDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            //context.deleteDatabase("app_database")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}