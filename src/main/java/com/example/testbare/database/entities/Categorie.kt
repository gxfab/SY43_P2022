package com.example.testbare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull
import org.jetbrains.annotations.PropertyKey

@Entity(tableName = "Categorie")
data class Categorie(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "cat_categorie")
    val categorie: String,
    @ColumnInfo(name = "cat_montant")
    val montant: Int
)

