package com.example.sy43_p2022.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.example.sy43_p2022.database.dao.*
import com.example.sy43_p2022.database.entities.*

@Database(entities = [(Balance::class), (Category::class), (SubCategory::class)], version = 7)

abstract class PiggyBankRoomDatabase : RoomDatabase(){

    //DAO initialization
    abstract fun balanceDAO(): BalanceDAO
    abstract fun categoryDAO(): CategoryDAO
    abstract fun subcategoryDAO(): SubCategoryDAO

    companion object {
        private var INSTANCE: PiggyBankRoomDatabase? = null

        internal fun getDatabase(context: Context): PiggyBankRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(PiggyBankRoomDatabase::class.java){
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<PiggyBankRoomDatabase>(context.applicationContext,
                            PiggyBankRoomDatabase::class.java, "piggybankroomdatabase").allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}