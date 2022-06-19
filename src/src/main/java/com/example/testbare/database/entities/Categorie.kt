package com.example.testbare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categorie")
data class Categorie(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "cat_categorie")
    val cat_categorie: String
)

