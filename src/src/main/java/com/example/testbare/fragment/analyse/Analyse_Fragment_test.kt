package com.example.testbare.fragment.analyse;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.testbare.MonthYearPickerDialog
import com.example.testbare.R
import com.example.testbare.database.AppDatabase
import kotlinx.coroutines.launch


class Analyse_Fragment_test : Fragment(), MonthYearPickerDialog.MonthListener {

    lateinit var graphBudget : Pie
    lateinit var graphDepense : Pie
    lateinit var rcGraph : RecyclerView
    var listGraph : ArrayList<Pie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_analyse_test, container, false)
        val btnChoixMois = view.findViewById<ImageButton>(R.id.frgmAnalyse_imgBtn_mois)
        rcGraph = view.findViewById<RecyclerView>(R.id.frgmAnalyse_rc_graph)
        rcGraph.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rcGraph.adapter = AnalyseView(listGraph)


        onOkDialogMonthClick(5,2022)

        btnChoixMois.setOnClickListener{
            val pd = MonthYearPickerDialog()
            pd.setListener(this)
            pd.show(this.requireActivity().supportFragmentManager, "MonthYearPickerDialog")
        }

        return view
    }


    override fun onOkDialogMonthClick(moisChoisi: Int, anneeChoisi: Int) {
        val context = requireContext()
        val moisDao = AppDatabase.getDatabase(context).MoisDao()
        val budgetDao = AppDatabase.getDatabase(context).BudgetDao()
        val depenseDao = AppDatabase.getDatabase(context).DepenseDao()
        view?.findViewById<TextView>(R.id.frgmAnalyse_tv_annee)?.text = anneeChoisi.toString()
        view?.findViewById<TextView>(R.id.frgmAnalyse_tv_mois)?.text = intToMonth(moisChoisi)
        var datafind = false
        listGraph.clear()
        lifecycleScope.launch {
            if (moisDao.existMonth(anneeChoisi, moisChoisi) == 0)
                Toast.makeText(context, "Aucune donnée sur ce mois", Toast.LENGTH_SHORT).show()
            else{
                val idMois = moisDao.getMoisId(anneeChoisi, moisChoisi)
                if(budgetDao.existMonthBudget(idMois) == 0) {
                    Toast.makeText(context, "Aucun budget défini sur ce mois", Toast.LENGTH_SHORT).show()
                }else {
                    datafind = true
                    val budgetsMoi = budgetDao.getBudgetDuMois(idMois)
                    val datas: MutableList<DataEntry> = ArrayList()
                    budgetsMoi.forEach { datas.add(ValueDataEntry(it.bud_categorie, it.bud_montant)) }
                    graphBudget = AnyChart.pie()
                    graphBudget.title("Rapport budgétisation du mois")
                    graphBudget.labels().position("outside")
                    graphBudget.data(datas)
                }
                if(depenseDao.existMonthDepense(idMois) != 0){
                    val depensesMoi = depenseDao.getDepenseDuMoisRegroupe(idMois)
                    val datas: MutableList<DataEntry> = ArrayList()
                    depensesMoi.forEach { datas.add(ValueDataEntry(it.categorie, it.montant)) }
                    graphDepense = AnyChart.pie()
                    graphDepense.title("Rapport sur les dépenses du mois")
                    graphDepense.labels().position("outside")
                    graphDepense.data(datas)
                }
            }
        }
        listGraph.add(graphBudget)
        listGraph.add(graphDepense)
        rcGraph.adapter?.notifyDataSetChanged()
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
}
