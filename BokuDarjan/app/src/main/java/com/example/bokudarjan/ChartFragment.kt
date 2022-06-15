package com.example.bokudarjan


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


class ChartFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chart, container, false)
        val chart = view.findViewById(R.id.chart) as BarChart

      /*  val data = BarData(getXAxisValues(), getDataSet())
        chart.data = data
        chart.description.text = "My chart"
        chart.animateXY(2000, 2000)
        chart.invalidate() */

        val entries: MutableList<BarEntry> = ArrayList()
        entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 80f))
        entries.add(BarEntry(2f, 60f))
        entries.add(BarEntry(3f, 50f))
        // gap of 2f
        // gap of 2f
        entries.add(BarEntry(5f, 70f))
        entries.add(BarEntry(6f, 60f))

        val set = BarDataSet(entries, "BarDataSet")

        val data = BarData(set)
        data.barWidth = 0.9f // set custom bar width

        chart.data = data
        chart.setFitBars(true) // make the x-axis fit exactly all bars

        chart.invalidate() // refresh

        return view
    }

    /*private fun getDataSet(): IBarDataSet? {
        var dataSets: ArrayList<IBarDataSet?>() = null
        val valueSet1: ArrayList<BarEntry> = ArrayList()
        val v1e1 = BarEntry(110.000f, 0f) // Jan
        valueSet1.add(v1e1)
        val v1e2 = BarEntry(40.000f, 1f) // Feb
        valueSet1.add(v1e2)
        val v1e3 = BarEntry(60.000f, 2f) // Mar
        valueSet1.add(v1e3)
        val v1e4 = BarEntry(30.000f, 3f) // Apr
        valueSet1.add(v1e4)
        val v1e5 = BarEntry(90.000f, 4f) // May
        valueSet1.add(v1e5)
        val v1e6 = BarEntry(100.000f, 5f) // Jun
        valueSet1.add(v1e6)
        val valueSet2: ArrayList<BarEntry> = ArrayList()
        val v2e1 = BarEntry(150.000f, 0f) // Jan
        valueSet2.add(v2e1)
        val v2e2 = BarEntry(90.000f, 1f) // Feb
        valueSet2.add(v2e2)
        val v2e3 = BarEntry(120.000f, 2f) // Mar
        valueSet2.add(v2e3)
        val v2e4 = BarEntry(60.000f, 3f) // Apr
        valueSet2.add(v2e4)
        val v2e5 = BarEntry(20.000f, 4f) // May
        valueSet2.add(v2e5)
        val v2e6 = BarEntry(80.000f, 5f) // Jun
        valueSet2.add(v2e6)
        val barDataSet1 = BarDataSet(valueSet1, "Brand 1")
        barDataSet1.color = Color.rgb(0, 155, 0)
        val barDataSet2 = BarDataSet(valueSet2, "Brand 2")
        barDataSet2.setColors(*ColorTemplate.COLORFUL_COLORS)
        dataSets = ArrayList<IBarDataSet?>()
        dataSets.add(barDataSet1)
        dataSets.add(barDataSet2)
        return dataSets
    }

    private fun getXAxisValues(): List<String> {
        val xAxis: ArrayList<String> = ArrayList()
        xAxis.add("JAN")
        xAxis.add("FEB")
        xAxis.add("MAR")
        xAxis.add("APR")
        xAxis.add("MAY")
        xAxis.add("JUN")
        return xAxis
    }*/



}