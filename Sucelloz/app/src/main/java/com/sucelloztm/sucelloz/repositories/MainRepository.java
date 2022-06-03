package com.sucelloztm.sucelloz.repositories;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.sucelloztm.sucelloz.database.SucellozDatabase;

public class MainRepository {
    private SucellozDatabase database;
    private CategoriesRepository categoriesRepository;

    public void initDb() {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                SucellozDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    public void closeDb() {
        database.close();
    }

    public MainRepository(){
        initDb();
        categoriesRepository = new CategoriesRepository(this.database);
    }

    public CategoriesRepository getCategoriesRepository() {
        return categoriesRepository;
    }
}
