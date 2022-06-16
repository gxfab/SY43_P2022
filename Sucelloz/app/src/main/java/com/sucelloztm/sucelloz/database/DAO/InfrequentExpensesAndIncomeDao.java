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
 * interface for the infrequent expenses and incomes entity of the dao
 */
@Dao
public interface InfrequentExpensesAndIncomeDao {
    /**
     * inserts an infrequent element in the dao
     * @param infrequentExpensesAndIncome element to insert
     * @return id of the inserted element
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertInfrequentExpenseAndIncome(InfrequentExpensesAndIncome infrequentExpensesAndIncome);

    /**
     * inserts a list of infrequent elements
     * @param infrequentExpensesAndIncomes elements to insert
     * @return list of the id of the inserted elements
     */
    @Insert
    List<Long> insertInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    /**
     * updates an infrequent element
     * @param infrequentExpenseOrIncome element to update
     */
    @Update
    void updateInfrequentExpenseAndIncome(InfrequentExpensesAndIncome infrequentExpenseOrIncome);

    /**
     * updates a list of infrequent elements
     * @param infrequentExpensesAndIncomes elements to update
     */
    @Update
    void updateInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    /**
     * deletes an infrequent element
     * @param infrequentExpenseOrIncome element to delete
     */
    @Delete
    void deleteInfrequentExpenseAndIncome(InfrequentExpensesAndIncome infrequentExpenseOrIncome);

    /**
     * deletes a list of infrequent elements
     * @param infrequentExpensesAndIncomes list of elements to delete
     */
    @Delete
    void deleteInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    /**
     * query to get all infrequent elements in a livedata
     * @return livedata of the infrequent elements
     */
    @Query("SELECT * FROM infrequent_expenses")
    LiveData<List<InfrequentExpensesAndIncome>> getAllInfrequent();

    /**
     * query to get all infrquent elements in a list
     * @return list of the infrequent elements
     */
    @Query("SELECT * FROM infrequent_expenses")
    List<InfrequentExpensesAndIncome> getInfrequent();

    /**
     * query to get positively signed infrequent elements
     * @return livedata of all the positively signed infrequent elements
     */
    @Query("SELECT * FROM infrequent_expenses WHERE sign LIKE '+'")
    LiveData<List<InfrequentExpensesAndIncome>> getAllPositiveInfrequent();

    /**
     * query to get negatively signed infrequent elements
     * @return livedata of all the negatively signed infrequent elements
     */
    @Query("SELECT * FROM infrequent_expenses WHERE sign LIKE '-'")
    LiveData<List<InfrequentExpensesAndIncome>> getAllNegativeInfrequent();

    /**
     * query to get the sum of all infrequent expenses
     * @return livedata of all the negatively signed infrequent elements
     */
    @Query("SELECT SUM(amount) FROM infrequent_expenses WHERE sign LIKE '-'")
    LiveData<Integer> getSumOfInfrequentExpenses();

    /**
     * query to get the sum of all infrequent incomes
     * @return livedata of all the positively signed infrequent elements
     */
    @Query("SELECT SUM(amount) FROM infrequent_expenses WHERE sign LIKE '+'")
    LiveData<Integer> getSumOfInfrequentIncomes();
}
