package com.example.gestimali.income_tags

import androidx.room.Entity

@Entity(tableName = "T_income_tags",primaryKeys = ["inc_id","tag_id"])
data class IncomeTags (
    val inc_id : Int,
    val tag_id : Int
        ){
}