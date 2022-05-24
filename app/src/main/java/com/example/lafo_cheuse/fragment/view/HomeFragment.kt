package com.example.lafo_cheuse.fragment.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lafo_cheuse.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    var chartView : PieChart? = null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)

        chartView = view.findViewById<PieChart>(R.id.piechart)

        val entries = ArrayList<PieEntry>()

        for (i in 0..3) {
            entries.add(PieEntry((Math.random() * 70 + 30).toFloat(), "Quarter " + (i + 1)))
        }
        val dataSet = PieDataSet(entries, "")

        dataSet.colors = ColorTemplate.PASTEL_COLORS.toList()
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 12f

        val data = PieData(dataSet)

        chartView!!.data = data
        val description = Description()
        description.textColor = Color.RED
        description.text = "Bootleg graph"
        chartView!!.description = description
        chartView!!.animateX(2000)
    }
}