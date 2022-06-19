package com.example.testbare.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.testbare.R
import com.example.testbare.database.AppDatabase
import com.example.testbare.database.entities.Depense
import com.example.testbare.database.entities.Mois

import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Ajouter_Depense_Fragment() : Fragment() {

    lateinit var spnrCategorie: Spinner
    lateinit var categorieChoisie: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ajouter_depense, container, false)
        var btnAjouter = view.findViewById<Button>(R.id.frgmAjoutCategorie_btn_ajouter)
        var etMontant = view.findViewById<EditText>(R.id.frgmAjoutDepense_et_montant)
        var etDate = view.findViewById<EditText>(R.id.frgmAjoutDepense_et_date)

        btnAjouter.isEnabled = false

        this.spnrCategorie = view.findViewById(R.id.frgmAjoutDepense_spnr_categories);

        val categorieDao = AppDatabase.getDatabase(this.context as Context).CategorieDao()

        val categories: ArrayList<String> = ArrayList()
        categories.add("choisir une catégorie")
        //ouverture d'une coroutine
        //recherche des noms de catégorie en BDD
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
        this.spnrCategorie.setAdapter(adapter);
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
                btnAjouter.isEnabled = categorieChoisie != "choisir une catégorie" && etMontant.text.toString() != ""

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
                btnAjouter.isEnabled = categorieChoisie != "choisir une catégorie" && etMontant.text.toString() != ""

            }
        })
        //gestion ajout d'une dépense en BDD
        btnAjouter.setOnClickListener {
            val depenseDao = AppDatabase.getDatabase(this.requireContext()).DepenseDao()
            val moisDao = AppDatabase.getDatabase(this.requireContext()).MoisDao()
            val dateDepenseAjoute = SimpleDateFormat("dd-MM-yyyy").parse(etDate.text.toString())
            val cal = Calendar.getInstance()
            cal.time = dateDepenseAjoute
            val moisDepenseAjoute = cal.get(Calendar.MONTH)
            val anneeDepenseAjoute = cal.get(Calendar.YEAR)
            Log.e("année de la dépense :", anneeDepenseAjoute.toString())
            Log.e("mois de la dépense :", moisDepenseAjoute.toString())
            lifecycleScope.launch{
                val exist = moisDao.existMonth(anneeDepenseAjoute, moisDepenseAjoute)
                if(exist == 0) {
                    moisDao.insertMois(Mois(0, anneeDepenseAjoute, moisDepenseAjoute))
                }
                val idMois = moisDao.getMoisId(anneeDepenseAjoute, moisDepenseAjoute)
                val dep = Depense(0,etMontant.getText().toString().toFloat(), dateDepenseAjoute, categorieChoisie, idMois)
                Log.e("dépense créé : ", dep.toString())
                depenseDao.insertDepense(dep)
                Toast.makeText(context, "Votre dépense a bien été ajouté", Toast.LENGTH_SHORT).show();
                etMontant.setText("")
                etDate.setText("")
            }
        }

        etDate.setOnClickListener{
            showDateDialog(etDate)
        }

        return view
    }

    private fun showDateDialog(date_in: EditText) {
        val calendar: Calendar = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
                date_in.setText(simpleDateFormat.format(calendar.getTime()))
            }
        DatePickerDialog(
            this.requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

}



