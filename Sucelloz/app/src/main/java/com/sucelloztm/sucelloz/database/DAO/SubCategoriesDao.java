package com.sucelloztm.sucelloz.database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentSum;

import java.util.List;

/**
 * Interface for the subcategory entity of the dao
 */
@Dao
public interface SubCategoriesDao {
    /**
     * Inserts a subcategory
     *
     * @param subCategory subcategory to insert
     * @return id of the inserted subcategory
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSubCategory(SubCategories subCategory);

    /**
     * Inserts a list of subcategories
     *
     * @param subCategories subcategories to insert
     * @return list of the ids of the inserted subcategories
     */
    @Insert
    List<Long> insertSubCategories(SubCategories... subCategories);

    /**
     * Updates a subcategory
     *
     * @param subCategory subcategory to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSubCategory(SubCategories subCategory);

    /**
     * Updates a list of subcategories
     *
     * @param subCategories list of subcategories to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSubCategories(SubCategories... subCategories);

    /**
     * Deletes a subcategory
     *
     * @param subCategory subcategory to delete
     */
    @Delete
    void deleteSubCategory(SubCategories subCategory);

    /**
     * Deletes a list of subcategories
     *
     * @param subCategories list of subcategories to delete
     */
    @Delete
    void deleteSubCategories(SubCategories... subCategories);

    /**
     * Query to get all subcategories
     *
     * @return list of subcategories
     */
    @Query("SELECT * FROM sub_categories")
    List<SubCategories> getSubCategories();

    /**
     * Query to get all the names of the subcategories
     *
     * @return list of the names of the subcategories
     */
    @Query("SELECT name FROM sub_categories")
    List<String> getSubCategoriesNames();

    /**
     * Query to get a specific subcategory thanks to its id
     *
     * @param id searched id
     * @return searched subcategory
     */
    @Query("SELECT * FROM sub_categories WHERE categories_id= :id")
    LiveData<List<SubCategories>> getSubCategoriesWithCategoryId(long id);

    /**
     * Query to get a specific subcategory thanks to its name
     *
     * @param nameOfSubCategory searched name
     * @return searched subcategory
     */
    @Query("SELECT * FROM sub_categories WHERE name=:nameOfSubCategory")
    SubCategories getSubCategoryWithName(String nameOfSubCategory);

    /**
     * Query to get a name of a subcategory thanks to its id
     *
     * @param idOfSubCategory searched id
     * @return searched name
     */
    @Query("Select name FROM sub_categories WHERE id=:idOfSubCategory")
    String getSubcategoryNameWithId(long idOfSubCategory);


    /**
     * Query to get the sum of all positive infrequent expenses from all sub-categories
     *
     * @return live data of a list of all sum of all positive infrequent expenses from all sub-categories
     */
    @Query("SELECT *,CAST(total(infrequent_expenses.amount) AS INTEGER) AS sum_of_infrequent, " +
            "sub_categories.name AS nameOfSubCategory, " +
            "sub_categories.categories_id AS id_of_category, " +
            "infrequent_expenses.sign AS sign  " +
            "FROM sub_categories JOIN infrequent_expenses ON sub_categories.id=infrequent_expenses.sub_categories_id " +
            "WHERE infrequent_expenses.sign LIKE '+' " +
            "GROUP BY sub_categories.id")
    LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithPositiveInfrequentSum();

    /**
     * Query to get the sum of all negative infrequent expenses from all sub-categories
     *
     * @return live data of a list of all sum of all negative infrequent expenses from all sub-categories
     */
    @Query("SELECT *,CAST(total(infrequent_expenses.amount) AS INTEGER) AS sum_of_infrequent, " +
            "sub_categories.name AS nameOfSubCategory, " +
            "sub_categories.categories_id AS id_of_category, " +
            "infrequent_expenses.sign AS sign  " +
            "FROM sub_categories JOIN infrequent_expenses ON sub_categories.id=infrequent_expenses.sub_categories_id " +
            "WHERE infrequent_expenses.sign LIKE '-' " +
            "GROUP BY sub_categories.id")
    LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithNegativeInfrequentSum();
}