package com.sucelloztm.sucelloz.database.DAO;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RunWith(AndroidJUnit4.class)
public class InfrequentExpensesAndIncomeDaoTest {

    // FOR DATA
    private SucellozDatabase database;
    private InfrequentExpensesAndIncomeDao infrequentExpensesAndIncomeDao;
    private CategoriesDao categoriesDao;
    private SubCategoriesDao subCategoriesDao;

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                SucellozDatabase.class)
                .build();
        infrequentExpensesAndIncomeDao = database.infrequentExpensesAndIncomeDao();
        subCategoriesDao = database.subCategoriesDao();
        categoriesDao = database.categoriesDao();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertInfrequentExpenseOrIncome() throws Exception {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        InfrequentExpensesAndIncome infrequent = new InfrequentExpensesAndIncome("test", 20, "+","1984", subCategoriesId);
        infrequentExpensesAndIncomeDao.insertInfrequentExpenseAndIncome(infrequent);
        List<InfrequentExpensesAndIncome> infrequents = infrequentExpensesAndIncomeDao.getInfrequent();

        assertEquals(infrequents.get(0).getName(),infrequent.getName());
        assertEquals(infrequents.get(0).getAmount(),infrequent.getAmount());
        assertEquals(infrequents.get(0).getSign(),infrequent.getSign());
        assertEquals(infrequents.get(0).getDate(),infrequent.getDate());
        assertEquals(infrequents.get(0).getSubCategoriesId(),infrequent.getSubCategoriesId());
    }

    @Test
    public void insertInfrequentExpensesAndIncome() throws Exception {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory1 = new SubCategories("test1", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory1);
        SubCategories subCategory2 = new SubCategories("test2", categoriesId);
        long subCategoriesId2 = subCategoriesDao.insertSubCategory(subCategory2);
        InfrequentExpensesAndIncome infrequent1 = new InfrequentExpensesAndIncome("test1", 20, "+","1990", subCategoriesId);
        InfrequentExpensesAndIncome infrequent2 = new InfrequentExpensesAndIncome("test2", 2, "-","1999", subCategoriesId2);
        infrequentExpensesAndIncomeDao.insertInfrequentExpensesAndIncome(infrequent1,infrequent2);
        List<InfrequentExpensesAndIncome> infrequents = infrequentExpensesAndIncomeDao.getInfrequent();

        assertEquals(infrequents.get(0).getName(),infrequent1.getName());
        assertEquals(infrequents.get(0).getAmount(),infrequent1.getAmount());
        assertEquals(infrequents.get(0).getSign(),infrequent1.getSign());
        assertEquals(infrequents.get(0).getDate(),infrequent1.getDate());
        assertEquals(infrequents.get(0).getSubCategoriesId(),infrequent1.getSubCategoriesId());

        assertEquals(infrequents.get(1).getName(),infrequent2.getName());
        assertEquals(infrequents.get(1).getAmount(),infrequent2.getAmount());
        assertEquals(infrequents.get(1).getSign(),infrequent2.getSign());
        assertEquals(infrequents.get(1).getDate(),infrequent2.getDate());
        assertEquals(infrequents.get(1).getSubCategoriesId(),infrequent2.getSubCategoriesId());
    }

    @Test
    public void updateInfrequentExpenseOrIncome() {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        SubCategories subCategory2 = new SubCategories("test2", categoriesId);
        long subCategoriesId2 = subCategoriesDao.insertSubCategory(subCategory2);
        InfrequentExpensesAndIncome infrequent = new InfrequentExpensesAndIncome("test", 20, "+","1990", subCategoriesId);
        long insertedID = infrequentExpensesAndIncomeDao.insertInfrequentExpenseAndIncome(infrequent);
        infrequent.setId(insertedID);
        infrequent.setAmount(3);
        infrequent.setDate("1880");
        infrequent.setName("hello");
        infrequent.setSign("-");
        infrequent.setSubCategoriesId(subCategoriesId2);
        infrequentExpensesAndIncomeDao.updateInfrequentExpenseAndIncome(infrequent);
        List<InfrequentExpensesAndIncome> infrequents = infrequentExpensesAndIncomeDao.getInfrequent();

        assertEquals(infrequents.get(0).getName(),infrequent.getName());
        assertEquals(infrequents.get(0).getAmount(),infrequent.getAmount());
        assertEquals(infrequents.get(0).getSign(),infrequent.getSign());
        assertEquals(infrequents.get(0).getDate(),infrequent.getDate());
        assertEquals(infrequents.get(0).getSubCategoriesId(),infrequent.getSubCategoriesId());
    }

    @Test
    public void updateInfrequentExpensesAndIncome() {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        SubCategories subCategory2 = new SubCategories("test2", categoriesId);
        long subCategoriesId2 = subCategoriesDao.insertSubCategory(subCategory2);
        InfrequentExpensesAndIncome infrequent = new InfrequentExpensesAndIncome("test", 20, "+","1990", subCategoriesId);
        SubCategories subCategory3 = new SubCategories("test3", categoriesId);
        long subCategoriesId3 = subCategoriesDao.insertSubCategory(subCategory3);
        SubCategories subCategory4 = new SubCategories("test4", categoriesId);
        long subCategoriesId4 = subCategoriesDao.insertSubCategory(subCategory4);
        InfrequentExpensesAndIncome infrequent2 = new InfrequentExpensesAndIncome("test5", 2, "-","199", subCategoriesId3);

        List<Long> insertedID = infrequentExpensesAndIncomeDao.insertInfrequentExpensesAndIncome(infrequent,infrequent2);
        infrequent.setId(insertedID.get(0));
        infrequent.setAmount(3);
        infrequent.setDate("1880");
        infrequent.setName("hello");
        infrequent.setSign("-");
        infrequent.setSubCategoriesId(subCategoriesId2);

        infrequent2.setId(insertedID.get(1));
        infrequent2.setAmount(23);
        infrequent2.setDate("2880");
        infrequent2.setName("hello2");
        infrequent2.setSign("+");
        infrequent2.setSubCategoriesId(subCategoriesId4);

        infrequentExpensesAndIncomeDao.updateInfrequentExpensesAndIncome(infrequent,infrequent2);
        List<InfrequentExpensesAndIncome> infrequents = infrequentExpensesAndIncomeDao.getInfrequent();

        assertEquals(infrequents.get(0).getName(),infrequent.getName());
        assertEquals(infrequents.get(0).getAmount(),infrequent.getAmount());
        assertEquals(infrequents.get(0).getSign(),infrequent.getSign());
        assertEquals(infrequents.get(0).getDate(),infrequent.getDate());
        assertEquals(infrequents.get(0).getSubCategoriesId(),infrequent.getSubCategoriesId());

        assertEquals(infrequents.get(1).getName(),infrequent2.getName());
        assertEquals(infrequents.get(1).getAmount(),infrequent2.getAmount());
        assertEquals(infrequents.get(1).getSign(),infrequent2.getSign());
        assertEquals(infrequents.get(1).getDate(),infrequent2.getDate());
        assertEquals(infrequents.get(1).getSubCategoriesId(),infrequent2.getSubCategoriesId());
    }

    @Test
    public void deleteInfrequentExpenseOrIncome() {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        InfrequentExpensesAndIncome infrequent = new InfrequentExpensesAndIncome("test", 20, "+","1990", subCategoriesId);
        long insertedId = infrequentExpensesAndIncomeDao.insertInfrequentExpenseAndIncome(infrequent);
        infrequent.setId(insertedId);
        infrequentExpensesAndIncomeDao.deleteInfrequentExpenseAndIncome(infrequent);
        List<InfrequentExpensesAndIncome> infrequents = infrequentExpensesAndIncomeDao.getInfrequent();
        assertThat(infrequents.isEmpty(), is(true));
    }

    @Test
    public void deleteInfrequentExpensesAndIncome() {
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        InfrequentExpensesAndIncome infrequent = new InfrequentExpensesAndIncome("test", 20, "+","1990", subCategoriesId);
        SubCategories subCategory3 = new SubCategories("test3", categoriesId);
        long subCategoriesId3 = subCategoriesDao.insertSubCategory(subCategory3);
        InfrequentExpensesAndIncome infrequent2 = new InfrequentExpensesAndIncome("test5", 2, "-","199", subCategoriesId3);

        List<Long> insertedID = infrequentExpensesAndIncomeDao.insertInfrequentExpensesAndIncome(infrequent,infrequent2);
        infrequent.setId(insertedID.get(0));
        infrequent2.setId(insertedID.get(1));
        infrequentExpensesAndIncomeDao.deleteInfrequentExpensesAndIncome(infrequent,infrequent2);

        List<InfrequentExpensesAndIncome> infrequents = infrequentExpensesAndIncomeDao.getInfrequent();
        assertThat(infrequents.isEmpty(), is(true));
    }

    @Test
    public void getSumOfInfrequentIncomes(){
        Categories category = new Categories("test", true);
        long categoriesId = categoriesDao.insertCategory(category);
        SubCategories subCategory = new SubCategories("test", categoriesId);
        long subCategoriesId = subCategoriesDao.insertSubCategory(subCategory);
        InfrequentExpensesAndIncome infrequent = new InfrequentExpensesAndIncome("test", 20, "+","1990", subCategoriesId);
        SubCategories subCategory3 = new SubCategories("test3", categoriesId);
        long subCategoriesId3 = subCategoriesDao.insertSubCategory(subCategory3);
        InfrequentExpensesAndIncome infrequent2 = new InfrequentExpensesAndIncome("test5", 2, "-","199", subCategoriesId3);

        List<Long> insertedID = infrequentExpensesAndIncomeDao.insertInfrequentExpensesAndIncome(infrequent,infrequent2);
        infrequent.setId(insertedID.get(0));
        infrequent2.setId(insertedID.get(1));

        LiveData<Integer> lsum = infrequentExpensesAndIncomeDao.getSumOfInfrequentIncomes();
        final Integer[] sum = new Integer[1];
        final Observer<Integer> cringeobs= new Observer<Integer>() {
            @Override
            public void onChanged(Integer cringeint) {
                sum[0] = cringeint;
            }
        };
        Integer expected = 22;
        List<InfrequentExpensesAndIncome> infrequents = infrequentExpensesAndIncomeDao.getInfrequent();
        assertEquals(expected,sum[0]);
    }
}