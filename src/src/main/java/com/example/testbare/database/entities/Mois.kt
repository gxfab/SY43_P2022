package com.example.testbare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Mois",
    indices = [Index(value = ["mois_annee", "mois_mois"], unique = true)]
)
data class Mois (
    @PrimaryKey(autoGenerate = true)
    val mois_id: Int,
    @ColumnInfo(name = "mois_annee")
    var mois_annee: Int,
    @ColumnInfo(name = "mois_mois")
    var mois_mois: Int
)