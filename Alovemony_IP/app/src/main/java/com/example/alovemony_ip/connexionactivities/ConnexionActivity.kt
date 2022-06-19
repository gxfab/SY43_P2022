package com.example.alovemony_ip.connexionactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.alovemony_ip.R
import com.example.alovemony_ip.repository.UserRepository.Singleton.userList

/*
Correspondant à la page où l'utilisateur pourra écrire le pseudo qu'il lui sera assigner
 */
class ConnexionActivity() : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        //on affecte le layout
        setContentView(R.layout.connexion_name)
        val name = findViewById<EditText>(R.id.account_nom)

        //Bouton pour passer à l'autre page
        val button_name_to_income = findViewById<Button>(R.id.account_next_button_name)
        button_name_to_income.setOnClickListener {
            // on  vérifie s'il a bien rempli l'editText
            if(name.text.toString() == "")
            {
                name.error= "Veuillez entrez votre nom"
            }else {
                //on l'assigne au nom de notre liste
                userList[0].name = name.text.toString()
                //on change d'activitée
                val intent: Intent = Intent(this, ConnexionIncomeActivity::class.java)
                startActivity(intent)
            }
        }

        super.onCreate(savedInstanceState)

    }
}