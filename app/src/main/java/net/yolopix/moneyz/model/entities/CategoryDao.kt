package net.yolopix.moneyz.model.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category WHERE monthNumber == :monthNumber AND yearNumber == :yearNumber AND accountUid == :accountUid")
    suspend fun getCategoriesForMonth(
        monthNumber: Int,
        yearNumber: Int,
        accountUid: Int
    ): List<Category>


    @Insert
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)
}