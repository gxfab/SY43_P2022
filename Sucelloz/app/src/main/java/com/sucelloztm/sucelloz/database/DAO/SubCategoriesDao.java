package com.sucelloztm.sucelloz.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;
import androidx.room.Transaction;
import androidx.room.Query;
import java.util.List;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategoriesWithStableExpensesAndIncome;

@Dao
public interface SubCategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubCategory(SubCategories subCategory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubCategories(SubCategories... subCategories);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSubCategory(SubCategories subCategory);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSubCategories(SubCategories... subCategories);

    @Delete
    void deleteSubCategory(SubCategories subCategory);

    @Delete
    void deleteSubCategories(SubCategories... subCategories);

    @Query("SELECT * FROM sub_categories")
    List<SubCategories> getSubCategories();

    @Transaction
    @Query("SELECT * FROM sub_categories")
    List<SubCategoriesWithStableExpensesAndIncome> getSubCategoriesWithStableExpensesAndIncome();

    @Transaction
    @Query("SELECT * FROM sub_categories")
    List<SubCategoriesWithInfrequentExpensesAndIncome> getSubCategoriesWithInfrequentExpensesAndIncome();


}