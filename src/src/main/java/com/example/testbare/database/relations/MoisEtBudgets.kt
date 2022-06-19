package com.example.testbare.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.testbare.database.entities.Budget
import com.example.testbare.database.entities.Mois

data class MoisEtBudgets(
    @Embedded
    val mois: Mois,
    @Relation(
        parentColumn = "mois_id",
        entityColumn = "bud_mois_id"
    )
    val mois_budgets: List<Budget>
)