package fr.sy.lebudgetduzero.item

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Activity on account with a negative impact on the bank account.
 *
 * @property id ID of the spent
 * @property name Name of the transaction
 * @property value Value of the transaction in euro, always positive
 * @property id_type ID of the type of spent
 * @property date Date of transaction as a unix timestamp integer in second
 * @constructor Create empty Spent item at the 01/01/2022
 */
@Entity
public class SpentItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "Income",
    val value: Float = 0F,
    val id_type: Int =0,
    val date: Int = 1640995200
)