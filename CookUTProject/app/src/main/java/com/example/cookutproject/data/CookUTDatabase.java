package com.example.cookutproject.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cookutproject.models.Aliment;
import com.example.cookutproject.models.Evenement;
import com.example.cookutproject.models.Facture;
import com.example.cookutproject.models.Materiel;
import com.example.cookutproject.models.NoteDeFrais;
import com.example.cookutproject.models.Semestre;
import com.example.cookutproject.models.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Aliment.class, Semestre.class, User.class, Materiel.class, Facture.class, NoteDeFrais.class, Evenement.class},version=2,exportSchema = false)
public abstract class CookUTDatabase extends RoomDatabase {
    //Daos
    public abstract CookUTDao cookUTDao();

    //Database
    public static volatile CookUTDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static  final ExecutorService databasWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static CookUTDatabase getDatabase(Context context){
        if(INSTANCE !=null){
            return INSTANCE;
        }
        synchronized (CookUTDatabase.class){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CookUTDatabase.class,
                    "CookooT")
                    .fallbackToDestructiveMigration()
                    .build();
            return INSTANCE;
        }
    }

}