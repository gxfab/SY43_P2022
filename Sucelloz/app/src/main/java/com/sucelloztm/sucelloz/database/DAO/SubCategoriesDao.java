package com.sucelloztm.sucelloz.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.SubCategories;

@Dao
public interface SubCategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSubCategory(SubCategories subCategory);

    @Insert
    public void insertSubCategories(SubCategories... subCategories);

    @Update
    public void updateSubCategory(SubCategories subCategory);

    @Update
    public void updateSubCategories(SubCategories... subCategories);

    @Delete
    public void deleteSubCategory(SubCategories subCategory);

    @Delete
    public void deleteSubCategories(SubCategories... subCategories);
}
