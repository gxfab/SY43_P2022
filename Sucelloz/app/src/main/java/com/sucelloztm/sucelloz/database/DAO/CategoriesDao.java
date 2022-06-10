package com.sucelloztm.sucelloz.database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;
import androidx.room.Transaction;
import androidx.room.Query;

import java.util.List;


import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.CategoriesWithSubCategories;

@Dao
public interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCategory(Categories category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertCategories(Categories... categories);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategory(Categories category);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategories(Categories... categories);

    @Delete
    void deleteCategory(Categories category);

    @Delete
    void deleteCategories(Categories... categories);

    @Query("SELECT * FROM categories")
    LiveData<List<Categories>> getAllCategories();

    @Transaction
    @Query("SELECT * FROM categories")
    List<CategoriesWithSubCategories> getCategoriesWithSubCategories();

    @Query("SELECT * FROM categories WHERE name LIKE :categoryName")
    Categories getCategoryByName(String categoryName);

}



