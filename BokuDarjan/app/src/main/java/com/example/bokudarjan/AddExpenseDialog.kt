package com.example.bokudarjan

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.example.bokudarjan.expense.Expense
import com.example.bokudarjan.expense.ExpenseViewModel

class AddExpenseDialog(cat:String) : DialogFragment() {

    private lateinit var expenseViewModel: ExpenseViewModel
    var category : String = cat

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction

            val builder = AlertDialog.Builder(it)
            expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]
            builder.setView(R.layout.dialog_add_expense)
            builder.setMessage("Ajouter une dépense à $category")
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        insertDataToDatabase();
                    })
                .setNegativeButton("Annuler",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it

            val out = builder.create()
            out;
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onStart() {
        val res = super.onStart()
        val addExpenseDay = this.dialog!!.findViewById<NumberPicker>(R.id.addExpenseDay);
        addExpenseDay.minValue = 1;
        addExpenseDay.maxValue = 31;
        (dialog as AlertDialog)!!.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#4CAF50"))
        (dialog as AlertDialog)!!.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("red"))
    }


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun insertDataToDatabase() {


        val addExpenseName = this.dialog!!.findViewById<EditText>(R.id.addExpenseName);
        val addExpenseAmount = this.dialog!!.findViewById<EditText>(R.id.addExpenseAmount);
        val addExpenseDay = this.dialog!!.findViewById<NumberPicker>(R.id.addExpenseDay);
        val toggleExpenseIncome = this.dialog!!.findViewById<Switch>(R.id.toggleExpenseIncome);

        val pref = requireContext().getSharedPreferences("pref" , Context.MODE_PRIVATE)
        val month = pref.getInt("month", -1)

        val name: String = addExpenseName?.text.toString()
        Log.d("[EXPENSE]", name)

        var amount: Float = 0F
        if (addExpenseAmount?.text.toString().isNotEmpty()) {
            amount = addExpenseAmount?.text.toString().toFloat()
        }
        val date = addExpenseDay?.value
        val moneyIncoming: Boolean? = toggleExpenseIncome?.isChecked

        val expense = Expense(category, name, amount, date.toString(),month, moneyIncoming!!)


        //If the input is ok, we add the expense to the database
        if (isInputValid(expense)) {
            expenseViewModel.addExpense(expense)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            Log.i("AddExpenseFragment", "Expense successfully added")
        } else {
            Toast.makeText(
                requireContext(),
                "Error while adding expense to database",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun isInputValid(expense : Expense) : Boolean{

        return expense.name.isNotEmpty() && expense.categoryName.isNotEmpty() && expense.amount != 0f
    }

}