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
import com.example.bokudarjan.envelope.Envelope
import com.example.bokudarjan.envelope.EnvelopeViewModel
import com.example.bokudarjan.expense.Expense
import kotlinx.android.synthetic.main.fragment_add_envelope.*
import kotlinx.android.synthetic.main.fragment_add_expense.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addEnvelopeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddEnvelopeFragment : Fragment() {


    private lateinit var envelopeViewModel: EnvelopeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_envelope, container, false)


        envelopeViewModel = ViewModelProvider(this)[EnvelopeViewModel::class.java]

        view.findViewById<Button>(R.id.addEnvelopeButton).setOnClickListener(){
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val name  : String = addEnvelopeName.text.toString()
        var amount : Float = 0F

        Log.i("AddEnvelopeFragment", " Amountempty : " +  addEnvelopeAmount.text.toString().isEmpty())
        if (! addEnvelopeAmount.text.toString().isEmpty()){
             amount = addEnvelopeAmount.text.toString().toFloat()
            Log.i("AddEnvelopeFragment", " Amount2 : " + amount)
        }
        Log.i("AddEnvelopeFragment", " Amount2.4 : " + amount)
        val date : String = datePickerEnvelope.toString()
        Log.i("AddEnvelopeFragment", " Amount2.5 : " + amount)
        val envelope = Envelope("category",name, amount, date)


        Log.i("AddEnvelopeFragment", " Amount3 : " + envelope.amount)


        //If the input is ok, we add the envelope to the database
        if(isInputValid(envelope)){
            Log.i("AddEnvelopeFragment", " Amount3 : " + envelope.amount)
            envelopeViewModel.addEnvelope(envelope)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            Log.i("AddEnvelopeFragment", "Envelope successfully added")
        }else{
            Toast.makeText(requireContext(), "Error while adding envelope to database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isInputValid(envelope : Envelope) : Boolean{
        return !envelope.name.isEmpty() && envelope.amount != null
    }

}