package com.example.testbare.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.testbare.database.entities.Mois
import com.example.testbare.database.entities.Revenu

data class MoisEtRevenu(
    @Embedded
    val mois: Mois,
    @Relation(
        parentColumn = "mois_id",
        entityColumn = "rev_mois_id"
    )
    val mois_revenus: List<Revenu>
)