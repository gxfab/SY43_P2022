package com.example.econo_misons.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Budget.class, Budget_User.class, Category.class, Envelope.class, PrevisionalBudget.class, Transaction.class}, version = 1)
public abstract class MoneyDB extends RoomDatabase {

    private static volatile MoneyDB INSTANCE;

    public abstract  DAO dao();

    public static MoneyDB getInstance(Context context) {

        if (INSTANCE == null) {

            synchronized (MoneyDB.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),

                                    MoneyDB.class, "MyDatabase.db")

                            .build();

                }

            }

        }

        return INSTANCE;

    }
}
