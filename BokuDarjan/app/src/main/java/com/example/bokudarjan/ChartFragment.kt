package com.example.bokudarjan


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.bokudarjan.bmonth.BMonth
import com.example.bokudarjan.bmonth.BMonthViewModel
import com.example.bokudarjan.envelope.EnvelopeViewModel
import com.example.bokudarjan.expense.ExpenseViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.fragment_month.view.*


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
        val chart = view.findViewById(R.id.chart) as BarChart

        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        envelopeViewModel = ViewModelProvider(this).get(EnvelopeViewModel::class.java)
        bMonthViewModel = ViewModelProvider(this).get(BMonthViewModel::class.java)

        var pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val month = pref.getInt("month", -1)

        var sumAmount : ArrayList<Float> = ArrayList<Float>()


        val entries: MutableList<BarEntry> = ArrayList()
        //Add sum to barchart
        bMonthViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            for(month in it){
                sumAmount.add(0f)
                envelopeViewModel.getSumOfEnvelopes(month.id).observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        sumAmount.set(month.id, sumAmount.get(month.id) + it)
                    }
                    expenseViewModel.getSumOfNegativeExpenses(month.id).observe(viewLifecycleOwner, Observer {
                        if (it != null) {
                            sumAmount.set(month.id, sumAmount.get(month.id) - it)

                            Log.d("ChartFragment", "1:" + (month.id-1).toFloat() + "--2:" + sumAmount.get(month.id))
                            //entries.add(BarEntry((month.id-1).toFloat(), sumAmount.get(month.id)))
                            Log.d("ChartFragment", "-sumAmount final: " + sumAmount.get(month.id) + " for month " + month.id)
                        }
                    })
                })
            }
        })






        /*entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 80f))
        entries.add(BarEntry(2f, 60f))
        entries.add(BarEntry(3f, 50f))
        // gap of 2f
        entries.add(BarEntry(5f, 70f))
        entries.add(BarEntry(6f, 60f))*/

        val set = BarDataSet(entries, "BarDataSet")

        val data = BarData(set)
        data.barWidth = 0.9f // set custom bar width

        chart.data = data
        chart.setFitBars(true) // make the x-axis fit exactly all bars

        chart.invalidate() // refresh

        Log.d("ChartFragment", "exiting chart fragment")


        return view
    }




}