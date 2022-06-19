package com.example.testbare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "Depense",
    foreignKeys = [
        ForeignKey(
            entity = Mois::class,
            parentColumns = arrayOf("mois_id"),
            childColumns = arrayOf("dep_mois_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Categorie::class,
            parentColumns = arrayOf("cat_categorie"),
            childColumns = arrayOf("dep_categorie"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Depense(
    @PrimaryKey(autoGenerate = true)
    val dep_id: Int,
    @ColumnInfo(name = "dep_montant")
    var dep_montant: Float,
    @ColumnInfo(name = "dep_date")
    var dep_date: Date,
    @ColumnInfo(name = "dep_categorie")
    var dep_categorie: String,
    @ColumnInfo(name = "dep_mois_id")
    var dep_mois_id: Int,
)