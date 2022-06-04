package com.example.bokudarjan.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bokudarjan.category.Category

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCategory(Category: Category);

    @Query("SELECT * FROM expense_table ORDER BY categoryName")
    fun readAllData(): LiveData<List<Category>>;


}