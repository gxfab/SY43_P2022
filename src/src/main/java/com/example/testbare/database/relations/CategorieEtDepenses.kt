package com.example.testbare.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.testbare.database.entities.Categorie
import com.example.testbare.database.entities.Depense

data class CategorieEtDepenses(
    @Embedded
    val categorie: Categorie,
    @Relation(
        parentColumn = "cat_categorie",
        entityColumn = "dep_categorie"
    )
    val cat_depenses: List<Depense>
)