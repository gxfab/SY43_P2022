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

@Dao
public interface InfrequentExpensesAndIncomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertInfrequentExpenseAndIncome(InfrequentExpensesAndIncome infrequentExpensesAndIncome);

    @Insert
    List<Long> insertInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    @Update
    void updateInfrequentExpenseAndIncome(InfrequentExpensesAndIncome infrequentExpenseOrIncome);

    @Update
    void updateInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    @Delete
    void deleteInfrequentExpenseAndIncome(InfrequentExpensesAndIncome infrequentExpenseOrIncome);

    @Delete
    void deleteInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    @Query("SELECT * FROM infrequent_expenses")
    LiveData<List<InfrequentExpensesAndIncome>> getAllInfrequent();

    @Query("SELECT * FROM infrequent_expenses")
    List<InfrequentExpensesAndIncome> getInfrequent();

    @Query("SELECT * FROM infrequent_expenses WHERE sign LIKE '+'")
    LiveData<List<InfrequentExpensesAndIncome>> getAllPositiveInfrequent();

    @Query("SELECT * FROM infrequent_expenses WHERE sign LIKE '-'")
    LiveData<List<InfrequentExpensesAndIncome>> getAllNegativeInfrequent();
}
