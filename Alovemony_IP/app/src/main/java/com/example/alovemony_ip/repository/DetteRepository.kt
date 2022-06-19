package com.example.alovemony_ip.repository

import com.example.alovemony_ip.model.DetteModel
import com.example.alovemony_ip.model.ProjetModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.alovemony_ip.repository.DetteRepository.Singleton.databaseRef
import com.example.alovemony_ip.repository.DetteRepository.Singleton.detteList

class DetteRepository {

    object Singleton{
        // se connecter à la référence "projets"
        val databaseRef = FirebaseDatabase.getInstance().getReference("dette")

        // créer une liste qui va contenir nos projets
        val detteList = arrayListOf<DetteModel>()
    }

    fun updateData(){
        // absorber les données depuis la databaseref -> liste des projets
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciens
                detteList.clear()
                // récolter la liste
                for(ds in snapshot.children){
                    // construire un objet projet
                    val dette = ds.getValue(DetteModel::class.java)

                    //verfifer que le projet n'est pas null
                    if(dette != null){
                        // ajouter le projet à notre liste
                        detteList.add(dette)
                    }
                }
                // actionner le callback
               // callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    // mettre à jour un objet projet en bdd
    fun updateDette(dette: DetteModel) = databaseRef.child(dette.id).setValue(dette)
    fun insertDette(dette : DetteModel) = DetteRepository.Singleton.databaseRef.child(dette.id).setValue(dette)
    // supprimer un projet en bdd
    fun deleteDette(dette: DetteModel) = databaseRef.child(dette.id).removeValue()

}