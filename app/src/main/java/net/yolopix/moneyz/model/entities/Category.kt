package net.yolopix.moneyz.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.yolopix.moneyz.model.ExpenseType

/**
 * This entity represents a category that can be used to sort expenses by type
 * Categories have a predicted amount that makes up a prediction for a month
 * @param name The name of the category
 * @param predictedAmount The amount of money predicted to be spent this month in this category
 * @param monthNumber Month associated with the category
 * @param yearNumber The year for the month
 * @param accountUid The identifier of the account in which the category has been created
 */
@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var name: String,
    var predictedAmount: Float,
    val monthNumber: Int,
    val yearNumber: Int,
    val accountUid: Int,
    val expenseType: ExpenseType
) {
    override fun toString(): String {
        return name
    }
}