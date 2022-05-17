package com.example.testbare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "Depense")
data class Depense(
    @PrimaryKey(autoGenerate = true)
    val dep_id: Int,
    @NonNull
    @ColumnInfo(name = "dep_categorie")
    var dep_categorie: String,
    @NonNull
    @ColumnInfo(name = "dep_montant")
    var dep_montant: Int,
){
    override fun toString() : String{
        return this.dep_categorie + " " + this.dep_montant
    }

    fun getDepenseCategorie() : String { return dep_categorie}

    fun getDepenseMontant() : Int { return dep_montant}
}