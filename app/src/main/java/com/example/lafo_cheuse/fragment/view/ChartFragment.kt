package com.example.lafo_cheuse.fragment.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.material.RoundedBarChart
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChartFragment : Fragment() {
    var chartView : BarChart? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chartView = view.findViewById<BarChart>(R.id.specBudgetChart)
        createGraph()


    }

    private fun createGraph() {
        configureChartAppearance()
        val entries = ArrayList<BarEntry>()

        val x : Float = 0.0f
        val y: Float = (Math.random() * 100).toFloat()
        entries.add(BarEntry(x, y))

        val dataSet = BarDataSet(entries, "")

        val customColors = ArrayList<Int>()
        if(y > 50) {
            customColors.add(Color.rgb(175,236,61))
        } else if(y > 25) {
            customColors.add(Color.rgb(236,199,61))
        } else {
            customColors.add(Color.rgb(238,73,60))
        }

        dataSet.colors = customColors
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 12f

        val data = BarData(dataSet)

        chartView!!.data = data
        val description = Description()
        description.text = ""

        chartView!!.legend.isEnabled = false
        chartView!!.description = description
        chartView!!.animateX(2000)
    }

    private fun configureChartAppearance() {
        chartView?.setTouchEnabled(false);
        chartView!!.renderer = RoundedBarChart(chartView,chartView!!.animator,chartView!!.viewPortHandler, 50)
        chartView!!.description.isEnabled = false
        chartView!!.setDrawValueAboveBar(false)
        //chartView!!.setBackgroundColor(Color.rgb(121,121,121))
        val xAxis: XAxis = chartView!!.xAxis
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawLabels(false)

        val axisLeft: YAxis = chartView!!.axisLeft
        axisLeft.granularity = 10f
        axisLeft.axisMinimum = 0f
        axisLeft.axisMaximum = 100f

        axisLeft.setDrawLabels(false)
        axisLeft.setDrawAxisLine(false)
        axisLeft.setDrawGridLines(false)

        chartView!!.axisRight.isEnabled = false
    }
}