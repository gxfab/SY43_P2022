package fr.sy.lebudgetduzero.database.dao

import androidx.room.*
import fr.sy.lebudgetduzero.item.SpentItem

/**
 * Spent dao allowing to do database request concerning spent table
 */
@Dao
interface SpentDao {
    /**
     * Insert spend to spent table
     *
     * @param items A Spent Item
     */
    @Insert
    fun insertAll(vararg items: SpentItem)

    /**
     * Delete specified spent of spent table
     *
     * @param item A Spent Item
     */
    @Delete
    fun delete(item: SpentItem)

    /**
     * Delete all spend from spent table
     *
     */
    @Query("DELETE FROM SpentItem")
    fun deleteAll()

    /**
     * Get all spend from spent table
     *
     * @return
     */
    @Query("SELECT * FROM SpentItem ORDER BY date DESC")
    fun getAll(): List<SpentItem>

    /**
     * Get amount of all spend done after a specified time/date
     *
     * @param timestampBegin Int as unix timestamp as the time limit. We'll count spent which appears only after this date
     * @return Float of the amount in euro
     */
    @Query("SELECT SUM(value) FROM SpentItem WHERE date> :timestampBegin")
    fun getSpentValue(timestampBegin: Int): Float
}