package com.example.lafo_cheuse.fragment.view

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.material.RoundedBarChart
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.ExpenseSumContainer
import com.example.lafo_cheuse.viewmodels.CategoryViewModel
import com.example.lafo_cheuse.viewmodels.ExpenseViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ChartFragment : Fragment() {
    var chartView : BarChart? = null

    private val expenseViewModel : ExpenseViewModel by viewModels()
    private val categoryViewModel : CategoryViewModel by viewModels()

    private var budgetedExpenses : List<ExpenseSumContainer>? = null
    private var oneTimeExpenses : List<ExpenseSumContainer>? = null

    private var listCategories : List<Category>? = null
    private var selectedCategory : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_chart, container, false)
        initCategories(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chartView = view.findViewById<BarChart>(R.id.categoryButton)
        configureChartAppearance(100f)

    }

    private fun createGraph(entries : ArrayList<BarEntry>, maximumValue : Float) {
        configureChartAppearance(maximumValue)

        val dataSet = BarDataSet(entries, "")

        val customColors = ArrayList<Int>()
        if(entries[0].y > 50) {
            customColors.add(Color.rgb(175,236,61))
        } else if(entries[0].y > 25) {
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

    private fun configureChartAppearance(chartMaximum : Float) {
        chartView?.setTouchEnabled(false)
        chartView!!.renderer = RoundedBarChart(chartView,chartView!!.animator,chartView!!.viewPortHandler, 50)
        chartView!!.description.isEnabled = false
        chartView!!.setNoDataText(resources.getString(R.string.no_data_found))
        chartView!!.invalidate()
        chartView!!.setDrawValueAboveBar(false)
        val xAxis: XAxis = chartView!!.xAxis
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawLabels(false)

        val axisLeft: YAxis = chartView!!.axisLeft
        axisLeft.granularity = 10f
        axisLeft.axisMinimum = 0f
        axisLeft.axisMaximum = chartMaximum

        axisLeft.setDrawLabels(false)
        axisLeft.setDrawAxisLine(false)
        axisLeft.setDrawGridLines(false)

        chartView!!.axisRight.isEnabled = false
    }

    private fun initCategories(view : View) {
        categoryViewModel.getCategories()?.observe(viewLifecycleOwner) { mCategories ->
            listCategories = mCategories

            val res: Resources = resources
            val budgetName : TextView = view.findViewById<TextView>(R.id.budgetName)
            val switchButton : FloatingActionButton = view.findViewById(R.id.switchGraph)

            budgetName.text = java.lang.String.format(
                res.getString(R.string.budgetNameHolderComplete),
                mCategories[0].categoryName,
                mCategories[0].categoryEmoji
            )
            watchBudget(mCategories[selectedCategory])

            switchButton.setOnClickListener {
                incrementSelectedCategory(mCategories)
                budgetName.text = java.lang.String.format(
                    res.getString(R.string.budgetNameHolderComplete),
                    mCategories[selectedCategory].categoryName,
                    mCategories[selectedCategory].categoryEmoji
                )
                watchBudget(mCategories[selectedCategory])
            }

        }
    }

    private fun incrementSelectedCategory(list : List<Category>) {
        selectedCategory = (selectedCategory + 1) % list.size
    }

    private fun watchBudget(category: Category) {
        expenseViewModel.getMonthlyExpensesSumForCategory(category).observe(viewLifecycleOwner) { maximumExpense ->
            expenseViewModel.getOneTimeExpensesSumForCategory(category).observe(viewLifecycleOwner) { currentValue ->
                if(!(currentValue.isEmpty() || maximumExpense.isEmpty())) {
                    createGraph(convertExpensesInList(currentValue[0]),maximumExpense[0].totalAmount.toFloat())
                }
            }
        }
    }

    private fun convertExpensesInList(currentExpense : ExpenseSumContainer) : ArrayList<BarEntry> {
        val entries = ArrayList<BarEntry>()
        val y : Float = - currentExpense.totalAmount.toFloat()
        entries.add(BarEntry(0.0f, y))
        return entries
    }
}