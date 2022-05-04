package net.yolopix.moneyz.model

import java.util.Date

class Expense(date: Date, name: String, recurring: Boolean, amount: Double) {
    var date: Date = date
    var name: String = name
    var recurring: Boolean = recurring
    var amount: Double = amount
}