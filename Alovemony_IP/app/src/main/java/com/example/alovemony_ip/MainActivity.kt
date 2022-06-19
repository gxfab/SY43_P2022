package com.example.alovemony_ip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.alovemony_ip.connexionactivities.ConnexionIncomeActivity
import com.example.alovemony_ip.fragments.AccueilFragment
import com.example.alovemony_ip.fragments.DepenseFragment
import com.example.alovemony_ip.fragments.ProjetFragment
import com.example.alovemony_ip.repository.*

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadMainActivity()
    }

    private fun loadMainActivity(){
        setContentView(R.layout.activity_main)
        loadFragment(AccueilFragment(this),R.string.page_accueil_titre)
        val settingsBtn= findViewById<ImageButton>(R.id.settingsBtn)

        //Display les settings on click
        settingsBtn.setOnClickListener {
            val intent : Intent = Intent(this, ConnexionIncomeActivity::class.java)
            startActivity(intent)
        }


        //On initialise la barre de naviguation entre les trois fragments
        val navigationView=findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_page->{
                    loadFragment(AccueilFragment(this),R.string.page_accueil_titre)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.projet_page->{
                    loadFragment( ProjetFragment(this),R.string.page_projet_titre)

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.depense_page->{
                    loadFragment(DepenseFragment(this),R.string.page_depense_titre)

                    return@setOnNavigationItemSelectedListener true

                }
                else -> false
            }
        }


    }

    /*
        Fonction permettant de charger la base de données et le framgent en fonction du fragment sélectionné
     */
    public fun loadFragment(fragment: Fragment,string:Int) {

        // charger notre ProjetRepository
        val repo_projet = ProjetRepository()
        //charger notre DetteRepository
        val repo_dette = DetteRepository()
        //charger notre CategorieRepository
        val repo_categorie = CategorieRepository()
        //charger notre ProjetGlobalRepository
        val repo_projetGlobal = ProjetGlobalRepository()
        // charger notre DepenseRepository
        val repo_depense = DepenseRepository()
        val repo_income = IncomeRepository()


    //actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text= resources.getString(string)
// mettre à jour la liste des projets

        repo_income.updateData {
            repo_categorie.updateData {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container,fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        repo_projet.updateData() {
            //injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
            repo_dette.updateData()
            repo_projetGlobal.updateData()
        }


        repo_depense.updateData() {
            repo_categorie.updateData {

                //injecter le fragment dans notre boite (fragment_container)
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }


    }
}
