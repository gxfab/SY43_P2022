package com.example.lafo_cheuse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class MoneyChange {

    @PrimaryKey(autoGenerate = true)
    var moneyChangeId: Long = 0

    var frequency: Frequency = Frequency.OUNCE_A_DAY
    var category: String = ""
}