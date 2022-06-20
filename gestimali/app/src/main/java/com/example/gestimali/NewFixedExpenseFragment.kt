package com.example.gestimali

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gestimali.fixexpense.FixedExpense
import com.example.gestimali.fixexpense.FixedExpenseViewModel
import com.example.gestimali.income.Income
import com.example.gestimali.income.IncomeViewModel

class NewFixedExpenseFragment : Fragment() {

    private lateinit var mFixedExpenseViewModel : FixedExpenseViewModel



    companion object{
        private val ARGUMENT_MONTH_ID = "ARGUMENT_MONTH_ID"
        @JvmStatic
        fun newInstance(monthID: Int): NewFixedExpenseFragment? {
            val args = Bundle()
            // Save data here
            args.putInt(ARGUMENT_MONTH_ID, monthID)
            val fragment = NewFixedExpenseFragment()
            fragment.setArguments(args)
            return fragment
        }

    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View?{
        val view = inflater.inflate(R.layout.fragment_new_fixed_expense,container,false)

        mFixedExpenseViewModel = ViewModelProvider(this).get(FixedExpenseViewModel::class.java)

        val monthId = requireArguments().getInt(ARGUMENT_MONTH_ID)

        view.findViewById<ImageButton>(R.id.add_btn).setOnClickListener{
            val name = view.findViewById<EditText>(R.id.editExpenseName).text.toString()
            val value = view.findViewById<EditText>(R.id.editExpenseValue).text
            val day = view.findViewById<EditText>(R.id.editExpenseDate).text
            val isFixed = view.findViewById<Switch>(R.id.monthly_fixed_switch).isChecked

            if(inputCheck(name,value,day)){
                val expense = FixedExpense(
                    monthId,
                    2022,
                    monthId,
                    name,
                    value.toString().toFloat(),
                    0f,
                    day.toString().toInt(),
                    isFixed
                )

                //Toast.makeText(requireContext(),"Inside if",Toast.LENGTH_LONG).show()

                mFixedExpenseViewModel.addFixedExpense(expense)

                activity?.onBackPressed()
            }
        }

        return view
    }

    private fun insertDataToDatabase(view: View) {
        val incomeName = view.findViewById<EditText>(R.id.editIncomeName).text.toString()
        val incomeValue = view.findViewById<EditText>(R.id.editIncomeValue).text
        val incomeDay = view.findViewById<EditText>(R.id.editIncomeDay).text
        val incomeIsFixed = view.findViewById<Switch>(R.id.monthly_fixed_switch).isChecked

        if(inputCheck(incomeName,incomeValue,incomeDay)){
            val expense = FixedExpense(
                0,
                2022,
                1,
                incomeName,
                incomeValue.toString().toFloat(),
                0f,
                incomeDay.toString().toInt(),
                incomeIsFixed
            )

            mFixedExpenseViewModel.addFixedExpense(expense)

            activity?.supportFragmentManager?.popBackStack("addFragment",0)
            //activity?.supportFragmentManager?.popBackStack()
        }
    }

    /**
     * check if all the necessary input (ie. string, integer,...) are correctly fill
     *
     * @param name: name that the user fill in the text box
     * @param value: amount that the user fill in the text box
     *
     * @return a boolean, true if the inputs are valid, false if not
     */
    private fun inputCheck(name : String, value : Editable, day : Editable): Boolean {
        return !(TextUtils.isEmpty(name) && value.isEmpty() && day.isEmpty())
    }
}