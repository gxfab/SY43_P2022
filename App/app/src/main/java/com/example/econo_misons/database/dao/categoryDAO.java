package com.example.econo_misons.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.econo_misons.database.models.Category;


import java.util.List;

//Data access object with all the SQL Query for the category functions
@Dao
public interface categoryDAO {
    @Insert
    long addCategory(Category cat);
    @Update
    void updateCategory(Category cat);
    @Delete
    void deleteCategory(Category cat);
    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getAllCategories();
    @Query("SELECT * FROM Category WHERE ID = :catID")
    LiveData<List<Category>> getCategoryByID(int catID);

}
