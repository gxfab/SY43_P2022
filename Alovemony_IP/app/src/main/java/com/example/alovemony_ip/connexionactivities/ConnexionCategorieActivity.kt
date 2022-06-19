package com.example.alovemony_ip.connexionactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alovemony_ip.MainActivity
import com.example.alovemony_ip.R
import com.example.alovemony_ip.adapter.ConnexionCategorieAdapter
import com.example.alovemony_ip.adapter.ProjetItemDecoration
import com.example.alovemony_ip.repository.CategorieRepository.Singleton.categorieList

class ConnexionCategorieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.connexion_categorie)


        //on affiche la liste des catégories en assignant le recycler View au layout catégorieList et avec l'adapter correspondant
        val categorieRecyclerView = findViewById<RecyclerView>(R.id.connexion_categorie_list)
        categorieRecyclerView.adapter = ConnexionCategorieAdapter(this,categorieList)
        categorieRecyclerView.layoutManager = LinearLayoutManager(this)
        categorieRecyclerView.addItemDecoration(ProjetItemDecoration())


        //on assigne le changement d'activité au bouton "Next"
        val button_categorie_to_main = findViewById<Button>(R.id.account_next_button_categorie)
        button_categorie_to_main.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //on assigne le changement d'activité au bouton "Back"
        val button_categorie_to_levy = findViewById<Button>(R.id.account_back_button_categorie)
        button_categorie_to_levy.setOnClickListener {
            val intent : Intent = Intent(this, ConnexionPrevAutoActivity::class.java)
            startActivity(intent)
        }
        super.onCreate(savedInstanceState)
    }
}