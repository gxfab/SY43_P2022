package com.example.lafo_cheuse.fragment.view

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.lafo_cheuse.MainActivity
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.material.DatabaseDate
import com.example.lafo_cheuse.models.ExpenseSumContainer
import com.example.lafo_cheuse.models.Income
import com.example.lafo_cheuse.viewmodels.CategoryViewModel
import com.example.lafo_cheuse.viewmodels.ExpenseViewModel
import com.example.lafo_cheuse.viewmodels.IncomeViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.DelicateCoroutinesApi
import java.util.*
import kotlin.collections.ArrayList

/**
 * The home fragment which will be displayed when the user arrive on the app
 *
 * @property incomeViewModel - an [IncomeViewModel] instance
 * @property expenseViewModel - an [ExpenseViewModel] instance
 * @property incomes - a list of all [Income] in the month and regular incomes
 * @property budgetedExpenses - a list of all budgeted expenses in an [ExpenseSumContainer]
 * @property oneTimeExpenses - a list of all one time expenses in an [ExpenseSumContainer]
 * @property listGraphNames - a list of the different name of the graph to display. the [indexSelectedGraph] go through it.
 * @property indexSelectedGraph - an index which will go through list [listGraphNames]
 * @property chartView - a [PieChart] where one display the data
 * @property totalDisplayer - a [TextView] where the total sum of the budget will be displayed
 *
 */
class HomeFragment() : Fragment() {
    private val incomeViewModel : IncomeViewModel by viewModels()
    private val expenseViewModel : ExpenseViewModel by viewModels()

    private var incomes : List<Income>? = null
    private var budgetedExpenses : ArrayList<ExpenseSumContainer>? = null
    private var oneTimeExpenses : List<ExpenseSumContainer>? = null

    private val listGraphNames : List<String> = listOf("Revenus","Dépenses prévues","Dépenses effectives")
    private var indexSelectedGraph : Int = 0

