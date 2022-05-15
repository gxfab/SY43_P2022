package com.example.lafo_cheuse.models

import androidx.room.Entity

@Entity
class Expense(frequency: Frequency, category: Category, amount: Double) :
    MoneyChange(frequency, category) {
    var amount: Double = 0.0
        set(value){
            if (value <= 0.0) {
                field = value
            }
             else {
                 throw Exception("amount is positive!")
            }
        }

    init {
        if(amount <= 0.0) {
            this.amount = amount
        } else {
            throw java.lang.Exception("The amount is positive!")
        }
    }
}