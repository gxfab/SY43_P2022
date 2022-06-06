package net.yolopix.moneyz.model.entities

import androidx.room.Entity

@Entity(primaryKeys = ["monthNumber", "yearNumber", "accountUid"])
class Month(
    val monthNumber: Int,
    val yearNumber: Int,
    var prevision: Double,
    var payday: Int,
    var accountUid: Int
)