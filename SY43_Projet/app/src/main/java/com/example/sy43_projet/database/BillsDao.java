package com.example.sy43_projet.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BillsDao {
    @Query("SELECT * FROM Bills")
    List<Bills> getAll();

    @Query("SELECT * FROM Bills WHERE id IN (:billID)")
    List<Bills> loadAllByIDs(int[] billID);

    @Query("SELECT * FROM Bills WHERE b_name LIKE :name LIMIT 1")
    Bills findByName(String name);

    @Query( "SELECT * FROM Bills " +
            "INNER JOIN SubCategory on SubCategory.id = Bills.b_subcategory " +
            "WHERE SubCategory.id = :subcategory")
    List<Bills> findBySubCategory(int subcategory);

    @Insert
    void insertAll(Bills... bill);

    @Delete
    void delete(Bills bill);
}
