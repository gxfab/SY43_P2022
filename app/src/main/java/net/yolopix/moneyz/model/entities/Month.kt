package net.yolopix.moneyz.model.entities

import androidx.room.Entity

/**
 * This entity contains the prediction for a single month
 * @param monthNumber The number of the month in the calendar
 * @param yearNumber The year for the month
 * @param prevision The predicted amount for this month
 * @param payday The day of the month where the month should begin/end
 * @param accountUid The identifier of the corresponding account
 */
@Entity(primaryKeys = ["monthNumber", "yearNumber", "accountUid"])
class Month(
    val monthNumber: Int,
    val yearNumber: Int,
    var prevision: Double,
    var payday: Int,
    var accountUid: Int
)