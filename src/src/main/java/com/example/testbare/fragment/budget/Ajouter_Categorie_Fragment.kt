package com.example.testbare.fragment.budget

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.testbare.R
import com.example.testbare.database.AppDatabase
import com.example.testbare.database.entities.Categorie
import kotlinx.coroutines.launch

class Ajouter_Categorie_Fragment(mois : Int, annee : Int) : Fragment() {

    val mois = mois
    val annee = annee

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ajouter_categorie, container, false)
        val btnAnnuler = view.findViewById<Button>(R.id.frgmAjoutCategorie_btn_annuler)
        val btnAjouter = view.findViewById<Button>(R.id.frgmAjoutCategorie_btn_ajouter)
        val etNomCategorie = view.findViewById<EditText>(R.id.frgmAjoutCategorie_et_Categorie)
        val mAjouterBudgetFragment = Ajouter_Budget_Fragment(mois, annee)
        val context = this.requireContext()
        val categorieDao = AppDatabase.getDatabase(context).CategorieDao()

        etNomCategorie.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ){}

            override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
            ) {
            btnAjouter.isEnabled = etNomCategorie.text.toString() != ""
            }
        })

        btnAnnuler.setOnClickListener{
            if(mAjouterBudgetFragment != null){
                val transaction = this.activity?.supportFragmentManager?.beginTransaction()
                if (transaction != null) {
                    transaction.replace(R.id.fragment_container, mAjouterBudgetFragment)
                }
                if (transaction != null) {
                    transaction.commit()
                }
            }
        }

        btnAjouter.setOnClickListener{
            var nomCategorie = etNomCategorie.text.toString()
            lifecycleScope.launch {
                if( categorieDao.getAllCategoriesNames().contains(nomCategorie)){
                    Toast.makeText(context, "Catégorie déjà existante. Veuillez changer le nom !", Toast.LENGTH_SHORT).show()
                }else{
                    categorieDao.insertCategorie(Categorie(nomCategorie))
                    Toast.makeText(context, "Votre nouvelle catégorie a été ajouté avec succès!", Toast.LENGTH_SHORT).show()
                    if(mAjouterBudgetFragment != null){
                        val transaction = activity?.supportFragmentManager?.beginTransaction()
                        if (transaction != null) {
                            transaction.replace(R.id.fragment_container, mAjouterBudgetFragment)
                        }
                        if (transaction != null) {
                            transaction.commit()
                        }
                    }
                }
            }
        }

        return view
    }
}