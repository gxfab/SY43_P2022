package com.example.bokudarjan

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.example.bokudarjan.envelope.Envelope
import com.example.bokudarjan.envelope.EnvelopeViewModel
import com.example.bokudarjan.expense.Expense
import com.example.bokudarjan.expense.ExpenseViewModel
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.android.synthetic.main.fragment_add_envelope.*
import kotlinx.android.synthetic.main.fragment_side_bar.*
import java.util.*

class AddEnvelopeDialog() : DialogFragment() {

    private lateinit var envelopeViewModel: EnvelopeViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction

            val builder = AlertDialog.Builder(it)
            envelopeViewModel = ViewModelProvider(this)[EnvelopeViewModel::class.java]
            builder.setView(R.layout.fragment_add_envelope)
            builder.setMessage("Plannifier une dépense")
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        insertDataToDatabase()
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
        super.onStart()
        (dialog as AlertDialog)!!.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#4CAF50"))
        (dialog as AlertDialog)!!.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("red"))
    }

    private fun insertDataToDatabase(){
        val name  : String = this.dialog!!.findViewById<EditText>(R.id.addEnvelopeName).text.toString()
        val addEnvelopeAmount = this.dialog!!.findViewById<EditText>(R.id.addEnvelopeAmount);
        var amount : Float = 0f

        Log.i("AddEnvelopeFragment", " Amountempty : " +  addEnvelopeAmount.text.toString().isEmpty())
        if (! addEnvelopeAmount.text.toString().isEmpty()){
            amount = addEnvelopeAmount.text.toString().toFloat()
            Log.i("AddEnvelopeFragment", " Amount2 : " + amount)
        }
        Log.i("AddEnvelopeFragment", " Amount2.4 : " + amount)
        val date : String = Calendar.getInstance().toString()
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
        return envelope.name.isNotEmpty()
    }


}