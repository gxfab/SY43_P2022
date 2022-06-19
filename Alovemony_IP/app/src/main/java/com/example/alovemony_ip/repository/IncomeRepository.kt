package com.example.alovemony_ip.repository

import com.example.alovemony_ip.model.CategorieModel
import com.example.alovemony_ip.model.IncomeModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class IncomeRepository {
    object Singleton{
        // se connecter à la référence "depenses"
        val databaseRef = FirebaseDatabase.getInstance().getReference("income")
        // créer une liste qui va contenir nos depenses
        val incomeList = arrayListOf<IncomeModel>()
    }

    fun updateData(callback: () -> Unit){
        // absorber les données depuis la databaseref -> liste des depenses
        IncomeRepository.Singleton.databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciens
                IncomeRepository.Singleton.incomeList.clear()

                // récolter la liste
                for(ds in snapshot.children){
                    // construire un objet depense
                    val income = ds.getValue(IncomeModel::class.java)

                    //verfifer que la depense n'est pas null
                    if(income != null){
                        // ajouter la depense à notre liste
                        IncomeRepository.Singleton.incomeList.add(income)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun updateIncome(income: IncomeModel) = IncomeRepository.Singleton.databaseRef.child(income.id).setValue(income)

    fun insertIncome(income : IncomeModel) = IncomeRepository.Singleton.databaseRef.child(income.id).setValue(income)

    // supprimer un objet depense en bdd
    fun deleteIncome(income : IncomeModel) = IncomeRepository.Singleton.databaseRef.child(income.id).removeValue()
}