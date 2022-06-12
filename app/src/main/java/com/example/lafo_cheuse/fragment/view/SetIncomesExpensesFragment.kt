package com.example.lafo_cheuse.fragment.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.BudgetSetterActivity
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.material.CategoryAdapter
import com.example.lafo_cheuse.material.ExpenseAdapter
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_set_incomes_expenses, container, false)
        val  budgetDisplayButton : FloatingActionButton = view.findViewById(R.id.budgetSetterFragment)

        budgetDisplayButton.setOnClickListener {
            val intent = Intent(activity, BudgetSetterActivity::class.java)
            startActivity(intent)
        }
        val recyclerView : RecyclerView = view.findViewById<RecyclerView>(R.id.ie_recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        val adapter = activity?.let { ExpenseAdapter(it) }
        recyclerView.adapter = adapter
        return view
    }
}