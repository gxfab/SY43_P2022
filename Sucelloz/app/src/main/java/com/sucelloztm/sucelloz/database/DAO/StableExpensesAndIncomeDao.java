package com.sucelloztm.sucelloz.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;

import java.util.List;

/**
 * interface for the stable expenses and income entity of the dao
 */
@Dao
public interface StableExpensesAndIncomeDao {
    /**
     * inserts a stable element in the dao
     * @param stableExpenseOrIncome element to insert
     * @return id of the inserted element
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    /**
     * inserts a list of stable elements
     * @param stableExpensesAndIncomes list of elements to insert
     * @return list of the ids of the inserted elements
     */
    @Insert
    List<Long> insertStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    /**
     * updates a stable element
     * @param stableExpenseOrIncome element to update
     */
    @Update
    void updateStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    /**
     * updates a list of stable elements
     * @param stableExpensesAndIncomes list of elements to update
     */
    @Update
    void updateStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    /**
     * deletes a stable element
     * @param stableExpenseOrIncome element to delete
     */
    @Delete
    void deleteStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    /**
     * updates a list of stable elements
     * @param stableExpensesAndIncomes list of elements to delete
     */
    @Delete
    void deleteStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    /**
     * query to get all stable elements
     * @return list of all stable elements
     */
    @Query("SELECT * FROM stable_expenses")
    List<StableExpensesAndIncome> getStable();
}
