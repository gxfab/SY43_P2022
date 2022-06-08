package fr.sy.lebudgetduzero.database.dao

import androidx.room.*
import fr.sy.lebudgetduzero.IncomeItem


@Dao
interface IncomeDao {
    @Insert
    fun insertAll(vararg items: IncomeItem)

    @Delete
    fun delete(item: IncomeItem)

    @Query("DELETE FROM IncomeItem")
    fun deleteAll()

    @Query("SELECT * FROM IncomeItem")
    fun getAll(): List<IncomeItem>
}