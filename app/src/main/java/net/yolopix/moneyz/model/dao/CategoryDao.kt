package net.yolopix.moneyz.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import net.yolopix.moneyz.model.entities.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category WHERE monthNumber == :monthNumber AND yearNumber == :yearNumber AND accountUid == :accountUid")
    suspend fun getCategoriesForMonth(
        monthNumber: Int,
        yearNumber: Int,
        accountUid: Int
    ): List<Category>

    @Query("SELECT SUM(predictedAmount) FROM category WHERE monthNumber == :monthNumber AND yearNumber == :yearNumber AND accountUid == :accountUid")
    suspend fun calculatePredictedAmount(
        monthNumber: Int,
        yearNumber: Int,
        accountUid: Int
    ):Float

    @Query("SELECT predictedAmount FROM category WHERE monthNumber == :monthNumber AND yearNumber == :yearNumber AND accountUid == :accountUid AND uid == :uid")
    suspend fun retrieveSinglePredictedAmount(
        monthNumber: Int,
        yearNumber: Int,
        accountUid: Int,
        uid : Int
    ):Float

    @Query("SELECT uid FROM category WHERE monthNumber == :monthNumber AND yearNumber == :yearNumber AND accountUid == :accountUid")
    suspend fun getCategoriesUidsForMonth(
        monthNumber: Int,
        yearNumber: Int,
        accountUid: Int
    ): List<Int>

    @Query("SELECT * FROM Category WHERE uid = :categoryUid")
    suspend fun getCategory(categoryUid: Int): Category

    @Insert
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)
}