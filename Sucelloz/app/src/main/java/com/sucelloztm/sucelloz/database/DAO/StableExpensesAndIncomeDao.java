package com.sucelloztm.sucelloz.database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;

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

    /**
     * query to get negatively signed infrequent elements
     * @return livedata of all the negatively signed infrequent elements
     */
    @Query("SELECT * FROM stable_expenses WHERE sign LIKE '-'")
    LiveData<List<StableExpensesAndIncome>> getAllNegativeStable();

    /**
     * query to get all stable elements in a list
     * @return list of the infrequent elements
     */

    @Query("SELECT * FROM stable_expenses")
    LiveData<List<StableExpensesAndIncome>> getAllStable();



    /**
     * query to get positively signed stable elements
     * @return livedata of all the positively signed infrequent elements
     */
    @Query("SELECT * FROM stable_expenses WHERE sign LIKE '+'")
    LiveData<List<StableExpensesAndIncome>> getAllPositiveStable();

    @Query("SELECT * FROM stable_expenses WHERE sub_categories_id=:idOfSubCategory")
    LiveData<List<StableExpensesAndIncome>> getAllStableFromSubCategory(long idOfSubCategory);

    /**
     * query to get the sum of all infrequent expenses
     * @return livedata of all the negatively signed infrequent elements
     */
    @Query("SELECT SUM(amount) FROM stable_expenses WHERE sign LIKE '-'")
    LiveData<Integer> getSumOfStableExpenses();

    /**
     * query to get the sum of all infrequent incomes
     * @return livedata of all the positively signed infrequent elements
     */
    @Query("SELECT SUM(amount) FROM stable_expenses WHERE sign LIKE '+'")
    LiveData<Integer> getSumOfStableIncomes();

}