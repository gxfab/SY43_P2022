package com.sucelloztm.sucelloz.database.DAO;

import static org.junit.Assert.*;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.database.DAO.CategoriesDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.Matchers.*;

@RunWith(AndroidJUnit4.class)
public class CategoriesDaoTest {

    // FOR DATA
    private SucellozDatabase database;
    private CategoriesDao categoriesDao;

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                SucellozDatabase.class)
                .build();
        categoriesDao=database.categoriesDao();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertCategory() throws Exception {
        Categories category = new Categories("test",true);
        categoriesDao.insertCategory(category);
        List<Categories> categories=categoriesDao.getCategories();
        assertEquals(categories.get(0).getName(),category.getName());
    }

    @Test
    public void insertCategories() {
        Categories category1 = new Categories("test1",true);
        Categories category2 = new Categories("test2",true);
        categoriesDao.insertCategories(category1,category2);
        List<Categories> categories=categoriesDao.getCategories();
        assertEquals(categories.get(0).getName(),category1.getName());
        assertEquals(categories.get(1).getName(),category2.getName());
    }

    @Test
    public void updateCategory() {
        Categories category = new Categories("test",true);
        long insertedId=categoriesDao.insertCategory(category);
        category.setId(insertedId);
        category.setReadOnly(false);
        categoriesDao.updateCategory(category);
        List<Categories> categories=categoriesDao.getCategories();
        assertFalse(categories.get(0).getReadOnly());
    }

    @Test
    public void updateCategories() {
        Categories category1 = new Categories("test",true);
        Categories category2 = new Categories("test",true);
        List<Long> insertedId=categoriesDao.insertCategories(category1,category2);
        category1.setId(insertedId.get(0));
        category1.setReadOnly(false);
        categoriesDao.updateCategory(category1);
        category2.setId(insertedId.get(1));
        category2.setReadOnly(false);
        categoriesDao.updateCategory(category2);
        List<Categories> categories=categoriesDao.getCategories();
        assertFalse(categories.get(0).getReadOnly());
        assertFalse(categories.get(1).getReadOnly());

    }

    @Test
    public void deleteCategory() {
        Categories category = new Categories("test",true);
        long insertedId=categoriesDao.insertCategory(category);
        categoriesDao.deleteCategory(insertedId);
        List<Categories> categories=categoriesDao.getCategories();
        assertThat(categories.isEmpty(),is(true));

    }

    @Test
    public void deleteCategories() {
        Categories category1 = new Categories("test",true);
        Categories category2 = new Categories("test",true);
        List<Long> insertedId=categoriesDao.insertCategories(category1,category2);
        categoriesDao.deleteCategories(insertedId.get(0),insertedId.get(1));
        List<Categories> categories=categoriesDao.getCategories();
        assertThat(categories.isEmpty(),is(true));
    }

    @Test
    public void getCategoriesWithSubCategories() {
    }
}