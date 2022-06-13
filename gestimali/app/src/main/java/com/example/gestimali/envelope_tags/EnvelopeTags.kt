package com.example.gestimali.envelope_tags

import androidx.room.Entity

@Entity(tableName = "T_enveloppe_tags",primaryKeys = ["env_id","tag_id"])
data class EnvelopeTags (
    val env_id : Int,
    val tag_id : Int
        ){
}