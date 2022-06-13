package com.example.sy43.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SubCategoryDao {
    @Query("SELECT * FROM SubCategory")
    List<SubCategory> getAll();

    @Query("SELECT * FROM SubCategory ORDER BY s_name")
    List<SubCategory> getAllOrderedByName();

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

    @Update (onConflict = OnConflictStrategy.REPLACE)
    int updateAll(SubCategory... subcat);

    @Delete
    void delete(SubCategory subcat);
}
