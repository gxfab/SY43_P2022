package net.yolopix.moneyz.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var name: String,
    var predictedAmount : Float,
    var monthNumber: Int,
    var yearNumber: Int
)