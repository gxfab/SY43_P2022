package com.example.econo_misons.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.econo_misons.database.dao.budgetDAO;
import com.example.econo_misons.database.dao.categoryDAO;
import com.example.econo_misons.database.dao.prevBudgetDAO;
import com.example.econo_misons.database.dao.userDAO;
import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.Budget_User;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.database.models.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Budget.class, Budget_User.class, Category.class, Envelope.class, PrevisionalBudget.class, Transaction.class}, version = 1)
public abstract class MoneyDB extends RoomDatabase {

    private static volatile MoneyDB INSTANCE;

    public abstract userDAO userdao();

    public abstract budgetDAO budgetdao();

    public abstract categoryDAO catdao();

    public abstract prevBudgetDAO prevdao();

    public static MoneyDB getInstance(Context context) {

        if (INSTANCE == null) {

            synchronized (MoneyDB.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),

                                    MoneyDB.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();

                }

            }

        }

        return INSTANCE;

    }

    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(() -> INSTANCE.userdao().newUser(new User("Suiram")));
                Executors.newSingleThreadExecutor().execute(() -> {
                    INSTANCE.budgetdao().addBudget(new Budget("Budget perso"));
                    INSTANCE.budgetdao().linkBudgetUser(new Budget_User(1,1));
                    INSTANCE.prevdao().addPrevBudget(new PrevisionalBudget(1,"2022-06"));
                    INSTANCE.catdao().addCategory(new Category("Courses"));
                    INSTANCE.catdao().addCategory(new Category("Loisirs"));
                    INSTANCE.prevdao().addEnvelope(new Envelope(1,"2022-06",1,100));
                    INSTANCE.prevdao().addEnvelope(new Envelope(1,"2022-06",2,50));
                });

            }
        };
    }
}
