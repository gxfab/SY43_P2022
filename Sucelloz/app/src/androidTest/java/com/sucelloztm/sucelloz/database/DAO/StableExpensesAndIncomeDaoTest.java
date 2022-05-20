package com.sucelloztm.sucelloz.database.DAO;

import static org.junit.Assert.*;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StableExpensesAndIncomeDaoTest {

    // FOR DATA
    private SucellozDatabase database;
    private StableExpensesAndIncomeDao stableExpensesAndIncomeDao;

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                SucellozDatabase.class)
                .build();
        stableExpensesAndIncomeDao = database.stableExpensesAndIncomeDao();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertStableExpenseOrIncome() throws Exception {
        StableExpensesAndIncome stable = new StableExpensesAndIncome();
    }

    @Test
    public void insertStableExpensesAndIncome() throws Exception {
    }

    @Test
    public void updateStableExpenseOrIncome() {
    }

    @Test
    public void updateStableExpensesAndIncome() {
    }

    @Test
    public void deleteStableExpenseOrIncome() {
    }

    @Test
    public void deleteStableExpensesAndIncome() {
    }
}