package com.example.gestimali.fix_expense_tags

import androidx.room.Entity

@Entity(tableName = "T_fixed_expense_tags",primaryKeys = ["exp_id","tag_id"])
data class FixedExpenseTags (
    val exp_id : Int,
    val tag_id : Int
        ){
}