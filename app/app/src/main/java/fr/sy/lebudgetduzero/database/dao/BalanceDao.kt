package fr.sy.lebudgetduzero.database.dao

import androidx.room.*
import fr.sy.lebudgetduzero.item.SpentItem

/**
 * Balance global DAO
 */
@Dao
interface BalanceDao {

    @Query("INSERT INTO TypeItem (id,name,value_for_month,value_for_lastmonth) VALUES " +
            "(1,'Food',0,0)," +
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

}