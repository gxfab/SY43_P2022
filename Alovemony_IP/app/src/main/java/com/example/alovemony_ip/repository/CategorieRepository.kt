package com.example.alovemony_ip.repository


import com.example.alovemony_ip.model.CategorieModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.alovemony_ip.repository.CategorieRepository.Singleton.databaseRef
import com.example.alovemony_ip.repository.CategorieRepository.Singleton.categorieList

class CategorieRepository{

    object Singleton{
        // se connecter à la référence "depenses"
        val databaseRef = FirebaseDatabase.getInstance().getReference("categorie")
        // créer une liste qui va contenir nos depenses
        val categorieList = arrayListOf<CategorieModel>()
    }

    fun updateData(callback: () -> Unit){
        // absorber les données depuis la databaseref -> liste des depenses
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciens
                categorieList.clear()
                // récolter la liste
                for(ds in snapshot.children){
                    // construire un objet depense
                    val categorie = ds.getValue(CategorieModel::class.java)
                    //verfifer que la depense n'est pas null
                    if(categorie != null){
                        // ajouter la depense à notre liste
                        categorieList.add(categorie)
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
    fun updateCategorie(categorie : CategorieModel) = databaseRef.child(categorie.id).setValue(categorie)

    fun insertCategorie(categorie : CategorieModel) = databaseRef.child(categorie.id).setValue(categorie)

    // supprimer un objet depense en bdd
    fun deleteCategorie(categorie : CategorieModel) = databaseRef.child(categorie.id).removeValue()
}