package com.sucelloztm.sucelloz.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.CategoriesWithSubCategories;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;

import java.util.List;

@Dao
public interface StableExpensesAndIncomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    @Insert
    List<Long> insertStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    @Update
    void updateStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    @Update
    void updateStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    @Delete
    void deleteStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    @Delete
    void deleteStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    @Query("SELECT * FROM stable_expenses")
    List<StableExpensesAndIncome> getStable();
}
