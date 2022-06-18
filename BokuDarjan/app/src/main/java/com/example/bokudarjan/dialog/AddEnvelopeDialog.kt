package com.example.bokudarjan.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.example.bokudarjan.R
import com.example.bokudarjan.category.Category
import com.example.bokudarjan.envelope.Envelope
import com.example.bokudarjan.envelope.EnvelopeViewModel
import java.util.*

/**
 * A simple [Dialog] allowing the user to add a new enveloppe
 */
class AddEnvelopeDialog(list: List<Category>) : DialogFragment() {


    private lateinit var envelopeViewModel: EnvelopeViewModel
    private var list: List<Category> = list


    /**
     * Setting up the interface
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction

            val builder = AlertDialog.Builder(it)
            envelopeViewModel = ViewModelProvider(this)[EnvelopeViewModel::class.java]
            builder.setView(R.layout.dialog_add_envelope)
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

    /**
     * Setting up the color of the button
     */
    override fun onStart() {
        super.onStart()
        (dialog as AlertDialog)!!.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#4CAF50"))
        (dialog as AlertDialog)!!.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("red"))
    }

    private fun insertDataToDatabase(){

        val pref = requireContext().getSharedPreferences("pref" , Context.MODE_PRIVATE)
        val month = pref.getInt("month", -1)

        val name  : String = this.dialog!!.findViewById<EditText>(R.id.addEnvelopeName).text.toString()
        val addEnvelopeAmount = this.dialog!!.findViewById<EditText>(R.id.addEnvelopeAmount);
        val cat = this.dialog!!.findViewById<EditText>(R.id.addEnvelopeCategory);
        var amount : Float = 0f


        if (! addEnvelopeAmount.text.toString().isEmpty()){
            amount = addEnvelopeAmount.text.toString().toFloat()
        }
        val date : String = Calendar.getInstance().toString()
        val envelope = Envelope(cat.text.toString(),name, amount, date, month)


        Log.i("AddEnvelopeFragment", " Amount3 : " + envelope.amount + " | Envelope name : " + envelope.name)


        //If the input is ok, we add the envelope to the database
        if(isInputValid(envelope)){

                for(elem in list){
                    if (elem.categoryName == cat.text.toString()){
                        envelopeViewModel.addEnvelope(envelope)
                        Toast.makeText(requireContext(), "Ajout réussi", Toast.LENGTH_SHORT).show()
                    }
                }

        }else{
            Toast.makeText(requireContext(), "Error while adding envelope to database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isInputValid(envelope : Envelope) : Boolean{
        return envelope.name.isNotEmpty()
    }


}