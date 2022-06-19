package com.example.testbare.fragment.budget

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.testbare.R
import com.example.testbare.database.AppDatabase
import com.example.testbare.database.entities.Budget
import com.example.testbare.database.entities.Mois
import kotlinx.coroutines.launch
import java.util.*


class Ajouter_Budget_Fragment(mois : Int, annee : Int) : Fragment() {

    lateinit var spnrCategorie: Spinner
    lateinit var categorieChoisie : String
    val moisAjout = mois
    val anneeAjout = annee

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ajouter_budget, container, false)
        val btnAnnuler = view.findViewById<Button>(R.id.frgmAjoutBudget_btn_annuler)
        val btnAjouterBudget = view.findViewById<Button>(R.id.frgmAjoutBudget_btn_ajouter)
        val btnAjouterCategorie = view.findViewById<Button>(R.id.frgmAjoutBudget_btn_ajouterCategorie)
        val etMontant = view.findViewById<EditText>(R.id.frgmAjoutBudget_et_montant)
        spnrCategorie = view.findViewById(R.id.frgmAjoutBudget_spnr_categories)

        val mBudgetFragment = Budget_Fragment()
        val mAjouterCategorieFragment = Ajouter_Categorie_Fragment(moisAjout, anneeAjout)
        val context = this.requireContext()

        val budgetDao = AppDatabase.getDatabase(context).BudgetDao()
        val moisDao = AppDatabase.getDatabase(context).MoisDao()
        val categorieDao = AppDatabase.getDatabase(context).CategorieDao()

        val categories: ArrayList<String> = ArrayList()
        categories.add("choisir une catégorie")
        lifecycleScope.launch {
            for (categorie in categorieDao.getAllCategoriesNames()) {
                categories.add(categorie)
            }
            categories.sort()
        }

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(), android.R.layout.simple_spinner_item, categories
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrCategorie.setAdapter(adapter)

        spnrCategorie?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent == null)
                    return;
                val adapter: Adapter = parent.adapter
                categorieChoisie = adapter.getItem(position) as String
                btnAjouterBudget.isEnabled = categorieChoisie != "choisir une catégorie" && etMontant.text.toString() != ""

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        etMontant.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                btnAjouterBudget.isEnabled = categorieChoisie != "choisir une catégorie" && etMontant.text.toString() != ""

            }
        })

        btnAjouterCategorie.setOnClickListener{
            if(mAjouterCategorieFragment != null){
                val transaction = this.activity?.supportFragmentManager?.beginTransaction()
                if (transaction != null) {
                    transaction.replace(R.id.fragment_container, mAjouterCategorieFragment)
                }
                if (transaction != null) {
                    transaction.commit()
                }
            }
        }

        btnAnnuler.setOnClickListener{
            if (mBudgetFragment != null) {
                val transaction = this.activity?.supportFragmentManager?.beginTransaction()
                if (transaction != null) {
                    transaction.replace(R.id.fragment_container, mBudgetFragment)
                }
                if (transaction != null) {
                    transaction.commit()
                }
            }
        }

        btnAjouterBudget.setOnClickListener{
            val montant = etMontant.text.toString()
            categorieChoisie = spnrCategorie.selectedItem.toString()
            if(montant == ""){
                Toast.makeText(this.activity, "Veuillez remplir tous les champs avant d'ajouter", Toast.LENGTH_SHORT).show()
            }
            else {
                lifecycleScope.launch{
                    val exist = moisDao.existMonth(anneeAjout, moisAjout)
                    if(exist == 0) {
                        moisDao.insertMois(Mois(0, anneeAjout, moisAjout))
                    }
                    val idMois = moisDao.getMoisId(anneeAjout, moisAjout)
                    budgetDao.insertBudget(Budget(0,montant.toFloat(),idMois,categorieChoisie))
                    etMontant.setText("")
                    if (mBudgetFragment != null) {
                        val transaction = activity?.supportFragmentManager?.beginTransaction()
                        if (transaction != null) {
                            transaction.replace(R.id.fragment_container, mBudgetFragment)
                        }
                        if (transaction != null) {
                            transaction.commit()
                        }
                    }
                    Toast.makeText(context, "Votre nouveau budget a été pris en compte!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }
}