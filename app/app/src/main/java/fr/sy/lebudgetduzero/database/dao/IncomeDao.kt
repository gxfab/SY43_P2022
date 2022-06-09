package fr.sy.lebudgetduzero.database.dao

import androidx.room.*
import fr.sy.lebudgetduzero.item.IncomeItem

/**
 * Income dao allowing to do database request concerning income table
 */
@Dao
interface IncomeDao {
    /**
     * Insert incomes to income table
     *
     * @param items An Income Item
     */
    @Insert
    fun insertAll(vararg items: IncomeItem)

    /**
     * Delete specified income of income table
     *
     * @param item An Income Item
     */
    @Delete
    fun delete(item: IncomeItem)

    /**
     * Delete all incomes of income table
     *
     */
    @Query("DELETE FROM IncomeItem")
    fun deleteAll()

    /**
     * Get all incomes of income table
     *
     * @return
     */
    @Query("SELECT * FROM IncomeItem ORDER BY date DESC")
    fun getAll(): List<IncomeItem>

    /**
     * Get amount of all incomes done after a specified time/date
     *
     * @param timestampBegin Int as unix timestamp as the time limit. We'll count income which appears only after this date
     * @return Float of the amount in euro
     */
    @Query("SELECT SUM(value) FROM IncomeItem WHERE date> :timestampBegin")
    fun getIncomeValue(timestampBegin: Int): Float
}