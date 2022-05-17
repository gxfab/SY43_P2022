package com.example.testbare.fragment

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

import kotlinx.coroutines.launch


class Ajouter_Depense_Fragment() : Fragment() {

     lateinit var m_Category: Spinner
     lateinit var m_depense : String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ajouter_depense, container, false)
        var btn = view.findViewById<Button>(R.id.frgmAjoutDepense_btn_ajouter)
        var nb = view.findViewById<EditText>(R.id.frgmAjoutDepense_et_Depense)

        btn.isEnabled = false
        btn.setOnClickListener(){
            m_depense = nb.text.toString()
        }
        this.m_Category =view.findViewById<Spinner>(R.id.frgmAjoutDepense_spnr_categories);

        val categorieDao = AppDatabase.getDatabase(this.context as Context).CategorieDao()

        var categories: List<String>
        val values: ArrayList<String> = ArrayList()
        values.add("choisir une catégorie")
        //ouverture d'une coroutine
        //recherche des noms de catégorie en BDD
        lifecycleScope.launch{
            categories = categorieDao.getAllCategoriesNames()
            for(categorie in categories){
                values.add(categorie)
            }
        }
        values.add("Autre")

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(), android.R.layout.simple_spinner_item, values
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.m_Category.setAdapter(adapter);
        m_Category?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?,     position: Int, id: Long) {
                if (parent == null)
                        return;
                val adapter: Adapter = parent.adapter
                m_depense = adapter.getItem(position) as String
                btn.isEnabled = m_depense !="choisir une catégorie" && nb.text.toString() != ""

            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }
        nb.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                btn.isEnabled = m_depense !="choisir une catégorie" && nb.text.toString() != ""

            }
        })
        //gestion ajout d'une dépense en BDD
        btn.setOnClickListener{
            val dao = AppDatabase.getDatabase(this.context as Context).DepenseDao()
            val dep : Depense = Depense(0, m_depense, nb.getText().toString().toInt())
            var msg = "On vient d'ajouter une dépense : "
            msg += dep
            Log.e("Bouton cliqué", msg)
            lifecycleScope.launch{
                dao.insertDepense(dep)
                Toast.makeText(context,"Votre dépense a bien été ajouté", Toast.LENGTH_SHORT).show();
                nb.setText("")
            }
        }
        return view
    }
   /* @SuppressLint("WrongConstant")
    private fun onItemSelectedHandler(
        adapterView: AdapterView<*>,
        view: View,
        position: Int,
        id: Long
    ) {
        val adapter: Adapter = adapterView.adapter
        val employee: String = adapter.getItem(position) as String
        if(employee !="choisir une catégorie" ){

        }
        val dialog = Dialog(this.requireContext())
       // val dialog = Dialog(getApplicationContext())

        val text = "Hello toast!"
        val duration = 1000000
        Toast.makeText(context, "ee", Toast.LENGTH_SHORT).show();
//        Toast.makeText(
//            context,
//            "Selected Employee: " ,
//            Toast.LENGTH_SHORT
//        ).show()

      //  val toast = Toast.makeText(this.requireContext(), text, duration)
       // Toast.makeText(this.requireContext(), "Data has been saved successfully!", Toast.LENGTH_LONG).show();
     //   toast.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }*/

}



