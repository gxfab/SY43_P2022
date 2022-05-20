package com.sucelloztm.sucelloz.database.DAO;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.SubCategories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SubCategoriesDaoTest {

    // FOR DATA
    private SucellozDatabase database;
    private CategoriesDao categoriesDao;
    private SubCategoriesDao subCategoriesDao;

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                SucellozDatabase.class)
                .build();
        subCategoriesDao = database.subCategoriesDao();
        categoriesDao = database.categoriesDao();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertSubCategory() throws Exception {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        subCategoriesDao.insertSubCategory(subCategory);
        List<SubCategories> subCategories = subCategoriesDao.getSubCategories();
        assertEquals(subCategories.get(0).getName(), subCategory.getName());
    }

    @Test
    public void insertSubCategories() throws Exception {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory1 = new SubCategories("test", categoriesId);
        SubCategories subCategory2 = new SubCategories("test", categoriesId);
        subCategoriesDao.insertSubCategories(subCategory1, subCategory2);
        List<SubCategories> subCategories = subCategoriesDao.getSubCategories();
        assertEquals(subCategories.get(0).getName(), subCategory1.getName());
        assertEquals(subCategories.get(1).getName(), subCategory2.getName());
    }

    @Test
    public void updateSubCategory() throws Exception {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long insertedId = subCategoriesDao.insertSubCategory(subCategory);
        List<SubCategories> subCategories = subCategoriesDao.getSubCategories();
        subCategory.setId(insertedId);
        subCategory.setName("testName");
        subCategoriesDao.updateSubCategory(subCategory);
        subCategories = subCategoriesDao.getSubCategories();
        assertEquals(subCategories.get(0).getName(), subCategory.getName());
    }

    @Test
    public void updateSubCategories() throws Exception {
        Categories category1 = new Categories("test1", true);
        Categories category2 = new Categories("test2", true);
        List<Long> categoriesId = categoriesDao.insertCategories(category1, category2);
        SubCategories subCategory1 = new SubCategories("test", categoriesId.get(0));
        SubCategories subCategory2 = new SubCategories("test", categoriesId.get(1));
        List<Long> insertedId = subCategoriesDao.insertSubCategories(subCategory1, subCategory2);
        List<SubCategories> subCategories = subCategoriesDao.getSubCategories();
        subCategory1.setId(insertedId.get(0));
        subCategory1.setName("testName");
        subCategory2.setId(insertedId.get(1));
        subCategory2.setName("testName");
        subCategoriesDao.updateSubCategories(subCategory1, subCategory2);
        subCategories = subCategoriesDao.getSubCategories();
        assertEquals(subCategories.get(0).getName(), subCategory1.getName());
        assertEquals(subCategories.get(1).getName(), subCategory2.getName());
    }

    @Test
    public void deleteSubCategory() throws Exception {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long insertedId = subCategoriesDao.insertSubCategory(subCategory);
        subCategory.setId(insertedId);
        subCategoriesDao.deleteSubCategory(subCategory);
        List<SubCategories> subCategories = subCategoriesDao.getSubCategories();
        assertThat(subCategories.isEmpty(), is(true));

    }

    @Test
    public void deleteSubCategories() throws Exception {
        Categories category1 = new Categories("test1", true);
        Categories category2 = new Categories("test2", true);
        List<Long> categoriesId = categoriesDao.insertCategories(category1, category2);
        SubCategories subCategory1 = new SubCategories("test", categoriesId.get(0));
        SubCategories subCategory2 = new SubCategories("test", categoriesId.get(1));
        List<Long> insertedId = subCategoriesDao.insertSubCategories(subCategory1, subCategory2);
        subCategory1.setId(insertedId.get(0));
        subCategory2.setId(insertedId.get(1));
        subCategoriesDao.deleteSubCategories(subCategory1, subCategory2);
        List<SubCategories> subCategories = subCategoriesDao.getSubCategories();
        assertThat(subCategories.isEmpty(), is(true));
    }

    @Test
    public void getSubCategories() {
    }

    @Test
    public void getSubCategoriesWithStableExpensesAndIncome() {
    }

    @Test
    public void getSubCategoriesWithInfrequentExpensesAndIncome() {
    }
}