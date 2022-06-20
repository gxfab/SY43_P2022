package com.sucelloztm.sucelloz.database.DAO;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Savings;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SavingsDaoTest {
    // FOR DATA
    private SucellozDatabase database;
    private SavingsDao savingsDao;

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                SucellozDatabase.class)
                .build();
        savingsDao = database.savingsDao();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertSaving() throws Exception {
        Savings saving = new Savings("test", "janvier", 1000);
        savingsDao.insertSaving(saving);
        List<Savings> savings = savingsDao.getSavings();
        assertEquals(savings.get(0).getName(), saving.getName());
    }

    @Test
    public void insertSavings() throws Exception {
        Savings saving1 = new Savings("test1", "février", 1000);
        Savings saving2 = new Savings("test2", "mars", 1000);
        savingsDao.insertSavings(saving1, saving2);
        List<Savings> savings = savingsDao.getSavings();
        assertEquals(savings.get(0).getName(), saving1.getName());
        assertEquals(savings.get(1).getName(), saving2.getName());
    }

    @Test
    public void updateSaving() throws Exception {
        Savings saving = new Savings("test", "demain", 1000);
        long insertId = savingsDao.insertSaving(saving);
        saving.setId(insertId);
        saving.setName("testUpdate");
        savingsDao.updateSaving(saving);
        List<Savings> savings = savingsDao.getSavings();
        assertEquals(savings.get(0).getName(), saving.getName());
    }

    @Test
    public void updateSavings() throws Exception {
        Savings saving1 = new Savings("test", "août", 1000);
        Savings saving2 = new Savings("test", "septembre", 1000);
        List<Long> insertId = savingsDao.insertSavings(saving1, saving2);
        saving1.setId(insertId.get(0));
        saving1.setName("testUpdate1");
        saving2.setId(insertId.get(1));
        saving2.setName("testUpdate2");
        savingsDao.updateSavings(saving1, saving2);
        List<Savings> savings = savingsDao.getSavings();
        assertEquals(savings.get(0).getName(), saving1.getName());
        assertEquals(savings.get(1).getName(), saving2.getName());
    }

    @Test
    public void deleteSaving() throws Exception {
        Savings saving = new Savings("test", "un jour", 1000);
        long insertId = savingsDao.insertSaving(saving);
        saving.setId(insertId);
        savingsDao.deleteSaving(saving);
        List<Savings> savings = savingsDao.getSavings();
        assertThat(savings.isEmpty(), is(true));
    }

    @Test
    public void deleteSavings() throws Exception {
        Savings saving1 = new Savings("test", "mon prince viendra", 1000);
        Savings saving2 = new Savings("test", "st glin glin", 1000);
        List<Long> insertId = savingsDao.insertSavings(saving1, saving2);
        saving1.setId(insertId.get(0));
        saving2.setId(insertId.get(1));
        savingsDao.deleteSavings(saving1, saving2);
        List<Savings> savings = savingsDao.getSavings();
        assertThat(savings.isEmpty(), is(true));
    }
}