    var chartView : PieChart? = null
    var totalDisplayer : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_in_left)
        enterTransition = inflater.inflateTransition(R.transition.slide_in_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        val chartButton : FloatingActionButton = view.findViewById(R.id.floatingActionButton)
        val switchButton : FloatingActionButton = view.findViewById(R.id.switchGraphHome)
        val typeDisplayed : TextView = view.findViewById(R.id.budetTypeDisplayer)
        totalDisplayer = view.findViewById(R.id.total_displayer)

        typeDisplayed.text = listGraphNames[indexSelectedGraph]

        chartButton.setOnClickListener {
            displayChart()
        }

        switchButton.setOnClickListener {
            incrementIndex()
            typeDisplayed.text = listGraphNames[indexSelectedGraph]
            watchBudget()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chartView = view.findViewById<PieChart>(R.id.piechart)
        watchBudget()
    }

    /**
     * Method to manage the graph button and switch the current fragment displayed to the [ChartFragment]
     *
     */
    @OptIn(DelicateCoroutinesApi::class)
    fun displayChart() {
        (this.activity as MainActivity).navigationView!!.selectedItemId = R.id.chartFragment
    }

    /**
     * Function to increment the [indexSelectedGraph] property which choose which graph to display
     *
     */
    fun incrementIndex() {
        indexSelectedGraph = (indexSelectedGraph + 1) % listGraphNames.size
    }

    /**
     * Function that will watch the budget from the 3 different graphs :
     * - The income graph : all the regular incomes + incomes of the month are displayed
     * - The regular expense graph : all the regular expenses are displayed by category
     * - the one time expense graph : all the one time expenses are displayed also by category
     */
    private fun watchBudget() {
        val today : DatabaseDate = convertDateInDatabaseDate(Calendar.getInstance())
        when(listGraphNames[indexSelectedGraph]) {
            "Revenus"-> {
                incomeViewModel.getIncomesForMonth(today.year,today.month).observe(viewLifecycleOwner) { mIncomes ->
                    incomes = mIncomes
                    createGraph(convertIncomesInList(incomes!!))
                }
                incomeViewModel.getIncomeSumByDate(today.year,today.month).observe(viewLifecycleOwner) { sum ->
                    totalDisplayer?.text = if(sum == null) {
                        resources.getString(R.string.budget_total_full,0.0)
                    } else {
                        resources.getString(R.string.budget_total_full, sum)
                    }
                }
            }
            "Dépenses prévues"-> {
                expenseViewModel.getMonthlyExpensesSumByCategory().observe(viewLifecycleOwner) { mExpenses ->
                    budgetedExpenses = mExpenses as ArrayList<ExpenseSumContainer>?
                    expenseViewModel.getMonthlyExpensesSumByDate(today.year,today.month).observe(viewLifecycleOwner) { _expenseSum ->
                        incomeViewModel.getIncomeSumByDate(today.year,today.month).observe(viewLifecycleOwner) { _incomeSum ->
                            var incomeSum : Double? = _incomeSum
                            var expenseSum : Double? = _expenseSum
                            if(expenseSum == null) expenseSum = 0.0
                            if(incomeSum == null) incomeSum = 0.0
                            if(expenseSum + incomeSum != 0.0) {
                                val nonAllocatedSum = ExpenseSumContainer("Non alloué","❌",-(expenseSum + incomeSum))
                                budgetedExpenses?.add(nonAllocatedSum)
                            }
                            createGraph(convertExpensesInList(budgetedExpenses!!))
                        }

                    }

                }
                expenseViewModel.getMonthlyExpensesSum().observe(viewLifecycleOwner) { sum ->
                    totalDisplayer?.text = if(sum == null) {
                        resources.getString(R.string.budget_total_full,0.0)
                    } else {
                        resources.getString(R.string.budget_total_full, -sum)
                    }
                }
             }
            "Dépenses effectives" -> {
                expenseViewModel.getOneTimeExpensesSumByCategoryAndMonth(today.year,today.month).observe(viewLifecycleOwner) { mExpenses ->
                    oneTimeExpenses = mExpenses
                    createGraph(convertExpensesInList(oneTimeExpenses!!))
                }
                expenseViewModel.getOneTimeExpensesSumByDate(today.year,today.month).observe(viewLifecycleOwner) { sum ->
                    totalDisplayer?.text = if(sum == null) {
                        resources.getString(R.string.budget_total_full,0.0)
                    } else {
                        resources.getString(R.string.budget_total_full,-sum)
                    }
                }

            }
        }

    }

    /**
     * Method that create a pie chart from a list of entries
     *
     * @param entries a list of PieEntry from the MPAndroidChart library
     */
    private fun createGraph(entries : ArrayList<PieEntry>) {
        configureChartAppearance()

        val dataSet = setGraphData(entries)
        val data = PieData(dataSet)

        chartView!!.data = data
        val description = Description()
        description.textColor = Color.RED
        description.text = ""

        chartView!!.legend.isEnabled = false
        chartView!!.description = description
        chartView!!.animateX(2000)
    }

    /**
     * Convert a list of incomes into a List of PieEntry to be displayed in the graph
     *
     * @param mIncomes - list of incomes to convert
     * @return an ArrayList of PieEntry
     */
    private fun convertIncomesInList(mIncomes : List<Income>) : ArrayList<PieEntry> {
        val entries = ArrayList<PieEntry>()
        for(income in mIncomes) {
            entries.add(PieEntry(income.amount.toFloat(), income.name+" "+ (income.category?.categoryEmoji)))
        }
        return entries
    }

    /**
     * Convert a list of expenses into a List of PieEntry to be displayed in the graph
     *
     * @param mExpenses - list of expenses to convert
     * @return an ArrayList of PieEntry
     */
    private fun convertExpensesInList(mExpenses : List<ExpenseSumContainer>) : ArrayList<PieEntry> {
        val entries = ArrayList<PieEntry>()
        for(expense in mExpenses) {
            entries.add(PieEntry(-expense.totalAmount.toFloat(), expense.category_name+" "+ (expense.category_emoji)))
        }
        return entries
    }

    /**
     * A Specific function to set the graphical aspect of the data displayed and put it in a DataSet
     *
     * @param entries - an ArrayList of PieEntry on which we want to put a specific aspect
     * @return a PieDataSet, an object specific to MPAndroidChart which contain data and graphical constraints
     */
    private fun setGraphData(entries : ArrayList<PieEntry>) : PieDataSet {
        val dataSet = PieDataSet(entries, "")

        dataSet.colors = ColorTemplate.PASTEL_COLORS.toList()
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 12f
        return dataSet
    }

    /**
     * A function that will manage the appearance of the pie chart from MPAndroidChart library
     *
     */
    private fun configureChartAppearance() {
        chartView?.setTouchEnabled(false)
        chartView!!.description.isEnabled = false
    }

    /**
     * Small function to convert a calendar date to DatabaseDate object
     *
     * @param calendar - a Calendar object from java.utils which one wants to convert
     * @return a DatabaseDate object with all the [calendar] data
     */
    private fun convertDateInDatabaseDate(calendar: Calendar) : DatabaseDate {
        return DatabaseDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(
            Calendar.DAY_OF_MONTH))
    }

}