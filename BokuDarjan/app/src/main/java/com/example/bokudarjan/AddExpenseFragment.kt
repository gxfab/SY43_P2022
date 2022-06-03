package com.example.bokudarjan

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.bokudarjan.data.Expense
import com.example.bokudarjan.viewModel.ExpenseViewModel
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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var expenseViewModel: ExpenseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_expense, container, false)


        expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]

        view.findViewById<Button>(R.id.addButton).setOnClickListener(){
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val name = addName.text.toString()

        //If the input is ok, we add the expense to the database
        if(inputCheck(name)){
            val expense = Expense(name)
            expenseViewModel.addExpense(expense)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Error while adding expense to database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name : String) : Boolean{
        return TextUtils.isEmpty(name)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment addExpenseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddExpenseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}