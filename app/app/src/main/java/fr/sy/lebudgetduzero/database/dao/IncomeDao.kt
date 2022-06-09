package fr.sy.lebudgetduzero.database.dao

import androidx.room.*
import fr.sy.lebudgetduzero.item.IncomeItem


@Dao
interface IncomeDao {
    @Insert
    fun insertAll(vararg items: IncomeItem)

    @Delete
    fun delete(item: IncomeItem)

    @Query("DELETE FROM IncomeItem")
    fun deleteAll()

    @Query("SELECT * FROM IncomeItem ORDER BY date DESC")
    fun getAll(): List<IncomeItem>

    @Query("SELECT SUM(value) FROM IncomeItem WHERE date> :timestampBegin")
    fun getIncomeValue(timestampBegin: Int): Float
}