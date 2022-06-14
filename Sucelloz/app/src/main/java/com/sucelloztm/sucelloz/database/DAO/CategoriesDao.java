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

/**
 * interface for the categories entity of the database
 */
@Dao
public interface CategoriesDao {
    /**
     * inserts a specific category
     * @param category category to insert
     * @return id of the inserted category
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCategory(Categories category);

    /**
     * inserts a list of categories
     * @param categories list of categories to insert
     * @return list of the id of the inserted categories
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertCategories(Categories... categories);

    /**
     * updates a category based on the id of the given category
     * @param category category to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategory(Categories category);

    /**
     * updates a list of categories based on the ids of the given categories
     * @param categories list of categories to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategories(Categories... categories);

    /**
     * deletes a given category
     * @param category category to delete
     */
    @Delete
    void deleteCategory(Categories category);

    /**
     * deletes a list of categories
     * @param categories list of categories to delete
     */
    @Delete
    void deleteCategories(Categories... categories);

    /**
     * query to get all categories in a livedata
     * @return livedata with the list of all categories
     */
    @Query("SELECT * FROM categories")
    LiveData<List<Categories>> getAllCategories();

    /**
     * query to get all categories in a list
     * @return list of all categories
     */
    @Query("SELECT * FROM categories")
    List<Categories> getCategories();

    @Transaction
    @Query("SELECT * FROM categories")
    List<CategoriesWithSubCategories> getCategoriesWithSubCategories();

    /**
     * get categories with a given name
     * @param categoryName name to search for
     * @return category object with the wanted name
     */
    @Query("SELECT * FROM categories WHERE name LIKE :categoryName")
    Categories getCategoryByName(String categoryName);

}



