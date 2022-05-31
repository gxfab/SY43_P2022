package net.yolopix.moneyz.model.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
	@Query("SELECT * FROM category")
	suspend fun getAll(): List<Category>

	@Insert
	suspend fun insertCategory(category: Category)
}