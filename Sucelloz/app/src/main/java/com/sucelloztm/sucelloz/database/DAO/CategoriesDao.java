package com.sucelloztm.sucelloz.database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.Categories;

import java.util.List;

/**
 * Interface for the categories entity of the database
 */
@Dao
public interface CategoriesDao {
    /**
     * Inserts a specific category
     *
     * @param category category to insert
     * @return id of the inserted category
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCategory(Categories category);

    /**
     * Inserts a list of categories
     *
     * @param categories list of categories to insert
     * @return list of the id of the inserted categories
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertCategories(Categories... categories);

    /**
     * Updates a category
     *
     * @param category category to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategory(Categories category);

    /**
     * Updates a list of Categories
     *
     * @param categories list of categories to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategories(Categories... categories);

    /**
     * Deletes a given category
     *
     * @param category category to delete
     */
    @Delete
    void deleteCategory(Categories category);

    /**
     * Deletes a list of categories
     *
     * @param categories list of categories to delete
     */
    @Delete
    void deleteCategories(Categories... categories);

    /**
     * Query to get all categories with read_only=0
     *
     * @return livedata with the list of all categories
     */
    @Query("SELECT * FROM categories WHERE read_only=0")
    LiveData<List<Categories>> getAllCategories();

    /**
     * Query to get all categories in a list
     *
     * @return list of all categories
     */
    @Query("SELECT * FROM categories")
    List<Categories> getCategories();

    /**
     * Get categories with a given name
     *
     * @param categoryName name to search for
     * @return category object with the wanted name
     */
    @Query("SELECT * FROM categories WHERE name LIKE :categoryName")
    Categories getCategoryByName(String categoryName);


}



