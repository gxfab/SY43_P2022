package com.sucelloztm.sucelloz.database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;


import com.google.common.util.concurrent.ListenableFuture;
import com.sucelloztm.sucelloz.models.Categories;

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
    ListenableFuture<Void> insertCategory(Categories category);

    /**
     * inserts a list of categories
     * @param categories list of categories to insert
     * @return list of the id of the inserted categories
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Void> insertCategories(Categories... categories);

    /**
     * updates a category based on the id of the given category
     * @param category category to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Void> updateCategory(Categories category);

    /**
     * updates a list of categories based on the ids of the given categories
     * @param categories list of categories to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Void> updateCategories(Categories... categories);

    /**
     * deletes a given category
     * @param category category to delete
     */
    @Delete
    ListenableFuture<Void> deleteCategory(Categories category);

    /**
     * deletes a list of categories
     * @param categories list of categories to delete
     */
    @Delete
    ListenableFuture<Void> deleteCategories(Categories... categories);

    /**
     * query to get all categories in a livedata
     * @return livedata with the list of all categories
     */
    @Query("SELECT * FROM categories WHERE read_only=0")
    LiveData<List<Categories>> getAllCategories();

    /**
     * query to get all categories in a list
     * @return list of all categories
     */
    @Query("SELECT * FROM categories")
    List<Categories> getCategories();

    /**
     * get categories with a given name
     * @param categoryName name to search for
     * @return category object with the wanted name
     */
    @Query("SELECT * FROM categories WHERE name LIKE :categoryName")
    LiveData<Categories> getCategoryByName(String categoryName);



}



