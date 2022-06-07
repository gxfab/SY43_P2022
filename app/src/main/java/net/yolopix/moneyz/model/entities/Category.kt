package net.yolopix.moneyz.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var name: String,
    var predictedAmount: Float,
    val monthNumber: Int,
    val yearNumber: Int,
    val accountUid: Int
) {
    override fun toString(): String {
        return name
    }
}