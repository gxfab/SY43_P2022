package com.example.bokudarjan

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.expense.ExpenseViewModel
import com.example.bokudarjan.expense.ListAdapterExpense
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_expenses.view.*
import kotlinx.android.synthetic.main.fragment_list_expense.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExpensesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExpensesFragment : Fragment() {

    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_expenses, container, false)



        val tv = Space(view.context)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            60
        )
        tv.layoutParams = layoutParams


        Log.i("ListAdapterExpense", "Entering fragment view")


        //RecyclerView
        val adapter = ListAdapterExpense()
        val recyclerView : RecyclerView = view.recyclerViewExpense
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //TODO: Add day directly on expenses

        // ExpenseViewModel
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        expenseViewModel.readAllData.observe(viewLifecycleOwner, Observer { expense ->
            adapter.setData(expense)
        })

        var lay = view.findViewById<LinearLayout>(R.id.categorylay)

        for(i in 0..5){
            val view = inflater.inflate(R.layout.expense_category, container, false)
            view.setOnClickListener {
                var dialog = AddExpenseDialog();
                dialog.show(childFragmentManager,"")
            }
            lay.addView(view)
        }

        val expandBtn2 = view.findViewById<FloatingActionButton>(R.id.expandButton2);
        val catScroll = view.findViewById<HorizontalScrollView>(R.id.catScroll);
        val catText = view.findViewById<TextView>(R.id.catText);

        expandBtn2.setOnClickListener {
            if(catScroll.visibility == View.GONE){
                catScroll.visibility = View.VISIBLE;
                catText.visibility = View.VISIBLE;
            }else{
                catScroll.visibility = View.GONE
                catText.visibility = View.GONE;
            }
        }


        //To test, not final
        /*lay.addView(inflater.inflate(R.layout.expense_card, container, false));
        lay.addView(inflater.inflate(R.layout.expense_card, container, false));
        lay.addView(inflater.inflate(R.layout.expense_card, container, false));
        lay.addView(tv)
        lay.addView(txt2)
        lay.addView(inflater.inflate(R.layout.expense_card, container, false));
        lay.addView(inflater.inflate(R.layout.expense_card, container, false));*/

        return view;
    }


}