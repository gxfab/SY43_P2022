package com.example.alovemony_ip.repository

import com.example.alovemony_ip.model.ProjetGlobalModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.alovemony_ip.repository.ProjetGlobalRepository.Singleton.databaseRef
import com.example.alovemony_ip.repository.ProjetGlobalRepository.Singleton.projetGlobalList

class ProjetGlobalRepository {

    object Singleton{
        // se connecter à la référence "projets"
        val databaseRef = FirebaseDatabase.getInstance().getReference("projetGlobal")

        // créer une liste qui va contenir nos projets
        val projetGlobalList = arrayListOf<ProjetGlobalModel>()
    }

    fun updateData(){
        // absorber les données depuis la databaseref -> liste des projets
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciens
                projetGlobalList.clear()

                // récolter la liste
                for(ds in snapshot.children){
                    // construire un objet projet
                    val projetGlobal = ds.getValue(ProjetGlobalModel::class.java)
                    //verfifer que le projet n'est pas null
                    if(projetGlobal != null){
                        // ajouter le projet à notre liste
                        projetGlobalList.add(projetGlobal)
                    }
                }
                // actionner le callback
                //callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    // mettre à jour un objet projet en bdd
    fun updateProjetGlobal(projetGlobal: ProjetGlobalModel) = databaseRef.child(projetGlobal.id).setValue(projetGlobal)

    fun insertProjetGlobal(projetGlobal: ProjetGlobalModel) = ProjetGlobalRepository.Singleton.databaseRef.child(projetGlobal.id).setValue(projetGlobal)
    // supprimer un projet en bdd
    fun deleteProjetGlobal(projetGlobal: ProjetGlobalModel) = databaseRef.child(projetGlobal.id).removeValue()

}