package com.example.alovemony_ip.repository

import com.example.alovemony_ip.model.DetteModel
import com.example.alovemony_ip.model.ProjetModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.alovemony_ip.repository.ProjetRepository.Singleton.databaseRef
import com.example.alovemony_ip.repository.ProjetRepository.Singleton.projetList

class ProjetRepository {

    object Singleton {
        // se connecter à la référence "projets"
        val databaseRef = FirebaseDatabase.getInstance().getReference("projets")

        // créer une liste qui va contenir nos projets
        val projetList = arrayListOf<ProjetModel>()
    }

    fun updateData(callback: () -> Unit) {
        // absorber les données depuis la databaseref -> liste des projets
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciens
                projetList.clear()

                // récolter la liste
                for (ds in snapshot.children) {
                    // construire un objet projet
                    val projet = ds.getValue(ProjetModel::class.java)

                    //verfifer que le projet n'est pas null
                    if (projet != null) {
                        // ajouter le projet à notre liste
                        projetList.add(projet)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    // mettre à jour un objet projet en bdd
    fun updateProjet(projet: ProjetModel) = databaseRef.child(projet.id).setValue(projet)
    fun insertProjet(projet: ProjetModel) = ProjetRepository.Singleton.databaseRef.child(projet.id).setValue(projet)

    // supprimer un projet en bdd
    fun deleteProjet(projet: ProjetModel) = databaseRef.child(projet.id).removeValue()
}
