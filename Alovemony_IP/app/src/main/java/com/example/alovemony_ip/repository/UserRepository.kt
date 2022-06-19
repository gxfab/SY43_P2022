package com.example.alovemony_ip.repository

import com.example.alovemony_ip.model.CategorieModel
import com.example.alovemony_ip.model.IncomeModel
import com.example.alovemony_ip.model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserRepository {
    object Singleton{
        // se connecter à la référence "depenses"
        val databaseRef = FirebaseDatabase.getInstance().getReference("user")
        // créer une liste qui va contenir nos depenses
        val userList = arrayListOf<UserModel>()
    }

    fun updateData(callback: () -> Unit){
        // absorber les données depuis la databaseref -> liste des depenses
        UserRepository.Singleton.databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciens
                UserRepository.Singleton.userList.clear()

                // récolter la liste
                for(ds in snapshot.children){
                    // construire un objet depense
                    val user = ds.getValue(UserModel::class.java)

                    //verfifer que la depense n'est pas null
                    if(user != null){
                        // ajouter la depense à notre liste
                        UserRepository.Singleton.userList.add(user)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun updateUser(user: UserModel) = UserRepository.Singleton.databaseRef.child(user.id).setValue(user)

    fun insertUser(user : UserModel) = UserRepository.Singleton.databaseRef.child(user.id).setValue(user)

    // supprimer un objet depense en bdd
    fun deleteUser(user : UserModel) = UserRepository.Singleton.databaseRef.child(user.id).removeValue()
}