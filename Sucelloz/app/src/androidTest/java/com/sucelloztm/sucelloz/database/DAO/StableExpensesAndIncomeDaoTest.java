package com.sucelloztm.sucelloz.database.DAO;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class StableExpensesAndIncomeDaoTest {

    // FOR DATA
    private SucellozDatabase database;
    private StableExpensesAndIncomeDao stableExpensesAndIncomeDao;
    private CategoriesDao categoriesDao;
    private SubCategoriesDao subCategoriesDao;

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                SucellozDatabase.class)
                .build();
        stableExpensesAndIncomeDao = database.stableExpensesAndIncomeDao();
        subCategoriesDao = database.subCategoriesDao();
        categoriesDao = database.categoriesDao();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertStableExpenseOrIncome() throws Exception {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        StableExpensesAndIncome stable = new StableExpensesAndIncome("test", 20, '+', 1990, 2, subCategoriesId);
        stableExpensesAndIncomeDao.insertStableExpenseOrIncome(stable);
        List<StableExpensesAndIncome> stables = stableExpensesAndIncomeDao.getStable();

        assertEquals(stables.get(0).getName(), stable.getName());
        assertEquals(stables.get(0).getAmount(), stable.getAmount());
        assertEquals(stables.get(0).getSign(), stable.getSign());
        assertEquals(stables.get(0).getDate(), stable.getDate());
        assertEquals(stables.get(0).getFrequency(), stable.getFrequency());
        assertEquals(stables.get(0).getSubCategoriesId(), stable.getSubCategoriesId());
    }

    @Test
    public void insertStableExpensesAndIncome() throws Exception {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory1 = new SubCategories("test1", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory1);
        SubCategories subCategory2 = new SubCategories("test2", categoriesId);
        long subCategoriesId2 = subCategoriesDao.insertSubCategory(subCategory2);
        StableExpensesAndIncome stable1 = new StableExpensesAndIncome("test1", 20, '+', 1990, 2, subCategoriesId);
        StableExpensesAndIncome stable2 = new StableExpensesAndIncome("test2", 2, '-', 1999, 22, subCategoriesId2);
        stableExpensesAndIncomeDao.insertStableExpensesAndIncome(stable1, stable2);
        List<StableExpensesAndIncome> stables = stableExpensesAndIncomeDao.getStable();

        assertEquals(stables.get(0).getName(), stable1.getName());
        assertEquals(stables.get(0).getAmount(), stable1.getAmount());
        assertEquals(stables.get(0).getSign(), stable1.getSign());
        assertEquals(stables.get(0).getDate(), stable1.getDate());
        assertEquals(stables.get(0).getFrequency(), stable1.getFrequency());
        assertEquals(stables.get(0).getSubCategoriesId(), stable1.getSubCategoriesId());

        assertEquals(stables.get(1).getName(), stable2.getName());
        assertEquals(stables.get(1).getAmount(), stable2.getAmount());
        assertEquals(stables.get(1).getSign(), stable2.getSign());
        assertEquals(stables.get(1).getDate(), stable2.getDate());
        assertEquals(stables.get(1).getFrequency(), stable2.getFrequency());
        assertEquals(stables.get(1).getSubCategoriesId(), stable2.getSubCategoriesId());
    }

    @Test
    public void updateStableExpenseOrIncome() {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        SubCategories subCategory2 = new SubCategories("test2", categoriesId);
        long subCategoriesId2 = subCategoriesDao.insertSubCategory(subCategory2);
        StableExpensesAndIncome stable = new StableExpensesAndIncome("test", 20, '+', 1990, 2, subCategoriesId);
        long insertedID = stableExpensesAndIncomeDao.insertStableExpenseOrIncome(stable);
        stable.setId(insertedID);
        stable.setAmount(3);
        stable.setDate(1880);
        stable.setFrequency(6);
        stable.setName("hello");
        stable.setSign('-');
        stable.setSubCategoriesId(subCategoriesId2);
        stableExpensesAndIncomeDao.updateStableExpenseOrIncome(stable);
        List<StableExpensesAndIncome> stables = stableExpensesAndIncomeDao.getStable();

        assertEquals(stables.get(0).getName(), stable.getName());
        assertEquals(stables.get(0).getAmount(), stable.getAmount());
        assertEquals(stables.get(0).getSign(), stable.getSign());
        assertEquals(stables.get(0).getDate(), stable.getDate());
        assertEquals(stables.get(0).getFrequency(), stable.getFrequency());
        assertEquals(stables.get(0).getSubCategoriesId(), stable.getSubCategoriesId());
    }

    @Test
    public void updateStableExpensesAndIncome() {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        SubCategories subCategory2 = new SubCategories("test2", categoriesId);
        long subCategoriesId2 = subCategoriesDao.insertSubCategory(subCategory2);
        StableExpensesAndIncome stable = new StableExpensesAndIncome("test", 20, '+', 1990, 2, subCategoriesId);
        SubCategories subCategory3 = new SubCategories("test3", categoriesId);
        long subCategoriesId3 = subCategoriesDao.insertSubCategory(subCategory3);
        SubCategories subCategory4 = new SubCategories("test4", categoriesId);
        long subCategoriesId4 = subCategoriesDao.insertSubCategory(subCategory4);
        StableExpensesAndIncome stable2 = new StableExpensesAndIncome("test5", 2, '-', 199, 25, subCategoriesId3);

        List<Long> insertedID = stableExpensesAndIncomeDao.insertStableExpensesAndIncome(stable, stable2);
        stable.setId(insertedID.get(0));
        stable.setAmount(3);
        stable.setDate(1880);
        stable.setFrequency(6);
        stable.setName("hello");
        stable.setSign('-');
        stable.setSubCategoriesId(subCategoriesId2);

        stable2.setId(insertedID.get(1));
        stable2.setAmount(23);
        stable2.setDate(2880);
        stable2.setFrequency(26);
        stable2.setName("hello2");
        stable2.setSign('+');
        stable2.setSubCategoriesId(subCategoriesId4);

        stableExpensesAndIncomeDao.updateStableExpensesAndIncome(stable, stable2);
        List<StableExpensesAndIncome> stables = stableExpensesAndIncomeDao.getStable();

        assertEquals(stables.get(0).getName(), stable.getName());
        assertEquals(stables.get(0).getAmount(), stable.getAmount());
        assertEquals(stables.get(0).getSign(), stable.getSign());
        assertEquals(stables.get(0).getDate(), stable.getDate());
        assertEquals(stables.get(0).getFrequency(), stable.getFrequency());
        assertEquals(stables.get(0).getSubCategoriesId(), stable.getSubCategoriesId());

        assertEquals(stables.get(1).getName(), stable2.getName());
        assertEquals(stables.get(1).getAmount(), stable2.getAmount());
        assertEquals(stables.get(1).getSign(), stable2.getSign());
        assertEquals(stables.get(1).getDate(), stable2.getDate());
        assertEquals(stables.get(1).getFrequency(), stable2.getFrequency());
        assertEquals(stables.get(1).getSubCategoriesId(), stable2.getSubCategoriesId());
    }

    @Test
    public void deleteStableExpenseOrIncome() {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        StableExpensesAndIncome stable = new StableExpensesAndIncome("test", 20, '+', 1990, 2, subCategoriesId);
        long insertedId = stableExpensesAndIncomeDao.insertStableExpenseOrIncome(stable);
        stable.setId(insertedId);
        stableExpensesAndIncomeDao.deleteStableExpenseOrIncome(stable);
        List<StableExpensesAndIncome> stables = stableExpensesAndIncomeDao.getStable();
        assertThat(stables.isEmpty(), is(true));
    }

    @Test
    public void deleteStableExpensesAndIncome() {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        StableExpensesAndIncome stable = new StableExpensesAndIncome("test", 20, '+', 1990, 2, subCategoriesId);
        SubCategories subCategory3 = new SubCategories("test3", categoriesId);
        long subCategoriesId3 = subCategoriesDao.insertSubCategory(subCategory3);
        StableExpensesAndIncome stable2 = new StableExpensesAndIncome("test5", 2, '-', 199, 25, subCategoriesId3);

        List<Long> insertedID = stableExpensesAndIncomeDao.insertStableExpensesAndIncome(stable, stable2);
        stable.setId(insertedID.get(0));
        stable2.setId(insertedID.get(1));
        stableExpensesAndIncomeDao.deleteStableExpensesAndIncome(stable, stable2);

        List<StableExpensesAndIncome> stables = stableExpensesAndIncomeDao.getStable();
        assertThat(stables.isEmpty(), is(true));
    }
}