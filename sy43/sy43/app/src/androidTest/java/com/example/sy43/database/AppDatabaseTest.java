package com.example.sy43.database;

import static org.junit.Assert.*;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AppDatabaseTest {
    private static AppDatabase instance;
    private CategoryDao categoryDao;
    private SubCategoryDao subcategoryDao;

    @Before
    public void setup(){
        this.instance = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),AppDatabase.class).build();
        categoryDao = instance.categoryDao();
        subcategoryDao = instance.subCategoryDao();
    }

    @After
    public void close(){
        instance.close();
    }

    @Test
    public void testSubCategory() {
        Category cat1 = new Category("azerty");
        Category cat2 = new Category("qsdfgh");
        categoryDao.insertAll(cat1,cat2);
        int id = categoryDao.getAll().get(0).id;
        SubCategory subcat = new SubCategory("souscat",100.00,id);
        subcategoryDao.insertAll(subcat);
        List<SubCategory> list = subcategoryDao.findByCategory(id);
        assertEquals("souscat",list.get(0).name);
    }

}
