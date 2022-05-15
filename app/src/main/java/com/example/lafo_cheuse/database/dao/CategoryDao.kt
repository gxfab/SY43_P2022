package com.example.lafo_cheuse.database.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lafo_cheuse.models.Budget
import com.example.lafo_cheuse.models.Category

@Dao
interface CategoryDao {
    /* TODO : Implement queries */
    @Query("SELECT * FROM Budget")
    fun getBudgets(): LiveData<List<Budget?>?>?

    @Query("SELECT * FROM Budget")
    fun getItemsWithCursor(): Cursor?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createCategory(category: Category?)

    @Update
    fun updateBudget(budget: Budget?): Int

    @Query("DELETE FROM Budget WHERE budgetId = :bId")
    fun deleteBudget(bId: Long): Int
}