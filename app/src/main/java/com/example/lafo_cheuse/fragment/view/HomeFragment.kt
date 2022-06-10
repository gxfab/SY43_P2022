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

class HomeFragment() : Fragment() {
    private val incomeViewModel : IncomeViewModel by viewModels()
    private val expenseViewModel : ExpenseViewModel by viewModels()

    private var incomes : List<Income>? = null
    private var budgetedExpenses : List<ExpenseSumContainer>? = null
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

    @OptIn(DelicateCoroutinesApi::class)
    fun displayChart() {
        (this.activity as MainActivity).navigationView!!.selectedItemId = R.id.chartFragment
    }

    fun incrementIndex() {
        indexSelectedGraph = (indexSelectedGraph + 1) % listGraphNames.size
    }

    private fun watchBudget() {
        when(listGraphNames[indexSelectedGraph]) {
            "Revenus"-> {
                incomeViewModel.getIncomes()?.observe(viewLifecycleOwner) { mIncomes ->
                    incomes = mIncomes
                    createGraph(convertIncomesInList(incomes!!))
                }
                incomeViewModel.getIncomeSum().observe(viewLifecycleOwner) { sum ->
                    totalDisplayer?.text = if(sum == null) {
                        resources.getString(R.string.budget_total_full,0.0)
                    } else {
                        resources.getString(R.string.budget_total_full, sum)
                    }
                }
            }
            "Dépenses prévues"-> {
                expenseViewModel.getMonthlyExpensesSumByCategory().observe(viewLifecycleOwner) { mExpenses ->
                    budgetedExpenses = mExpenses
                    createGraph(convertExpensesInList(budgetedExpenses!!))
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
                expenseViewModel.getOneTimeExpensesSumByCategory().observe(viewLifecycleOwner) { mExpenses ->
                    oneTimeExpenses = mExpenses
                    createGraph(convertExpensesInList(oneTimeExpenses!!))
                }
                expenseViewModel.getOneTimeExpensesSum().observe(viewLifecycleOwner) { sum ->
                    totalDisplayer?.text = if(sum == null) {
                        resources.getString(R.string.budget_total_full,0.0)
                    } else {
                        resources.getString(R.string.budget_total_full,-sum)
                    }
                }

            }
        }

    }

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

    private fun convertIncomesInList(mIncomes : List<Income>) : ArrayList<PieEntry> {
        val entries = ArrayList<PieEntry>()
        for(income in mIncomes) {
            entries.add(PieEntry(income.amount.toFloat(), income.name+" "+ (income.category?.categoryEmoji)))
        }
        return entries
    }

    private fun convertExpensesInList(mExpenses : List<ExpenseSumContainer>) : ArrayList<PieEntry> {
        val entries = ArrayList<PieEntry>()
        for(expense in mExpenses) {
            entries.add(PieEntry(-expense.totalAmount.toFloat(), expense.category_name+" "+ (expense.category_emoji)))
        }
        return entries
    }

    private fun setGraphData(entries : ArrayList<PieEntry>) : PieDataSet {
        val dataSet = PieDataSet(entries, "")

        dataSet.colors = ColorTemplate.PASTEL_COLORS.toList()
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 12f
        return dataSet
    }

    private fun configureChartAppearance() {
        chartView?.setTouchEnabled(false)
        chartView!!.description.isEnabled = false
    }

}