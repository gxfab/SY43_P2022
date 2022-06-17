package com.example.nomoola.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nomoola.database.entity.Category;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category... categories);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategory(Category... categories);

    @Delete
    void deleteCategory(Category category);

    @Query("SELECT * FROM T_CATEGORY")
    LiveData<List<Category>> getAllCategories();

    @Query("UPDATE T_CATEGORY SET CAT_NAME=:name, CAT_BUDGET_AMOUNT=:budgetAmount WHERE CAT_ID=:id")
    void updateCategory(String name, double budgetAmount, int id);

    @Query("SELECT * FROM T_CATEGORY WHERE CAT_ID=:catID")
    LiveData<Category> getCategoryFrom(int catID);

    @Query("SELECT * FROM T_CATEGORY WHERE CAT_TYPE=:type")
    LiveData<List<Category>> getCategoriesOfType(Category.CategoryType type);

    @Query("SELECT CAT_BUDGET_AMOUNT FROM T_CATEGORY WHERE CAT_ID=:catID")
    LiveData<Double> getBudgetOf(int catID);

}
