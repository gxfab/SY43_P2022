package com.sucelloztm.sucelloz.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;

@Dao
public interface InfrequentExpensesAndIncomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInfrequentExpenseOrIncome(InfrequentExpensesAndIncome infrequentExpensesAndIncome);

    @Insert
    void insertInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    @Update
    void updateInfrequentExpenseOrIncome(InfrequentExpensesAndIncome infrequentExpenseOrIncome);

    @Update
    void updateInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    @Delete
    void deleteInfrequentExpenseOrIncome(InfrequentExpensesAndIncome infrequentExpenseOrIncome);

    @Delete
    void deleteInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);
}
