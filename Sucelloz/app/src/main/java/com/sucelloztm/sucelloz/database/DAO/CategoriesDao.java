package com.sucelloztm.sucelloz.database.DAO;

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

    @Query("DELETE FROM categories WHERE category_id=:categoryId")
    void deleteCategory(long categoryId);

    @Query("DELETE FROM categories WHERE category_id IN (:categoryId)")
    void deleteCategories(long... categoryId);

    @Query("SELECT * FROM categories")
    List<Categories> getCategories();

    @Transaction
    @Query("SELECT * FROM categories")
    List<CategoriesWithSubCategories> getCategoriesWithSubCategories();

}



