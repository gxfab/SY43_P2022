package com.example.testbare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Revenu",
    foreignKeys = [
        ForeignKey(
            entity = Mois::class,
            parentColumns = arrayOf("mois_id"),
            childColumns = arrayOf("rev_mois_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Revenu(
    @PrimaryKey(autoGenerate = true)
    val rev_id : Int,
    @ColumnInfo(name = "rev_montant")
    var rev_montant : Float,
    @ColumnInfo(name = "rev_source")
    var rev_source : String,
    @ColumnInfo(name = "rev_mois_id", index = true)
    var rev_mois_id: Int
)