package com.example.gestimali.tag

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_tag")
data class Tag (
    @PrimaryKey(autoGenerate = true)
    val tag_id : Int,
    val tag_name : String,
    val tag_desc : String
        ){
}