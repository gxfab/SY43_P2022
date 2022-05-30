package com.example.sy43_projet.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM Category")
    List<Category> getAll();

    @Query("SELECT * FROM Category WHERE id IN (:catID)")
    List<Category> loadAllByIDs(int[] catID);

    @Query("SELECT * FROM Category WHERE c_name LIKE :name LIMIT 1")
    Category findByName(String name);

    @Insert
    void insertAll(Category... cat);

    @Delete
    void delete(Category cat);
}
