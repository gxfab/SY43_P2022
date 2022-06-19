package com.example.testbare.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Utilisateur")
data class Utilisateur(
    @PrimaryKey(autoGenerate = false)
    val usr_mail : String,
    val usr_mdp : String,
    val usr_mdp_actif : Boolean
)