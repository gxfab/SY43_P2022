package com.sucelloztm.sucelloz.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;

import java.util.List;

@Dao
public interface InfrequentExpensesAndIncomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertInfrequentExpenseOrIncome(InfrequentExpensesAndIncome infrequentExpensesAndIncome);

    @Insert
    List<Long> insertInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    @Update
    void updateInfrequentExpenseOrIncome(InfrequentExpensesAndIncome infrequentExpenseOrIncome);

    @Update
    void updateInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    @Delete
    void deleteInfrequentExpenseOrIncome(InfrequentExpensesAndIncome infrequentExpenseOrIncome);

    @Delete
    void deleteInfrequentExpensesAndIncome(InfrequentExpensesAndIncome... infrequentExpensesAndIncomes);

    @Query("SELECT * FROM infrequent_expenses")
    List<InfrequentExpensesAndIncome> getInfrequent();
}
