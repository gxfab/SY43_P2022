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
 * Interface for the stable expenses and income entity of the dao
 */
@Dao
public interface StableExpensesAndIncomeDao {
    /**
     * Inserts a stable element
     * @param stableExpenseOrIncome element to insert
     * @return id of the inserted element
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    /**
     * Inserts a list of stable elements
     * @param stableExpensesAndIncomes list of elements to insert
     * @return list of the ids of the inserted elements
     */
    @Insert
    List<Long> insertStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    /**
     * Updates a stable element
     * @param stableExpenseOrIncome element to update
     */
    @Update
    void updateStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    /**
     * Updates a list of stable elements
     * @param stableExpensesAndIncomes list of elements to update
     */
    @Update
    void updateStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    /**
     * Deletes a stable element
     * @param stableExpenseOrIncome element to delete
     */
    @Delete
    void deleteStableExpenseOrIncome(StableExpensesAndIncome stableExpenseOrIncome);

    /**
     * Updates a list of stable elements
     * @param stableExpensesAndIncomes list of elements to delete
     */
    @Delete
    void deleteStableExpensesAndIncome(StableExpensesAndIncome... stableExpensesAndIncomes);

    /**
     * Query to get all stable elements
     * @return list of all stable elements
     */
    @Query("SELECT * FROM stable_expenses")
    List<StableExpensesAndIncome> getStable();

    /**
     * Query to get negatively signed infrequent elements
     * @return livedata of all the negatively signed infrequent elements
     */
    @Query("SELECT * FROM stable_expenses WHERE sign LIKE '-'")
    LiveData<List<StableExpensesAndIncome>> getAllNegativeStable();

    /**
     * Query to get all stable elements in a list
     * @return livedata of list of the infrequent elements
     */

    @Query("SELECT * FROM stable_expenses")
    LiveData<List<StableExpensesAndIncome>> getAllStable();



    /**
     * Query to get positively signed stable elements
     * @return livedata of a list of all the positively signed infrequent elements
     */
    @Query("SELECT * FROM stable_expenses WHERE sign LIKE '+'")
    LiveData<List<StableExpensesAndIncome>> getAllPositiveStable();


    /**
     * Query to get all stable elements from a specific sub-category
     * @param idOfSubCategory id of specific sub-category
     * @return livedata of a list of all stable elements from a specific sub-category
     */
    @Query("SELECT * FROM stable_expenses WHERE sub_categories_id=:idOfSubCategory")
    LiveData<List<StableExpensesAndIncome>> getAllStableFromSubCategory(long idOfSubCategory);

    /**
     * Query to get the sum of all infrequent expenses
     * @return livedata of all the negatively signed infrequent elements
     */
    @Query("SELECT CAST(total(amount) AS INTEGER) FROM stable_expenses WHERE sign LIKE '-'")
    LiveData<Integer> getSumOfStableExpenses();

    /**
     * Query to get the sum of all infrequent incomes
     * @return livedata of all the positively signed infrequent elements
     */
    @Query("SELECT CAST(total(amount) AS INTEGER) FROM stable_expenses WHERE sign LIKE '+'")
    LiveData<Integer> getSumOfStableIncomes();

    /**
     * Query to get a specific stable element with an id
     * @param idOfStable id of a specific stable element
     * @return resulting stable element
     */
    @Query("SELECT * FROM stable_expenses WHERE id=:idOfStable")
    StableExpensesAndIncome getStableById(long idOfStable);

}