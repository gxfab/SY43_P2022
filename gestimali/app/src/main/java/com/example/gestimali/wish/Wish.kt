package com.example.gestimali.wish

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_wish")
data class Wish (
    @PrimaryKey(autoGenerate = true)
    val wis_id : Int,
    val wis_name : String,
    val wis_amount_needed : Float,
    val wis_amount_collected : Float,
    val wis_purchased : Boolean
        ){
}