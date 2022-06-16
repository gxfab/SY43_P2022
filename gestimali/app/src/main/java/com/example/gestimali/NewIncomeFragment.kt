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
import com.example.gestimali.income.Income
import com.example.gestimali.income.IncomeViewModel

class NewIncomeFragment : Fragment() {

    private lateinit var mIncomeViewModel : IncomeViewModel



    companion object{
        private val ARGUMENT_MONTH_ID = "ARGUMENT_MONTH_ID"
        @JvmStatic
        fun newInstance(monthID: Int): NewIncomeFragment? {
            val args = Bundle()
            // Save data here
            args.putInt(ARGUMENT_MONTH_ID, monthID)
            val fragment = NewIncomeFragment()
            fragment.setArguments(args)
            return fragment
        }

    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View?{
        val view = inflater.inflate(R.layout.fragment_new_income,container,false)

        mIncomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)

        val monthId = requireArguments().getInt(ARGUMENT_MONTH_ID)

        view.findViewById<ImageButton>(R.id.add_btn).setOnClickListener{
            val incomeName = view.findViewById<EditText>(R.id.editIncomeName).text.toString()
            val incomeValue = view.findViewById<EditText>(R.id.editIncomeValue).text
            val incomeDay = view.findViewById<EditText>(R.id.editIncomeDay).text
            val incomeIsFixed = view.findViewById<Switch>(R.id.monthly_fixed_switch).isChecked

            if(inputCheck(incomeName,incomeValue,incomeDay)){
                val income = Income(
                    0,
                    2022,
                    monthId,
                    incomeName,
                    incomeValue.toString().toFloat(),
                    0f,
                    incomeDay.toString().toInt(),
                    incomeIsFixed
                )

                //Toast.makeText(requireContext(),"Inside if",Toast.LENGTH_LONG).show()

                mIncomeViewModel.addIncome(income)

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
            val income = Income(
                0,
                2022,
                1,
                incomeName,
                incomeValue.toString().toFloat(),
                0f,
                incomeDay.toString().toInt(),
                incomeIsFixed
            )

            mIncomeViewModel.addIncome(income)

            activity?.supportFragmentManager?.popBackStack("addFragment",0)
            //activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun inputCheck(incomeName : String, incomeValue : Editable, incomeDay : Editable): Boolean {
        return !(TextUtils.isEmpty(incomeName) && incomeValue.isEmpty() && incomeDay.isEmpty())
    }
}