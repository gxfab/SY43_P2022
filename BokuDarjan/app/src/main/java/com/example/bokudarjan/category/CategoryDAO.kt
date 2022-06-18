package com.example.bokudarjan.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bokudarjan.bmonth.BMonth
import com.example.bokudarjan.category.Category

@Dao
/**
 * DAO Interface of [Category]
 */
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCategory(Category: Category);

    @Query("SELECT * FROM category_table ORDER BY categoryName")
    fun readAllData(): LiveData<List<Category>>;



    @Query("SELECT * FROM category_table WHERE categoryName = :name")
    fun getCategory(name: String): LiveData<List<Category>>

}