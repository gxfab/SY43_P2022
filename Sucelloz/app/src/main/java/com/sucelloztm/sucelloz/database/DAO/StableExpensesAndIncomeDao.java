package com.sucelloztm.sucelloz.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;

@Dao
public interface StableExpensesAndIncomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    @Insert
    void insertStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    @Update
    void updateStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    @Update
    void updateStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    @Delete
    void deleteStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    @Delete
    void deleteStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);
}
