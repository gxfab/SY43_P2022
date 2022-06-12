package com.example.sy43.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProjectsDao {
    @Query("SELECT * FROM Projects")
    List<Projects> getAll();

    @Query("SELECT * FROM Projects WHERE id IN (:catID)")
    List<Projects> loadAllByIDs(int[] catID);

    @Query("SELECT * FROM Projects WHERE name LIKE :name LIMIT 1")
    Projects findByName(String name);

    @Query("SELECT * FROM Projects WHERE value = :value")
    Projects findByValue(double value);

    @Insert
    void insertAll(Projects... debt);

    @Delete
    void delete(Projects debt);
}
