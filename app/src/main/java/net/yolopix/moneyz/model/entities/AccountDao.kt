package net.yolopix.moneyz.model.entities

import androidx.room.*

@Dao
interface AccountDao {
	@Query("SELECT * FROM account")
	suspend fun getAll(): List<Account>

	@Query("SELECT DISTINCT * FROM account WHERE uid == :uid ")
	suspend fun getAccountById(uid: Int): Account

	@Insert
	suspend fun insertAccount(account: Account)
}