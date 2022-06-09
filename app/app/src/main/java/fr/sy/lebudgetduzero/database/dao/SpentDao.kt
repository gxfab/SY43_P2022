package fr.sy.lebudgetduzero.database.dao

import androidx.room.*
import fr.sy.lebudgetduzero.item.SpentItem


@Dao
interface SpentDao {
    @Insert
    fun insertAll(vararg items: SpentItem)

    @Delete
    fun delete(item: SpentItem)

    @Query("DELETE FROM SpentItem")
    fun deleteAll()

    @Query("SELECT * FROM SpentItem ORDER BY date DESC")
    fun getAll(): List<SpentItem>

    @Query("SELECT SUM(value) FROM SpentItem WHERE date> :timestampBegin")
    fun getSpentValue(timestampBegin: Int): Float
}