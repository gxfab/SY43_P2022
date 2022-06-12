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
import com.example.lafo_cheuse.material.DatabaseDate
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
import java.util.*
import kotlin.collections.ArrayList

/**
 * The fragment where the remaining expenses to do are displayed by category
 *
 * @property chartView - a [BarChart] where the informations will be displayed
 * @property expenseViewModel - an instance of [ExpenseViewModel]
 * @property categoryViewModel - an instance of [CategoryViewModel]
 * @property listCategories - a list of all the different categories. One use the [selectedCategory] index to go through it.
 * @property selectedCategory - an index to go through [listCategories]
 *
 */
class ChartFragment : Fragment() {
    var chartView : BarChart? = null

    private val expenseViewModel : ExpenseViewModel by viewModels()
    private val categoryViewModel : CategoryViewModel by viewModels()

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
        chartView = view.findViewById(R.id.categoryButton)
        configureChartAppearance(100f)

    }

    /**
     * A method which will create a bar chart and display it on the fragment
     *
     * @param entries - An [ArrayList] of [BarEntry] with the data to put on the graph
     * @param maximumValue - A [Float] which stand for the maximum of the gauge (the ceiling of expenses)
     */
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

    /**
     * Method to configure the chart appearance. We use a specific renderer in this method.
     *
     * @param chartMaximum - a [Float] which set the maximum value of the gauge
     */
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

    /**
     * Method to initialize the categories and put hem in the [listCategories] list. Then, we display them in budgetName constance.
     *
     * @param view - The inflater of the [ChartFragment] instance
     */
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

    /**
     * small method which increment the [selectedCategory] property
     *
     * @param list - a list of [Category] to choose which one to display
     */
    private fun incrementSelectedCategory(list : List<Category>) {
        selectedCategory = (selectedCategory + 1) % list.size
    }

    /**
     * A method to watch the budget and create the required graph.
     *
     * @param category - a [Category] to display the expenses of
     */
    private fun watchBudget(category: Category) {
        val today : DatabaseDate = convertDateInDatabaseDate(Calendar.getInstance())
        expenseViewModel.getMonthlyExpensesSumForCategory(category).observe(viewLifecycleOwner) { maximumExpense ->
            expenseViewModel.getOneTimeExpensesSumForCategoryAndMonth(category,today.year,today.month).observe(viewLifecycleOwner) { currentValue ->
                if(!(currentValue.isEmpty() || maximumExpense.isEmpty())) {
                    createGraph(convertExpensesInList(currentValue[0]),maximumExpense[0].totalAmount.toFloat())
                }
            }
        }
    }

    /**
     * Method to convert an [ExpenseSumContainer] into a list of [BarEntry]
     *
     * @param currentExpense - the [ExpenseSumContainer] to convert
     * @return an [ArrayList] of [BarEntry] with only one variable : the amount of [currentExpense]
     */
    private fun convertExpensesInList(currentExpense : ExpenseSumContainer) : ArrayList<BarEntry> {
        val entries = ArrayList<BarEntry>()
        val y : Float = - currentExpense.totalAmount.toFloat()
        entries.add(BarEntry(0.0f, y))
        return entries
    }

    /**
     * Small function to convert a calendar date to DatabaseDate object
     *
     * @param calendar - a Calendar object from java.utils which one wants to convert
     * @return a DatabaseDate object with all the [calendar] data
     */
    private fun convertDateInDatabaseDate(calendar: Calendar) : DatabaseDate {
        return DatabaseDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
    }
}