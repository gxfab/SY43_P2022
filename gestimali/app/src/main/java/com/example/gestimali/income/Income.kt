package com.example.gestimali.income

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "T_income")
data class Income (
    @PrimaryKey(autoGenerate = true)
    val inc_id : Int,
    val inc_year : Int,
    val inc_month : Int,
    val inc_name : String,
    val inc_planned_amount : Float,
    val inc_real_amount : Float,
    val inc_day_transaction : Int,
    val inc_mensually_fixed : Boolean
        ){
}