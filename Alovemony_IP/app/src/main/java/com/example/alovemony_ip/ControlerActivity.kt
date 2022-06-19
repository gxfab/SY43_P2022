package com.example.alovemony_ip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alovemony_ip.connexionactivities.ConnexionActivity
import com.example.alovemony_ip.connexionactivities.ConnexionCategorieActivity
import com.example.alovemony_ip.connexionactivities.ConnexionMoneyGained
import com.example.alovemony_ip.repository.CategorieRepository
import com.example.alovemony_ip.repository.UserRepository
import com.example.alovemony_ip.repository.UserRepository.Singleton.userList
import com.google.firebase.database.FirebaseDatabase

class ControlerActivity : AppCompatActivity() {

    /*

        Activité permettant de renvoyer l'utilisateur sur MainActivty ou bien sur les pages de connexion,
        en fonction de si l'utilisateur c'était déjà connecté à l'appliaction
     */

    val repo_user = UserRepository()
    val repo_categorie = CategorieRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        repo_user.updateData {

            var firstConnexion = userList[0].isfirstco
            if(firstConnexion)
            {
                //on initialise les données de l'utilisateur
                userList[0].isfirstco = false
                userList[0].currentMoney =0
                userList[0].incomeTotale = 0
                repo_user.updateUser(userList[0])
                val intent : Intent = Intent(this, ConnexionActivity::class.java)
                startActivity(intent)
            }else{
                repo_user.updateData {
                    repo_categorie.updateData {
                        val intent: Intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

            }
        }
    }
}