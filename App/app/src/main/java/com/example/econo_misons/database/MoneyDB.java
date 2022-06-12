package com.example.econo_misons.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.econo_misons.database.dao.DAO;
import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.Budget_User;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.database.models.User;

@Database(entities = {User.class, Budget.class, Budget_User.class, Category.class, Envelope.class, PrevisionalBudget.class, Transaction.class}, version = 1)
public abstract class MoneyDB extends RoomDatabase {

    private static volatile MoneyDB INSTANCE;

    public abstract DAO dao();

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
