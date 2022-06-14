package com.example.sy43.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SubCategoryDao {
    @Query("SELECT * FROM SubCategory")
    List<SubCategory> getAll();

    @Query("SELECT * FROM SubCategory WHERE id IN (:subcatID)")
    List<SubCategory> loadAllByIDs(int[] subcatID);

    @Query("SELECT * FROM SubCategory WHERE s_name LIKE :name LIMIT 1")
    SubCategory findByName(String name);

    @Query( "SELECT * FROM SubCategory " +
            "INNER JOIN Category on Category.id = SubCategory.category " +
            "WHERE Category.id = :category")
    List<SubCategory> findByCategory(int category);

    @Insert
    void insertAll(SubCategory... subcat);

    @Delete
    void delete(SubCategory subcat);
}
