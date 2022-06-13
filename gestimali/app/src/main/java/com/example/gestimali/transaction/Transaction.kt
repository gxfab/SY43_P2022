package com.example.gestimali.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "T_transaction",primaryKeys = ["env_id","tra_id"])
data class Transaction (
    val env_id : Int,
    @PrimaryKey(autoGenerate = true)
    val tra_id : Int,
    val tra_amount : Float,
    val tra_date : Date
        ){}