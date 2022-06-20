package com.example.fluz.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.repositories.BudgetRepository
import com.example.fluz.data.repositories.CategoryRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentStatisticsBinding
import com.example.fluz.ui.viewmodels.CategoriesViewModel
import com.example.fluz.ui.viewmodels.CategoriesViewModelFactory
import com.example.fluz.ui.viewmodels.StatisticsViewModel
import com.example.fluz.ui.viewmodels.StatisticsViewModelFactory
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

/**
 * Statistics fragment
 *
 */
class Statistics : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val budgetRepository by lazy { BudgetRepository(database.BudgetDao()) }

    private val statisticsViewModel: StatisticsViewModel by viewModels {
        StatisticsViewModelFactory(userRepository, budgetRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticsBinding.inflate(layoutInflater)

        val sharedPref = this.activity!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        val barChart = binding.barChart

        val barWidth: Float
        val barSpace: Float
        val groupSpace: Float
        val groupCount = 2

        barWidth = 0.15f
        barSpace = 0.07f
        groupSpace = 0.56f

        statisticsViewModel.data.observe(this) {data ->
            println(data)
            var xAxisValues = ArrayList<String>()
            for ((k, v) in data) {
                xAxisValues.add(k.categoryId.toString())
            }

            var yValueGroup1 = ArrayList<BarEntry>()
            var yValueGroup2 = ArrayList<BarEntry>()

            // draw the graph
            var barDataSet1: BarDataSet
            var barDataSet2: BarDataSet


            var i: Float = 1f
            for ((k, v) in data) {
                yValueGroup1.add(BarEntry(i, k.amount.toFloat()))
                yValueGroup2.add(BarEntry(i, v.toFloat()))

                i++
            }


            barDataSet1 = BarDataSet(yValueGroup1, "")
            barDataSet1.setColors(Color.BLUE, Color.RED)
            barDataSet1.label = "2016"
            barDataSet1.setDrawIcons(false)
            barDataSet1.setDrawValues(false)



            barDataSet2 = BarDataSet(yValueGroup2, "")

            barDataSet2.label = "2017"
            barDataSet2.setColors(Color.YELLOW, Color.RED)

            barDataSet2.setDrawIcons(false)
            barDataSet2.setDrawValues(false)

            var barData = BarData(barDataSet1, barDataSet2)

            barChart.description.isEnabled = false
            barChart.description.textSize = 0f
            barData.setValueFormatter(LargeValueFormatter())
            barChart.setData(barData)
            barChart.getBarData().setBarWidth(barWidth)
            barChart.getXAxis().setAxisMinimum(0f)
            barChart.getXAxis().setAxisMaximum(groupCount.toFloat())
            barChart.groupBars(0f, groupSpace, barSpace)
            //   barChartView.setFitBars(true)
            barChart.getData().setHighlightEnabled(false)
            barChart.invalidate()

            // set bar label
            var legend = barChart.legend
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL)
            legend.setDrawInside(false)

            var legenedEntries = arrayListOf<LegendEntry>()

            legenedEntries.add(LegendEntry("2016", Legend.LegendForm.SQUARE, 8f, 8f, null, Color.RED))
            legenedEntries.add(LegendEntry("2017", Legend.LegendForm.SQUARE, 8f, 8f, null, Color.YELLOW))

            legend.setCustom(legenedEntries)

            legend.setYOffset(2f)
            legend.setXOffset(2f)
            legend.setYEntrySpace(0f)
            legend.setTextSize(5f)

            val xAxis = barChart.getXAxis()
            xAxis.setGranularity(1f)
            xAxis.setGranularityEnabled(true)
            xAxis.setCenterAxisLabels(true)
            xAxis.setDrawGridLines(false)
            xAxis.textSize = 9f

            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
            xAxis.setValueFormatter(IndexAxisValueFormatter(xAxisValues))

            xAxis.setLabelCount(12)
            xAxis.mAxisMaximum = 12f
            xAxis.setCenterAxisLabels(true)
            xAxis.setAvoidFirstLastClipping(true)
            xAxis.spaceMin = 4f
            xAxis.spaceMax = 4f

            barChart.setVisibleXRangeMaximum(12f)
            barChart.setVisibleXRangeMinimum(12f)
            barChart.setDragEnabled(true)

            //Y-axis
            barChart.getAxisRight().setEnabled(false)
            barChart.setScaleEnabled(true)

            val leftAxis = barChart.getAxisLeft()
            leftAxis.setValueFormatter(LargeValueFormatter())
            leftAxis.setDrawGridLines(false)
            leftAxis.setSpaceTop(1f)
            leftAxis.setAxisMinimum(0f)


            barChart.data = barData
            barChart.setVisibleXRange(1f, data.size.toFloat())
        }

        statisticsViewModel.getData(connectedUserId.toInt())



        return binding.root
    }
}