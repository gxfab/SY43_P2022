package net.yolopix.moneyz.model.entities

import java.util.Date
import androidx.room.*

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var dayOfMonth: Int,
    var monthUid: Int,
    var name: String,
    var recurring: Boolean,
    var amount: Double
)