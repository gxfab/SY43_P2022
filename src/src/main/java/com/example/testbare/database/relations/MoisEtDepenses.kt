package com.example.testbare.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.testbare.database.entities.Depense
import com.example.testbare.database.entities.Mois

data class MoisEtDepenses(
    @Embedded
    val mois: Mois,
    @Relation(
        parentColumn = "mois_id",
        entityColumn = "dep_mois_id"
    )
    val mois_depenses: List<Depense>
) {
    fun toTypedArray(): Array<Depense> {
        val lenght = mois_depenses.size
        val arrayDepense = arrayOf<Depense>()
        for (indice: Int in mois_depenses.indices){
            arrayDepense[indice] = mois_depenses[indice]
        }
        return arrayDepense
    }
}
