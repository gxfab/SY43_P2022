package com.example.gestimali.fixexpense

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "T_fixed_expense")
data class FixedExpense (
    @PrimaryKey(autoGenerate = true)
    val exp_id : Int,
    val exp_year : Int,
    val exp_month : Int,
    val exp_name : String,
    val exp_planned_amount : Float,
    val exp_real_amount : Float,
    val exp_day_transaction : Int,
    val exp_mensually_fix : Boolean,
        ){
}