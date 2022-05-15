package com.example.nomoola.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nomoola.database.entity.UnderCategory;

import java.util.List;

@Dao
public interface UnderCategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUnderCategory(UnderCategory...underCategories);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUnderCategory(UnderCategory...underCategories);

    @Delete
    void deleteCategory(UnderCategory underCategory);

    @Query("SELECT * FROM T_UNDERCATEGORY")
    LiveData<List<UnderCategory>> getAllUnderCategories();
}
