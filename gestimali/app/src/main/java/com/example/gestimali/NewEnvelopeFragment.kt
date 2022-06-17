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
import com.example.gestimali.envelope.Envelope
import com.example.gestimali.envelope.EnvelopeViewModel
import com.example.gestimali.fixexpense.FixedExpense
import com.example.gestimali.fixexpense.FixedExpenseViewModel

class NewEnvelopeFragment: Fragment() {

    private lateinit var mEnvelopeViewModel : EnvelopeViewModel



    companion object{
        private val ARGUMENT_MONTH_ID = "ARGUMENT_MONTH_ID"
        @JvmStatic
        fun newInstance(monthID: Int): NewEnvelopeFragment? {
            val args = Bundle()
            // Save data here
            args.putInt(ARGUMENT_MONTH_ID, monthID)
            val fragment = NewEnvelopeFragment()
            fragment.setArguments(args)
            return fragment
        }

    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View?{
        val view = inflater.inflate(R.layout.fragment_new_envelope,container,false)

        mEnvelopeViewModel = ViewModelProvider(this).get(EnvelopeViewModel::class.java)

        val monthId = requireArguments().getInt(ARGUMENT_MONTH_ID)

        view.findViewById<ImageButton>(R.id.add_btn).setOnClickListener{
            val name = view.findViewById<EditText>(R.id.editEnvelopeName).text.toString()
            val value = view.findViewById<EditText>(R.id.editEnvelopeValue).text

            if(inputCheck(name,value)){
                val envelope = Envelope(
                    monthId,
                    2022,
                    monthId,
                    name,
                    value.toString().toFloat(),
                    0f,
                )

                //Toast.makeText(requireContext(),"Inside if",Toast.LENGTH_LONG).show()

                mEnvelopeViewModel.addEnvelope(envelope)

                activity?.onBackPressed()
            }
        }

        return view
    }

    private fun insertDataToDatabase(view: View) {
        val incomeName = view.findViewById<EditText>(R.id.editIncomeName).text.toString()
        val incomeValue = view.findViewById<EditText>(R.id.editIncomeValue).text

        if(inputCheck(incomeName,incomeValue)){
            val envelope = Envelope(
                0,
                2022,
                1,
                incomeName,
                incomeValue.toString().toFloat(),
                0f,
            )

            mEnvelopeViewModel.addEnvelope(envelope)

            activity?.supportFragmentManager?.popBackStack("addFragment",0)
            //activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun inputCheck(name : String, value : Editable): Boolean {
        return !(TextUtils.isEmpty(name) && value.isEmpty())
    }
}