package com.example.alovemony_ip.repository

import com.example.alovemony_ip.model.DepenseModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.alovemony_ip.repository.DepenseRepository.Singleton.databaseRef
import com.example.alovemony_ip.repository.DepenseRepository.Singleton.depenseList

class DepenseRepository {

    object Singleton{
        // se connecter à la référence "depenses"
        val databaseRef = FirebaseDatabase.getInstance().getReference("depenses")
        // créer une liste qui va contenir nos depenses
        val depenseList = arrayListOf<DepenseModel>()
    }

    fun updateData(callback: () -> Unit){
        // absorber les données depuis la databaseref -> liste des depenses
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciens
                depenseList.clear()

                // récolter la liste
                for(ds in snapshot.children){
                    // construire un objet depense
                    val depense = ds.getValue(DepenseModel::class.java)
                    // System.out.println("test : " + depense)
                    //verfifer que la depense n'est pas null
                    if(depense != null){
                        // ajouter la depense à notre liste
                        depenseList.add(depense)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    // mettre à jour un objet depense en bdd
    fun updateDepense(depense: DepenseModel) = databaseRef.child(depense.id).setValue(depense)

    fun insertDepense(depense: DepenseModel) = databaseRef.child(depense.id).setValue(depense)

    // supprimer un objet depense en bdd
    fun deleteDepense(depense: DepenseModel) = databaseRef.child(depense.id).removeValue()
}