package net.yolopix.moneyz.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var dayOfMonth: Int,
    var categoryUid: Int,
    var name: String,
    var recurring: Boolean,
    var amount: Double
)