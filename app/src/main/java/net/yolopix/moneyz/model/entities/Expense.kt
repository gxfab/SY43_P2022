package net.yolopix.moneyz.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This entitiy represents an expense
 * @param dayOfMonth Day of the month when the expense has been made
 * @param categoryUid The category in which the expense is classed
 * @param name A name/description of the expense
 * @param recurring If the expense should be automatically repeated each month
 * @param amount Amount of money spent
 */
@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var dayOfMonth: Int,
    var categoryUid: Int,
    var name: String,
    var recurring: Boolean,
    var amount: Double
)