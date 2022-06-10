package com.example.bokudarjan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.expense.ExpenseViewModel
import com.example.bokudarjan.expense.ListAdapterExpense
import kotlinx.android.synthetic.main.fragment_list_expense.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [listExpenseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListExpenseFragment : Fragment() {

    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         Log.i("ListExpenseFragment", "Entering fragment view")
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_expense, container, false)

        //RecyclerView
        val adapter = ListAdapterExpense()
        val recyclerView : RecyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ExpenseViewModel
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        expenseViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        return view
    }

}