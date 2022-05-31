package net.yolopix.moneyz.model.entities

import androidx.room.*
import java.util.Date

@Entity
class Month(
    @PrimaryKey val uid: Int,
    var prevision: Double,
    var payday: Int,
    var accountUid: Int
)