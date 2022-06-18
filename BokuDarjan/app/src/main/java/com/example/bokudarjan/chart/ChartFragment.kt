package com.example.bokudarjan.chart


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bokudarjan.R
import com.example.bokudarjan.bmonth.BMonthViewModel
import com.example.bokudarjan.envelope.EnvelopeViewModel
import com.example.bokudarjan.expense.ExpenseViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


class ChartFragment : Fragment() {

    private lateinit var envelopeViewModel: EnvelopeViewModel
    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var bMonthViewModel: BMonthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("ChartFragment", "entering chart fragment")

        val view = inflater.inflate(R.layout.fragment_chart, container, false)

        getDataForChart() // Also call the displayGraph function

        return view
    }

    /**
     * Get data through observables
     * Call function generateGraph to display them
     */
    fun getDataForChart(){
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        envelopeViewModel = ViewModelProvider(this).get(EnvelopeViewModel::class.java)
        bMonthViewModel = ViewModelProvider(this).get(BMonthViewModel::class.java)

        var pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val month = pref.getInt("month", -1)

        var sumAmount : ArrayList<Float> = ArrayList<Float>() // List of sum per month
        val entries: MutableList<BarEntry> = ArrayList() // Chart entries

        //Add sum to barchart and display barchart
        bMonthViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            var lastMonthId : Int = 0
            for(month in it)
                lastMonthId = month.id
            for(month in it){
                sumAmount.add(0f)
                envelopeViewModel.getSumOfEnvelopes(month.id).observe(viewLifecycleOwner, Observer {
                    Log.d("ChartFragment", "Envelope observer from month : " + month.id)
                    if (it != null) {
                        sumAmount.set(month.id, sumAmount.get(month.id) + it)
                        entries.add(BarEntry((month.id-1).toFloat(), sumAmount.get(month.id)))
                    }

                    // If we are at the last month, generate graph
                    if(month.id == lastMonthId){
                        val set = BarDataSet(entries, "Somme des envelopes")
                        generateGraph(set)

                    }

                })

            }
        })
    }
    /**
     *      Generate graph with the provided BarDataSet
     */
    private fun generateGraph(barDataSet: BarDataSet) {

        val chart = view?.findViewById(R.id.chart) as BarChart
        val data = BarData(barDataSet)

        // Set graph style
        barDataSet.color = Color.parseColor("#005e32")
        barDataSet.formSize = 15f
        barDataSet.valueTextSize = 20f
        data.barWidth = 0.9f
        val rightAxis: YAxis = chart.getAxisRight()
        rightAxis.setDrawAxisLine(false)
        // Animation
        chart.animateY(1000);
        chart.animateX(1000);

        chart.data = data
        chart.setFitBars(true)
        chart.invalidate()
    }



}