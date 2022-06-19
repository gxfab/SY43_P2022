package com.example.testbare

import android.content.Intent
import com.example.testbare.fragment.Ajouter_Depense_Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.example.testbare.fragment.budget.Budget_Fragment
import com.example.testbare.database.AppDatabase
import com.example.testbare.database.entities.Categorie
import com.example.testbare.fragment.analyse.Analyse_Fragment
import com.example.testbare.fragment.depense.Depense_Fragment
import com.example.testbare.fragment.parametre.Parametre_Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){

    private val mAjoutDepenseFragment = Ajouter_Depense_Fragment()
    private val mSettingsFragment = Parametre_Fragment()
    private val mDepenseFragment = Depense_Fragment()
    private val mBudgetFragment = Budget_Fragment()
    private val mAnalyseFragment = Analyse_Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gameActivityIntent = Intent(this@MainActivity, Login::class.java)
        startActivity(gameActivityIntent)

        val  bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        replaceFragment(mAjoutDepenseFragment)

        //gestion changement de fragment lors du clic sur les boutons du menu
        bottomNavigationView.setOnItemSelectedListener   {
            when(it.itemId){
                R.id.menu_ajoutDepense -> replaceFragment(mAjoutDepenseFragment)
                R.id.menu_depense->replaceFragment(mDepenseFragment)
                R.id.menu_budget->replaceFragment(mBudgetFragment)
                R.id.menu_settings -> replaceFragment(mSettingsFragment)
                R.id.menu_analyse->replaceFragment(mAnalyseFragment)
                else -> false
            }
        }

        val categorieDao = AppDatabase.getDatabase(this).CategorieDao()

        lifecycle.coroutineScope.launch{
            val cat : Categorie = Categorie("Voiture")
            categorieDao.insertCategorie(cat)
            val cat2 : Categorie = Categorie("Alimentation")
            categorieDao.insertCategorie(cat2)
            val cat3 : Categorie = Categorie("Charge")
            categorieDao.insertCategorie(cat3)
            val cat4 : Categorie = Categorie("Loisirs")
            categorieDao.insertCategorie(cat4)
            val cat5 : Categorie = Categorie("Autre")
            categorieDao.insertCategorie(cat5)
        }
    }

    /***
     * méthode permettant de changer de fragment
     */
    private fun replaceFragment(fragment: Fragment): Boolean {
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
            return true
        }
        return false
    }
}