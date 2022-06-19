package com.example.alovemony_ip.fragments
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alovemony_ip.repository.DepenseRepository.Singleton.depenseList
import com.example.alovemony_ip.MainActivity
import com.example.alovemony_ip.R
import com.example.alovemony_ip.adapter.DepenseAdapter
import com.example.alovemony_ip.adapter.ProjetItemDecoration
import com.example.alovemony_ip.repository.CategorieRepository.Singleton.categorieList
import com.example.alovemony_ip.repository.IncomeRepository.Singleton.incomeList
import com.example.alovemony_ip.repository.UserRepository
import com.example.alovemony_ip.repository.UserRepository.Singleton.userList
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class AccueilFragment(private val context: MainActivity):Fragment() {


    private lateinit var pieChart : PieChart
    private lateinit var radarChart : RadarChart

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_accueil, container, false)
        val bouton_view_graphique = view.findViewById<ImageButton>(R.id.bouton_change_graphique)

        val name_view = view.findViewById<TextView>(R.id.nom_utilisateur)
        var montant_totale = view.findViewById<TextView>(R.id.argent_totale)

        name_view.text  = userList[0].name
        montant_totale.text  = userList[0].incomeTotale.toString()
        updateValIncome(view)

        /*
                On instancie les graphiques
         */
        radarChart = view.findViewById<RadarChart>(R.id.radar_chart)
        pieChart = view.findViewById<PieChart>(R.id.pie_chart)
        radarChart.visibility = View.INVISIBLE


        /*
             On paramètre les graphiques avec les données qu'on a actuellement
         */
        initPieChart()
        setDataPieChart()
        setDataRadarChart()

        // Bouton permettant d'alterné entre les deux graphiques

        bouton_view_graphique.setOnClickListener {
            if(pieChart.visibility == View.VISIBLE)
            {
                pieChart.visibility = View.INVISIBLE
                radarChart.visibility = View.VISIBLE
            }else{
                pieChart.visibility = View.VISIBLE
                radarChart.visibility = View.INVISIBLE
            }
        }

        return view
    }

    /*
     Fonction permettant d'actualliser le montant actuelle de l'utilisateur, ainsi que la progresse Bar
     */
    fun updateValIncome(view : View)
    {
        val incomeText = view.findViewById<TextView>(R.id.argent_restant)
        var income : Int = 0
        income = userList[0].currentMoney
        incomeText.text = income.toString()

        val incomeProgressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        incomeProgressBar.progress = userList[0].currentMoney
        incomeProgressBar.max = userList[0].incomeTotale
    }

    /*
            On assigne les données du graphique "Radar" en fonctio des données de l'application
     */
    private fun setDataRadarChart()
    {

        val dataRadarEntries1 = ArrayList<RadarEntry>()

        for(categorie in categorieList)
        {
            dataRadarEntries1.add(RadarEntry(categorie.montantPrev.toFloat()))
        }

        val dataRadarEntries2 = ArrayList<RadarEntry>()

        for(categorie in categorieList)
        {
            dataRadarEntries2.add(RadarEntry(categorie.montant.toFloat()))
        }

        val dataRadarSet1 = RadarDataSet(dataRadarEntries1," Previous month")
        val dataRadarSet2 = RadarDataSet(dataRadarEntries2,"Current month")

        dataRadarSet1.setColor(Color.RED)
        dataRadarSet1.fillColor = Color.RED
        dataRadarSet1.setLineWidth(2f)
        dataRadarSet1.setDrawFilled(true)
        dataRadarSet1.setValueTextColor(Color.RED)

        dataRadarSet2.setColor(Color.BLUE)
        dataRadarSet2.fillColor = Color.BLUE
        dataRadarSet2.setLineWidth(2f)
        dataRadarSet2.setDrawFilled(true)
        dataRadarSet2.setValueTextColor(Color.BLUE)

        val dataRadar = RadarData()
        dataRadar.addDataSet(dataRadarSet1)
        dataRadar.addDataSet(dataRadarSet2)


        val xlabels = ArrayList<String>()

        for(categorie in categorieList)
        {
            xlabels.add(categorie.name)
        }

        /*
        xlabels.add("20")
        xlabels.add("21")
        xlabels.add("22")
        xlabels.add("23")
        xlabels.add("24")
*/

        radarChart.yAxis.textSize = 2f
        radarChart.xAxis.textSize = 3f
        radarChart.xAxis.valueFormatter = IndexAxisValueFormatter(xlabels)
        radarChart.description.text = "Comparaison repartiton categorie"
        radarChart.data = dataRadar
        radarChart.description.isEnabled = false
        radarChart.invalidate()

    }
    // On initialise les paramètres du graphique 'Pie'
    private fun initPieChart()
    {
        pieChart.setUsePercentValues(false)
        pieChart.description.isEnabled = false
        pieChart.setEntryLabelTextSize(15f)
        pieChart.isDrawHoleEnabled =true
        pieChart.setEntryLabelColor(Color.BLACK)


        //hollow pie chart
        //adding padding
        pieChart.setExtraOffsets(20f, 0f, 20f, 20f)
        // pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart.legend.isWordWrapEnabled = true
    }

    /*
           On assigne les données du graphique "Pie" en fonctio des données de l'application
    */
    private fun setDataPieChart()
    {
        val dataEntries = ArrayList<PieEntry>()


        val colors: ArrayList<Int> = ArrayList()
        for(color in ColorTemplate.MATERIAL_COLORS)
        {
            colors.add(color)
        }

        for(categorie in categorieList)
        {
            dataEntries.add(PieEntry(categorie.montant.toFloat(),categorie.name))
        }


        val dataSet = PieDataSet(dataEntries,"")
        val data = PieData(dataSet)

        // In Percentage

        //data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        pieChart.data = data
        data.setValueTextSize(15f)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        //create hole in center
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)

        //add text in center
        pieChart.setDrawCenterText(true);
        pieChart.centerText = "Repartition Category"

    }
}
