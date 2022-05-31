package net.yolopix.moneyz.model.entities

import androidx.room.*

@Dao
interface CategoryDao {
	@Query("SELECT * FROM category")
	suspend fun getAll(): List<Category>

	@Insert
	suspend fun insertCategory(account: Category)
}