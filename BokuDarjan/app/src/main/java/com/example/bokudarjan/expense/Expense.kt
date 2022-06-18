package com.example.bokudarjan.expense

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
/**
 * A database entity class used to represent expense.
 */
class Expense {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    var month : Int = 0
    var categoryName : String = ""
    var name : String = ""
    var amount : Float = 0F
    var date : String = ""

    constructor()
    constructor(categoryName: String){
        this.categoryName = name
    }

    constructor(
        categoryName: String,
        name : String,
        amount: Float,
        date: String,
        month: Int,
    ) {
        this.categoryName = categoryName
        this.name = name
        this.amount = amount
        this.date = date
        this.month = month
    }

}