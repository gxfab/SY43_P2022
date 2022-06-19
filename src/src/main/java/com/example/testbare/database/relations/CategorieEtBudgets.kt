package com.example.testbare.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.testbare.database.entities.Budget
import com.example.testbare.database.entities.Categorie

data class CategorieEtBudgets(
    @Embedded
    val categorie: Categorie,
    @Relation(
        parentColumn = "cat_categorie",
        entityColumn = "bud_categorie"
    )
    val cat_budgets: List<Budget>
)