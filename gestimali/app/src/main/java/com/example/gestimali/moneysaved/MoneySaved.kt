package com.example.gestimali.moneysaved

import androidx.room.Entity

@Entity(tableName = "T_money_saved",primaryKeys = ["mon_year","mon_month","wis_id"])
data class MoneySaved (
        val mon_year : Int,
        val mon_month : Int,
        val wis_id : Int,
        val mon_amount : Float
        ){
}