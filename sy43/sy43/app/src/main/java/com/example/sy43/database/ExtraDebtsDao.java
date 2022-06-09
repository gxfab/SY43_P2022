package com.example.sy43.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExtraDebtsDao {
    @Query("SELECT * FROM ExtraDebts")
    LiveData<List<ExtraDebts>> getAll();

    @Query("SELECT * FROM ExtraDebts WHERE id IN (:debtID)")
    LiveData<List<ExtraDebts>> loadAllByIDs(int[] debtID);

    @Query("SELECT * FROM ExtraDebts WHERE name LIKE :name LIMIT 1")
    ExtraDebts findByName(String name);

    @Query("SELECT * FROM ExtraDebts WHERE value = :value")
    ExtraDebts findByValue(double value);

    @Insert
    void insertAll(ExtraDebts... debt);

    @Delete
    void delete(ExtraDebts debt);
}

