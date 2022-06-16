package com.example.sy43.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpensesDao {
    @Query("SELECT * FROM Expenses")
    List<Expenses> getAll();

    @Query("SELECT * FROM Expenses WHERE id IN (:expID)")
    List<Expenses> loadAllByIDs(int[] expID);

    @Query("SELECT * FROM Expenses WHERE e_name LIKE :name LIMIT 1")
    Expenses findByName(String name);

    @Query( "SELECT * FROM Expenses " +
            "INNER JOIN SubCategory on SubCategory.id = Expenses.e_subcategory " +
            "WHERE SubCategory.id = :subcategory")
    List<Expenses> findBySubCategory(int subcategory);

    @Query( "SELECT * FROM Expenses " +
            "INNER JOIN SubCategory on SubCategory.id = Expenses.e_subcategory " +
            "INNER JOIN Category on Category.id = SubCategory.category " +
            "WHERE Category.id = :category")
    List<Expenses> findByCategory(int category);

    @Query( "SELECT * FROM Expenses " +
            "INNER JOIN MonthlyRevenue on MonthlyRevenue.id = Expenses.e_monthlyrevenue " +
            "WHERE MonthlyRevenue.id = :month")
    List<Expenses> findByMonth(int month);

    @Insert
    void insertAll(Expenses... exp);

    @Delete
    void delete(Expenses exp);
}