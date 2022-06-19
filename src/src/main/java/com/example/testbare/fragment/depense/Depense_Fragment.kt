package com.example.testbare.fragment.depense

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testbare.MonthYearPickerDialog
import com.example.testbare.R
import com.example.testbare.database.AppDatabase
import com.example.testbare.database.entities.Categorie
import com.example.testbare.database.entities.Depense
import com.example.testbare.fragment.depense.FiltreView.OnItemCheckListener
import kotlinx.coroutines.launch

class Depense_Fragment : Fragment(), MonthYearPickerDialog.MonthListener {
    lateinit var dialogBuilder : AlertDialog.Builder
    lateinit var dialog : AlertDialog
    var depenses : ArrayList<Depense> = ArrayList()
    lateinit var rcDepenses : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_depense, container, false)
        val context : Context = this.context as Context

        val btnTrier = view.findViewById<Button>(R.id.frgmDepense_btn_trier)
        val btnChoixMois = view.findViewById<ImageButton>(R.id.frgmDepense_imgBtn_mois)
        val btnFiltrer = view.findViewById<Button>(R.id.frgmDepense_btn_filtrer)
        val tvMois = view.findViewById<TextView>(R.id.frgmDepense_tv_mois)
        val tvAnnee = view.findViewById<TextView>(R.id.frgmDepense_tv_annee)
        rcDepenses = view.findViewById<RecyclerView>(R.id.frgmDepense_rc_depenses)

        val depenseDao = AppDatabase.getDatabase(context).DepenseDao()
        val moisDao = AppDatabase.getDatabase(context).MoisDao()

        rcDepenses.layoutManager = LinearLayoutManager(context)
        rcDepenses.adapter = DepenseView(depenses)

        lifecycleScope.launch {
            depenses.clear()
            val annee = tvAnnee.text.toString().toInt()
            val mois = monthToInt(tvMois.text.toString())
            if( moisDao.existMonth(annee, mois) > 0){
                val idMois = moisDao.getMoisId(annee, mois)
                depenses.addAll(depenseDao.getDepenseDuMois(idMois) as ArrayList<Depense>)
            }
            rcDepenses.adapter?.notifyDataSetChanged()
            if (depenses.isEmpty()) {
                Toast.makeText(context, "Aucune dépense faite sur ce mois", Toast.LENGTH_LONG).show()
                btnTrier.isEnabled = false
            }
        }

        btnChoixMois.setOnClickListener{
            val pd = MonthYearPickerDialog()
            pd.setListener(this)
            pd.show(this.requireActivity().supportFragmentManager, "MonthYearPickerDialog")
        }

        btnTrier.setOnClickListener{
            lifecycleScope.launch {
                val mois = monthToInt(tvMois.text.toString())
                val annee = tvAnnee.text.toString().toInt()
                val idMois = moisDao.getMoisId(annee, mois)
                afficherPopupTrier(idMois)
            }
        }

        btnFiltrer.setOnClickListener {
            lifecycleScope.launch {
                val mois = monthToInt(tvMois.text.toString())
                val annee = tvAnnee.text.toString().toInt()
                val idMois = moisDao.getMoisId(annee, mois)
                afficherPopupFilter(idMois)
            }
        }

        return view
    }

    fun intToMonth(intMonth : Int) : String{
        when(intMonth){
            0 -> return "Janvier"
            1 -> return "Février"
            2 -> return "Mars"
            3 -> return "Avril"
            4 -> return "Mai"
            5 -> return "Juin"
            6 -> return "Juillet"
            7 -> return "Aout"
            8 -> return "Septembre"
            9 -> return "Octobre"
            10 -> return "Novembre"
            else -> return "Décembre"
        }
    }

    fun monthToInt(mois: String) : Int{
        when(mois){
            "Janvier" -> return 0
            "Février" -> return 1
            "Mars"  -> return 2
            "Avril" -> return 3
            "Mai" -> return 4
            "Juin" -> return 5
            "Juillet" -> return 6
            "Aout" -> return 7
            "Septembre" -> return 8
            "Octobre" -> return 9
            "Novembre" -> return 10
            else -> return 11
        }
    }

    fun afficherPopupTrier(idMois : Int){
        dialogBuilder = AlertDialog.Builder(this.requireContext())
        val popupTrier : View = getLayoutInflater().inflate(R.layout.popup_trier_depense,null)
        dialogBuilder.setView(popupTrier)
        dialog = dialogBuilder.create()
        dialog.show()

        val radioGroup = popupTrier.findViewById<RadioGroup>(R.id.popTrierDepense_rdGrp)
        val rdPlusRecent = popupTrier.findViewById<RadioButton>(R.id.popTrierDepense_rd_plusRecent)
        val rdMoinsRecent = popupTrier.findViewById<RadioButton>(R.id.popTrierDepense_rd_moinsRecent)
        val rdPlusChere = popupTrier.findViewById<RadioButton>(R.id.popTrierDepense_rd_plusChere)
        val rdMoinsChere = popupTrier.findViewById<RadioButton>(R.id.popTrierDepense_rd_moinsChere)

        val btnValider = popupTrier.findViewById<Button>(R.id.popTrierDepense_btn_valider)
        val btnAnnuler = popupTrier.findViewById<Button>(R.id.popTrierDepense_btn_annuler)

        btnAnnuler.setOnClickListener{ dialog.hide() }

        btnValider.setOnClickListener{
            val depenseDao = AppDatabase.getDatabase(requireContext()).DepenseDao()
            lifecycleScope.launch {
                var depensesTriees : ArrayList<Depense> = ArrayList()
                when(radioGroup.checkedRadioButtonId){
                    rdPlusRecent.id ->  depensesTriees.addAll(depenseDao.getAllDepensesDateDesc(idMois) as ArrayList<Depense>)

                    rdMoinsRecent.id -> depensesTriees.addAll(depenseDao.getAllDepensesDateAsc(idMois) as ArrayList<Depense>)

                    rdPlusChere.id -> depensesTriees.addAll(depenseDao.getAllDepensesMontantDesc(idMois) as ArrayList<Depense>)

                    rdMoinsChere.id -> depensesTriees.addAll(depenseDao.getAllDepensesMontantAsc(idMois) as ArrayList<Depense>)
                }
                val depensesTrieesIterator = depensesTriees.iterator()
                while(depensesTrieesIterator.hasNext()){
                    val dep = depensesTrieesIterator.next()
                    if(!depenses.contains(dep))
                        depensesTrieesIterator.remove()
                }
                Log.e("Après Tri", depensesTriees.toString())
                depenses.clear()
                depenses.addAll(depensesTriees)
                rcDepenses.adapter?.notifyDataSetChanged()
                dialog.hide()
            }
        }
    }

    fun afficherPopupFilter(idMois : Int){
        dialogBuilder = AlertDialog.Builder(this.requireContext())
        val popupFiltrer : View = getLayoutInflater().inflate(R.layout.popup_filtrer_categorie,null)
        dialogBuilder.setView(popupFiltrer)
        dialog = dialogBuilder.create()
        dialog.show()
        val btnAppliquer = popupFiltrer.findViewById<Button>(R.id.popFiltre_btn_appliquer)

        var categories : ArrayList<Categorie> = ArrayList()
        var categoriesCochees : ArrayList<String> = ArrayList()

        val rcCategorie = popupFiltrer.findViewById<RecyclerView>(R.id.popFiltre_rc_categorie)
        rcCategorie.layoutManager = LinearLayoutManager(context)

        rcCategorie.adapter = FiltreView(categories, object : OnItemCheckListener {
            override fun onItemCheck(item: String) {
                categoriesCochees.add(item)
            }

            override fun onItemUncheck(item: String) {
                categoriesCochees.remove(item)
            }
        })

        val categorieDao = AppDatabase.getDatabase(requireContext()).CategorieDao()
        lifecycleScope.launch {
            categories.addAll(categorieDao.getAllCategorie())
            rcCategorie.adapter?.notifyDataSetChanged()
        }


        btnAppliquer.setOnClickListener {
            Log.e("List catégorie sélectionné",categoriesCochees.toString())
            val depensesDao = AppDatabase.getDatabase(requireContext()).DepenseDao()
            lifecycleScope.launch {
                depenses.clear()
                if(categoriesCochees.size == 0)
                    depenses.addAll(depensesDao.getDepenseDuMois(idMois))
                else
                    depenses.addAll(depensesDao.getDepenseByCategories(categoriesCochees, idMois))
                rcDepenses.adapter?.notifyDataSetChanged()
            }
            dialog.hide()
        }
    }

    override fun onOkDialogMonthClick(moisChoisi: Int, anneeChoisi: Int) {
        view?.findViewById<TextView>(R.id.frgmDepense_tv_mois)?.text = intToMonth(moisChoisi)
        view?.findViewById<TextView>(R.id.frgmDepense_tv_annee)?.text = anneeChoisi.toString()
        val moisDao = AppDatabase.getDatabase(this.requireContext()).MoisDao()
        val depenseDao = AppDatabase.getDatabase(this.requireContext()).DepenseDao()
        lifecycleScope.launch {
            var existMonth = moisDao.existMonth(anneeChoisi, moisChoisi)
            if (existMonth == 0) {
                Toast.makeText(context,"Aucune dépense faite sur ce mois", Toast.LENGTH_LONG).show()
                depenses.clear()
                rcDepenses.adapter?.notifyDataSetChanged()
                view?.findViewById<Button>(R.id.frgmDepense_btn_trier)?.isEnabled = false
            } else {
                val idMois = moisDao.getMoisId(anneeChoisi, moisChoisi)
                depenses.clear()
                depenses.addAll(depenseDao.getDepenseDuMois(idMois) as ArrayList<Depense>)
                rcDepenses.adapter?.notifyDataSetChanged()
                view?.findViewById<Button>(R.id.frgmDepense_btn_trier)?.isEnabled = true
            }
        }
    }
}