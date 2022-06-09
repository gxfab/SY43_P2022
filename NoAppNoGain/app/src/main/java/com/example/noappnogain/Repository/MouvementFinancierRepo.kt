package com.example.noappnogain.Repository
import com.example.noappnogain.Model.MouvementFinancier
import com.example.noappnogain.Repository.MouvementFinancierRepo.Singleton.MouvementFinancierList
import com.example.noappnogain.Repository.MouvementFinancierRepo.Singleton.databaseRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MouvementFinancierRepo {

    object Singleton {
        //connexion à la ref "MouvementFinancier"
        val databaseRef = FirebaseDatabase.getInstance().getReference("mouvements")

        //Création liste contenant les mouvements financier
        val MouvementFinancierList = arrayListOf<MouvementFinancier>()
    }

    fun updateData(callback: () -> Unit){
        //Actualisation de la liste via données de la database
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //Retirer les anciens Mouvements Financiers
                MouvementFinancierList.clear()

                //Recuperer la liste
                for (ds in snapshot.children){
                    //Construire objet MouvementFinancier
                    val mouvementF = ds.getValue(MouvementFinancier::class.java)

                    //Vérification que le mouvement est bien chargé
                    if(mouvementF != null){
                        //Ajout du Mouvement Financier à la liste
                        MouvementFinancierList.add(mouvementF)
                    }
                }

                //Actionner le callback
                callback()
            }

            override fun onCancelled(snapshot: DatabaseError) {

            }

        })
    }
}