package com.example.testbare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "Budget",
    foreignKeys = [
        ForeignKey(
            entity = Mois::class,
            parentColumns = arrayOf("mois_id"),
            childColumns = arrayOf("bud_mois_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Categorie::class,
            parentColumns = arrayOf("cat_categorie"),
            childColumns = arrayOf("bud_categorie"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Budget (
    @PrimaryKey(autoGenerate = true)
    val bud_id: Int,
    @ColumnInfo(name = "bud_montant")
    var bud_montant: Float,
    @ColumnInfo(name = "bud_mois_id", index = true)
    var bud_mois_id: Int,
    @ColumnInfo(name = "bud_categorie", index = true)
    var bud_categorie: String
)
