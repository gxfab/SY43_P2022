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

import com.google.common.util.concurrent.ListenableFuture;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentSum;
import com.sucelloztm.sucelloz.models.SubCategoriesWithStableExpensesAndIncome;

/**
 * interface for the subcategory entity of the dao
 */
@Dao
public interface SubCategoriesDao {
    /**
     * inserts a subcategory in the dao
     * @param subCategory subcategory to insert
     * @return id of the inserted subcategory
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Void> insertSubCategory(SubCategories subCategory);

    /**
     * inserts a list of subcategories
     * @param subCategories subcategories to insert
     * @return list of the ids of the inserted subcategories
     */
    @Insert
    List<Long> insertSubCategories(SubCategories... subCategories);

    /**
     * updates a subcategory
     * @param subCategory subcategory to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Void> updateSubCategory(SubCategories subCategory);

    /**
     * updates a list of subcategories
     * @param subCategories list of subcategories to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSubCategories(SubCategories... subCategories);

    /**
     * deletes a subcategory
     * @param subCategory subcategory to delete
     */
    @Delete
    ListenableFuture<Void> deleteSubCategory(SubCategories subCategory);

    /**
     * deletes a list of subcategories
     * @param subCategories list of subcategories to delete
     */
    @Delete
    void deleteSubCategories(SubCategories... subCategories);

    /**
     * query to get all subcategories
     * @return list of subcategories
     */
    @Query("SELECT * FROM sub_categories")
    List<SubCategories> getSubCategories();

    /**
     * query to get all the names of the subcategories
     * @return list of the names of the subcategories
     */
    @Query("SELECT name FROM sub_categories")
    List<String> getSubCategoriesNames();

    @Transaction
    @Query("SELECT * FROM sub_categories")
    List<SubCategoriesWithStableExpensesAndIncome> getSubCategoriesWithStableExpensesAndIncome();

    @Transaction
    @Query("SELECT * FROM sub_categories")
    List<SubCategoriesWithInfrequentExpensesAndIncome> getSubCategoriesWithInfrequentExpensesAndIncome();

    /**
     * query to get a specific subcategory thanks to its id
     * @param id searched id
     * @return searched subcategory
     */
    @Query("SELECT * FROM sub_categories WHERE categories_id= :id")
    LiveData<List<SubCategories>> getSubCategoriesWithCategoryId(long id);

    /**
     * query to get a specific subcategory thanks to its name
     * @param nameOfSubCategory searched name
     * @return searched subcategory
     */
    @Query("SELECT * FROM sub_categories WHERE name=:nameOfSubCategory")
    LiveData<SubCategories> getSubCategoryWithName(String nameOfSubCategory);

    /**
     * query to get a name of a subcategory thanks to its id
     * @param idOfSubCategory searched id
     * @return searched name
     */
    @Query("Select name FROM sub_categories WHERE id=:idOfSubCategory")
    LiveData<String> getSubcategoryNameWithId(long idOfSubCategory);

    @Query("SELECT *,CAST(total(infrequent_expenses.amount) AS INTEGER) AS sum_of_infrequent, " +
            "sub_categories.name AS nameOfSubCategory, " +
            "sub_categories.categories_id AS id_of_category, " +
            "infrequent_expenses.sign AS sign  " +
            "FROM sub_categories JOIN infrequent_expenses ON sub_categories.id=infrequent_expenses.sub_categories_id " +
            "WHERE infrequent_expenses.sign LIKE '+' " +
            "GROUP BY sub_categories.id")
    LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithPositiveInfrequentSum();

    @Query("SELECT *,CAST(total(infrequent_expenses.amount) AS INTEGER) AS sum_of_infrequent, " +
            "sub_categories.name AS nameOfSubCategory, " +
            "sub_categories.categories_id AS id_of_category, " +
            "infrequent_expenses.sign AS sign  " +
            "FROM sub_categories JOIN infrequent_expenses ON sub_categories.id=infrequent_expenses.sub_categories_id " +
            "WHERE infrequent_expenses.sign LIKE '-' " +
            "GROUP BY sub_categories.id")
    LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithNegativeInfrequentSum();
}