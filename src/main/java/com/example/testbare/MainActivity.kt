package com.example.testbare
import com.example.testbare.fragment.Ajouter_Depense_Fragment
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.example.testbare.budget.ClasseTemplate
import com.example.testbare.database.AppDatabase
import com.example.testbare.database.entities.Categorie
import com.example.testbare.fragment.InfoFragment
import com.example.testbare.parametre.Parametre_Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){

    private val mDeshboardFragment = Ajouter_Depense_Fragment()
    private val mSettingsFragment = Parametre_Fragment()
    private val mInfoFragment = InfoFragment()
    private val mAnalyseFragment = ClasseTemplate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        replaceFragment(mInfoFragment)

        //pour commencer sur ce dashboard
        bottomNavigationView.setOnItemSelectedListener   {
            when(it.itemId){
                R.id.dashboard -> replaceFragment(mDeshboardFragment)
                R.id.settings -> replaceFragment(mSettingsFragment)
                R.id.info->replaceFragment(mInfoFragment)
                R.id.Analyse->replaceFragment(mAnalyseFragment)

                else -> false
            }
        }

        val categorieDao = AppDatabase.getDatabase(this).CategorieDao()

        lifecycle.coroutineScope.launch{
            val cat : Categorie = Categorie("Voiture",400)
            categorieDao.insertCategorie(cat)
            val cat2 : Categorie = Categorie("Alimentation",350)
            categorieDao.insertCategorie(cat2)
            val cat3 : Categorie = Categorie("Charge",520)
            categorieDao.insertCategorie(cat3)
            val cat4 : Categorie = Categorie("Loisirs",150)
            categorieDao.insertCategorie(cat4)
        }

    }


    /***
     * méthode permettant de changer de dashboard
     */

    private fun replaceFragment(fragment: Fragment): Boolean {
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
       //     setContentView(R.layout.fragment);
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
            return true

        }
        return false
    }

     fun getContexte() : Context{
        return this.getContexte()
    }

}