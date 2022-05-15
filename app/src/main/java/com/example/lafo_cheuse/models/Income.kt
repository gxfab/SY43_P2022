package com.example.lafo_cheuse.models

import androidx.room.Entity

@Entity
class Income : MoneyChange() {
    var amount: Double = 0.0
        set(value){
            if (value > 0.0) {
                field = value
            }
        }
}