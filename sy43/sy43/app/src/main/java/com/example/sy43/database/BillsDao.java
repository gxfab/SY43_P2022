package com.example.sy43.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BillsDao {
    @Query("SELECT * FROM Bills")
    LiveData<List<Bills>> getAll();

    @Query("SELECT * FROM Bills WHERE id IN (:billID)")
    LiveData<List<Bills>> loadAllByIDs(int[] billID);

    @Query("SELECT * FROM Bills WHERE b_name LIKE :name LIMIT 1")
    Bills findByName(String name);

    @Query( "SELECT * FROM Bills " +
            "INNER JOIN SubCategory on SubCategory.id = Bills.b_subcategory " +
            "WHERE SubCategory.id = :subcategory")
    LiveData<List<Bills>> findBySubCategory(int subcategory);

    @Insert
    void insertAll(Bills... bill);

    @Delete
    void delete(Bills bill);
}
