package fr.sy.lebudgetduzero.database.dao

import androidx.room.*
import fr.sy.lebudgetduzero.item.SpentItem
import fr.sy.lebudgetduzero.item.TypeItem

/**
 * Balance global DAO
 */
@Dao
interface BalanceDao {

    /**
     * Insert basical spent types for the creation of the database
     *
     */
    @Query("INSERT INTO TypeItem (id,name,value_for_month,value_for_lastmonth) VALUES " +
            "(1,'Food',100,0)," +
            "(2,'Services',0,0),"+
            "(3,'Car',0,0)," +
            "(4,'Lodging',0,0)," +
            "(5,'Social Security',0,0)," +
            "(6,'Health',0,0)," +
            "(7,'Hobbies',0,0)," +
            "(8,'Savings',0,0)," +
            "(9,'Events',0,0)," +
            "(10,'Holidays',0,0)," +
            "(11,'Other',0,0)")
    fun insertTypes()

    /**
     * Select all informations of a spent type
     *
     * @param id Id of the type asked
     * @return TypeItem
     */
    @Query("SELECT * FROM TypeItem WHERE id=:id")
    fun selectTypeInfo(id:Int):TypeItem

    @Query("SELECT value_for_month FROM TypeItem WHERE id=:idType")
    fun selectBudgetValueType(idType:Int):Int

    /**
     * Update the budget value of a type
     *
     * @param value value to set
     * @param idType Id tof the type to update
     */
    @Query("UPDATE TypeItem SET value_for_month=:value WHERE id=:idType")
    fun updateBudgetType(value:Int, idType:Int)

    /**
     * Select budget global value
     *
     * @return Integer in euro
     */
    @Query("SELECT SUM(value_for_month) FROM TypeItem")
    fun selectBudgetGlobalValue():Int

}