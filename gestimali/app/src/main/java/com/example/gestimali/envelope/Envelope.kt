package com.example.gestimali.envelope

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_enveloppe")
data class Envelope (
    @PrimaryKey(autoGenerate = true)
    val env_id : Int,
    val env_year : Int,
    val env_month : Int,
    val env_name : String,
    val env_planned_amount : Float,
    val env_money_used : Float
    ){

}