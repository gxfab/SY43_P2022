package com.example.lafo_cheuse.fragment.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.BudgetSetterActivity
import com.example.lafo_cheuse.CreateIncomeExpenseActivity
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.material.ExpenseAdapter
import com.example.lafo_cheuse.material.ExpenseSetterAdapter
import com.example.lafo_cheuse.material.IncomeSetterAdapter
import com.example.lafo_cheuse.viewmodels.ExpenseViewModel
import com.example.lafo_cheuse.viewmodels.IncomeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * Fragment where the one time expenses are dispalyed and where we can modify them.
 * We can also add a regular income/expense by clicking on the Calendar button.
 *
 */
class SetIncomesExpensesFragment : Fragment() {
    private var expenseAdapter: ExpenseAdapter? = null
    private val incomeViewModel: IncomeViewModel by viewModels()
    private val expenseViewModel: ExpenseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_set_incomes_expenses, container, false)
        val  budgetDisplayButton : FloatingActionButton = view.findViewById(R.id.budgetSetterFragment)
        val incomeExpenseSetterButton : FloatingActionButton = view.findViewById(R.id.incomeExpenseSetterFragment)
        val recyclerView : RecyclerView = view.findViewById<RecyclerView>(R.id.ie_recycler)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        expenseAdapter = ExpenseAdapter(context as Activity)
        recyclerView.adapter = expenseAdapter

        expenseViewModel.getOneTimeExpense().observe(viewLifecycleOwner) { list ->
            expenseAdapter!!.setExpenses(list)
        }

        budgetDisplayButton.setOnClickListener {
            val intent = Intent(activity, BudgetSetterActivity::class.java)
            startActivity(intent)
        }

        incomeExpenseSetterButton.setOnClickListener {
            val intent = Intent(activity, CreateIncomeExpenseActivity::class.java)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        val adapter = activity?.let { ExpenseAdapter(it) }
        recyclerView.adapter = adapter
        return view
    }

}