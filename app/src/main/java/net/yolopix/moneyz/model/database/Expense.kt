package net.yolopix.moneyz.model.database

import java.util.Date
import androidx.room.*

@Entity
data class Expense(
    @PrimaryKey val uid: Int,
    var date: Date,
    var name: String,
    var recurring: Boolean,
    var amount: Double
)