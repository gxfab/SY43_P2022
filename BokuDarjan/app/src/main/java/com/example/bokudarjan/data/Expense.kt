package com.example.bokudarjan.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
class Expense {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    var categoryName : String = ""
    var amount : Float = 0F
    var date : String = ""
    var moneyIncoming : Boolean = false

    constructor()
    constructor(name : String){
        this.categoryName = name
    }
}