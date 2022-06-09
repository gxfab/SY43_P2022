package com.example.sy43.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SubCategoryDao {
    //Live data is used to prevent memory leaks and app crashes
    //it is lifecycle aware, and will update the activity only when in the foreground
    //if the activity is destroyed it is destroyed with it

    @Query("SELECT * FROM SubCategory")
    LiveData<List<SubCategory>> getAll();

    @Query("SELECT * FROM SubCategory WHERE id IN (:subcatID)")
    LiveData<List<SubCategory>> loadAllByIDs(int[] subcatID);

    @Query("SELECT * FROM SubCategory WHERE s_name LIKE :name LIMIT 1")
    SubCategory findByName(String name);

    @Query( "SELECT * FROM SubCategory " +
            "INNER JOIN Category on Category.id = SubCategory.category " +
            "WHERE Category.id = :category")
    LiveData<List<SubCategory>> findByCategory(int category);

    @Insert
    void insert(SubCategory subcat);

    @Delete
    void delete(SubCategory subcat);

    @Update
    void update(SubCategory subcat);
}
