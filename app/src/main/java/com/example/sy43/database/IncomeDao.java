package com.example.sy43.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IncomeDao {
    @Query("SELECT * FROM Income")
    List<Income> getAll();

    @Query("SELECT * FROM Income WHERE id IN (:incomeID)")
    List<Income> loadAllByIDs(int[] incomeID);

    @Query("SELECT * FROM Income WHERE i_name LIKE :name LIMIT 1")
    Income findByName(String name);

    @Query( "SELECT * FROM Income " +
            "INNER JOIN MonthlyRevenue on MonthlyRevenue.id = Income.i_monthlyrevenue " +
            "WHERE MonthlyRevenue.id = :month")
    List<Income> findByMonth(int month);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Income... income);

    @Delete
    void delete(Income income);
}
