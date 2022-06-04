package com.example.bokudarjan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.bokudarjan.expense.Expense
import com.example.bokudarjan.expense.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_add_expense.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addExpenseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddExpenseFragment : Fragment() {


    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_expense, container, false)


        expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]

        view.findViewById<Button>(R.id.addExpenseButton).setOnClickListener(){
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val name : String = addExpenseName.text.toString()

        var amount : Float = 0F
        if (! addExpenseAmount.text.toString().isEmpty()){
             amount  = addExpenseAmount.text.toString().toFloat()
        }
        val date : Int = addExpenseDay.value
        val moneyIncoming : Boolean = toggleExpenseIncome.isChecked()

        val expense = Expense("category",name, amount, date.toString(), moneyIncoming)


        //If the input is ok, we add the expense to the database
        if(isInputValid(expense)){
            expenseViewModel.addExpense(expense)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            Log.i("AddExpenseFragment", "Expense successfully added")
        }else{
            Toast.makeText(requireContext(), "Error while adding expense to database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isInputValid(expense : Expense) : Boolean{

        return !expense.name.isEmpty() && !expense.categoryName.isEmpty() && expense.amount != null
    }

}