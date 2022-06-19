package com.example.testbare.fragment.budget


import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
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
import com.example.testbare.database.entities.Budget
import com.example.testbare.database.entities.Mois
import com.example.testbare.database.entities.Revenu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class Budget_Fragment : Fragment(), MonthYearPickerDialog.MonthListener {
    lateinit var mAjoutCategorieFragment : Fragment
    lateinit var dialogBuilder : AlertDialog.Builder
    lateinit var dialog : AlertDialog
    var budgets : ArrayList<Budget> = ArrayList()
    var revenus : ArrayList<Revenu> = ArrayList()
    lateinit var rcBudgets : RecyclerView
    lateinit var rcRevenus : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_budget, container, false)
        val context : Context = this.context as Context

        var btnAjoutBudget = view.findViewById<FloatingActionButton>(R.id.frgmBudget_fab_ajoutBudget)
        val btnChoixMois = view.findViewById<ImageButton>(R.id.frgmBudget_imgBtn_mois)
        val btnAccessRevenus = view.findViewById<Button>(R.id.frgmBudget_btn_revenu)
        val tvMois = view.findViewById<TextView>(R.id.frgmBudget_tv_mois)
        val tvAnnee = view.findViewById<TextView>(R.id.frgmBudget_tv_annee)
        rcBudgets = view.findViewById<RecyclerView>(R.id.frgmBudget_rc_budgets)


        val budgetDao = AppDatabase.getDatabase(context).BudgetDao()
        val moisDao = AppDatabase.getDatabase(context).MoisDao()
        rcBudgets.layoutManager = LinearLayoutManager(context)
        rcBudgets.adapter = BudgetView(budgets)

        lifecycleScope.launch {
            budgets.clear()
            val annee = tvAnnee.text.toString().toInt()
            val mois = monthToInt(tvMois.text.toString())
            if( moisDao.existMonth(annee, mois) > 0) {
                val idMois = moisDao.getMoisId(annee, mois)
                budgets.addAll(budgetDao.getBudgetDuMois(idMois) as ArrayList<Budget>)
            }
            rcBudgets.adapter?.notifyDataSetChanged()
            if(budgets.isEmpty())
                Toast.makeText(context,"Aucun budget défini pour ce mois",Toast.LENGTH_LONG).show()
        }

        btnChoixMois.setOnClickListener{
            val pd = MonthYearPickerDialog()
            pd.setListener(this)
            pd.show(this.requireActivity().supportFragmentManager, "MonthYearPickerDialog")
        }

        btnAccessRevenus.setOnClickListener{
            afficherRevenuDialog(monthToInt(tvMois.text.toString()), tvAnnee.text.toString().toInt())
            setMontantTotalRevenu(monthToInt(tvMois.text.toString()), tvAnnee.text.toString().toInt())
        }

        btnAjoutBudget.setOnClickListener {
            val annee = tvAnnee.text.toString().toInt()
            val mois = monthToInt(tvMois.text.toString())
            mAjoutCategorieFragment = Ajouter_Budget_Fragment(mois, annee)
            if (mAjoutCategorieFragment != null) {
                val transaction = this.activity?.supportFragmentManager?.beginTransaction()
                if (transaction != null) {
                    transaction.replace(R.id.fragment_container, mAjoutCategorieFragment)
                }
                if (transaction != null) {
                    transaction.commit()
                }
            }
        }

        setMontantTotalRevenu(5,2022)

        return view
    }

    fun setMontantTotalRevenu(mois : Int, annee : Int){
        var montantTotalRevenu  = 0F
        var montantTotalBudget = 0F
        val revenuDao = AppDatabase.getDatabase(this.requireContext()).RevenuDao()
        val moisDao = AppDatabase.getDatabase(this.requireContext()).MoisDao()
        val budgetDao = AppDatabase.getDatabase(this.requireContext()).BudgetDao()
        var idMois : Int

        lifecycleScope.launch {
            if (moisDao.existMonth(annee, mois) != 0) {
                idMois = moisDao.getMoisId(annee, mois)
                if(revenuDao.existMonthRevenu(idMois) != 0)
                    montantTotalRevenu = revenuDao.getMontantTotalRevenuDuMois(idMois)
                if(budgetDao.existMonthBudget(idMois) != 0)
                    montantTotalBudget = budgetDao.getMontantTotalBudgetMois(idMois)
            }
            view?.findViewById<TextView>(R.id.frgmBudget_tv_revenu)?.text = montantTotalRevenu.toString()
            if(montantTotalRevenu < montantTotalBudget) {
                view?.findViewById<TextView>(R.id.frgmBudget_tv_revenu)?.setBackgroundColor(Color.RED)
                Toast.makeText(context,"Attention Revenus inférieurs aux budgets",Toast.LENGTH_LONG).show()
            }else
                view?.findViewById<TextView>(R.id.frgmBudget_tv_revenu)?.setBackgroundColor(Color.GREEN)
        }
    }

    fun afficherRevenuDialog(mois : Int, annee : Int){
        dialogBuilder = AlertDialog.Builder(this.requireContext())
        val popupRevenu : View = getLayoutInflater().inflate(R.layout.popup_revenu,null)
        dialogBuilder.setView(popupRevenu)
        dialog = dialogBuilder.create()
        dialog.show()
        rcRevenus = popupRevenu.findViewById(R.id.popRevenu_rc_revenus)
        rcRevenus.layoutManager = LinearLayoutManager(context)
        rcRevenus.adapter = RevenuView(revenus)

        val context = this.requireContext()
        val revenuDao = AppDatabase.getDatabase(context).RevenuDao()
        val moisDao = AppDatabase.getDatabase(context).MoisDao()

        lifecycleScope.launch {
            revenus.clear()
            if (moisDao.existMonth(annee, mois) > 0) {
                val idMois = moisDao.getMoisId(annee, mois)
                if (revenuDao.existMonthRevenu(idMois) > 0) {
                    revenus.addAll(revenuDao.getRevenusDuMois(idMois) as ArrayList<Revenu>)
                }
            }
            rcRevenus.adapter?.notifyDataSetChanged()
        }

        val btnAjouterRevenu = popupRevenu.findViewById<Button>(R.id.popRevenu_btn_ajouter)
        val etSourceRevenu = popupRevenu.findViewById<EditText>(R.id.popRevenu_et_source)
        val etMontantRevenu = popupRevenu.findViewById<EditText>(R.id.popRevenu_et_montant)

        btnAjouterRevenu.setOnClickListener{
            val montantRevenu = etMontantRevenu.text.toString().toFloat()
            val sourceRevenu = etSourceRevenu.text.toString()
            lifecycleScope.launch {
                var idMois : Int
                var revenuInsere : Revenu
                if (moisDao.existMonth(annee, mois) > 0){
                    idMois = moisDao.getMoisId(annee, mois)
                    revenuInsere = Revenu(0,montantRevenu,sourceRevenu,idMois)
                    revenuDao.insertRevenu(revenuInsere)
                }else{
                    moisDao.insertMois(Mois(0,annee,mois))
                    idMois = moisDao.getMoisId(annee, mois)
                    revenuInsere = Revenu(0,montantRevenu, sourceRevenu,idMois)
                    revenuDao.insertRevenu(revenuInsere)
                }
                revenus.add(0,revenuInsere)
                rcRevenus.adapter?.notifyItemInserted(0)
            }
            setMontantTotalRevenu(mois, annee)
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

    override fun onOkDialogMonthClick(moisChoisi : Int, anneeChoisi: Int) {
        view?.findViewById<TextView>(R.id.frgmBudget_tv_mois)?.text = intToMonth(moisChoisi)
        view?.findViewById<TextView>(R.id.frgmBudget_tv_annee)?.text = anneeChoisi.toString()
        val moisDao = AppDatabase.getDatabase(this.requireContext()).MoisDao()
        val budgetDao = AppDatabase.getDatabase(this.requireContext()).BudgetDao()
        lifecycleScope.launch {
            var existMonth = moisDao.existMonth(anneeChoisi, moisChoisi)
            if (existMonth == 0) {
                budgets.clear()
                rcBudgets.adapter?.notifyDataSetChanged()
                Toast.makeText(context,"Aucune budget défini sur ce mois", Toast.LENGTH_LONG).show()
            } else {
                val idMois = moisDao.getMoisId(anneeChoisi, moisChoisi)
                budgets.clear()
                budgets.addAll(budgetDao.getBudgetDuMois(idMois) as ArrayList<Budget>)
                rcBudgets.adapter?.notifyDataSetChanged()
            }
        }
        setMontantTotalRevenu(moisChoisi, anneeChoisi)
    }
}

