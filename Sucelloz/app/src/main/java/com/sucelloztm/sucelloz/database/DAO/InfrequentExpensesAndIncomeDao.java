package com.sucelloztm.sucelloz.database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;

import java.util.List;

/**
 * Interface for the infrequent expenses and incomes entity of the dao
 */
@Dao
public interface InfrequentExpensesAndIncomeDao {
    /**
     * Inserts an infrequent element
     *
     * @param infrequentExpensesAndIncome element to insert
     * @return id of the inserted element
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertInfrequentExpenseAndIncome(InfrequentExpensesAndIncome infrequentExpensesAndIncome);

    /**
     * Inserts a list of infrequent elements
     *
     * @param infrequentExpensesAndIncomes elements to insert
     * @return list of the id of the inserted elements
     */
    @Insert
    List<Long> insertInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    /**
     * Updates an infrequent element
     *
     * @param infrequentExpenseOrIncome element to update
     */
    @Update
    void updateInfrequentExpenseAndIncome(InfrequentExpensesAndIncome infrequentExpenseOrIncome);

    /**
     * Updates a list of infrequent elements
     *
     * @param infrequentExpensesAndIncomes elements to update
     */
    @Update
    void updateInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    /**
     * Deletes an infrequent element
     *
     * @param infrequentExpenseOrIncome element to delete
     */
    @Delete
    void deleteInfrequentExpenseAndIncome(InfrequentExpensesAndIncome infrequentExpenseOrIncome);

    /**
     * Deletes a list of infrequent elements
     *
     * @param infrequentExpensesAndIncomes list of elements to delete
     */
    @Delete
    void deleteInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    /**
     * Query to get all infrequent elements
     *
     * @return livedata of the infrequent elements
     */
    @Query("SELECT * FROM infrequent_expenses")
    LiveData<List<InfrequentExpensesAndIncome>> getAllInfrequent();

    /**
     * Query to get all infrequent elements in a list
     *
     * @return list of the infrequent elements
     */
    @Query("SELECT * FROM infrequent_expenses")
    List<InfrequentExpensesAndIncome> getInfrequent();

    /**
     * Query to get positively signed infrequent elements
     *
     * @return livedata of all the positively signed infrequent elements
     */
    @Query("SELECT * FROM infrequent_expenses WHERE sign LIKE '+'")
    LiveData<List<InfrequentExpensesAndIncome>> getAllPositiveInfrequent();

    /**
     * Query to get negatively signed infrequent elements
     *
     * @return livedata of all the negatively signed infrequent elements
     */
    @Query("SELECT * FROM infrequent_expenses WHERE sign LIKE '-'")
    LiveData<List<InfrequentExpensesAndIncome>> getAllNegativeInfrequent();

    /**
     * Query to get the sum of all infrequent expenses
     *
     * @return livedata of all the negatively signed infrequent elements
     */
    @Query("SELECT CAST(total(amount) AS INTEGER) FROM infrequent_expenses WHERE sign LIKE '-'")
    LiveData<Integer> getSumOfInfrequentExpenses();

    /**
     * Query to get the sum of all infrequent incomes
     *
     * @return livedata of all the positively signed infrequent elements
     */
    @Query("SELECT CAST(total(amount) AS INTEGER) FROM infrequent_expenses WHERE sign LIKE '+'")
    LiveData<Integer> getSumOfInfrequentIncomes();
}
