package net.yolopix.moneyz.model.entities

import androidx.room.*

@Dao
interface CategoryDao {
	@Query("SELECT * FROM category")
	suspend fun getAll(): List<Account>

	@Insert
	suspend fun insertCategory(account: Account)
}