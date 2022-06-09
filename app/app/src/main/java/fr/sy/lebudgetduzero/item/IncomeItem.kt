package fr.sy.lebudgetduzero.item

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Activity on account with a positive amount of money arriving on the account
 *
 * @property id ID of the Income
 * @property name Name of the transaction
 * @property value Value of the transaction in euro, always positive
 * @property date Date of transaction as a unix timestamp integer in second
 * @constructor Create empty Income item at the 01/01/2022
 */
@Entity
public class IncomeItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "Income",
    val value: Float = 0F,
    val date: Int = 1640995200
)