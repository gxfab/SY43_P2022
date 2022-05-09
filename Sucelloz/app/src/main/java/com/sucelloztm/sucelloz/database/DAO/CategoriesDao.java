package com.sucelloztm.sucelloz.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.Categories;

@Dao
public interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCategory(Categories category);

    @Insert
    public void insertCategories(Categories... categories);

    @Update
    public void updateCategory(Categories category);

    @Update
    public void updateCategories(Categories... categories);

    @Delete
    public void deleteCategory(Categories category);

    @Delete
    public void deleteCategories(Categories... categories);

}

