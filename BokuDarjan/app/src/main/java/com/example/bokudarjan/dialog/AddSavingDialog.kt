package com.example.bokudarjan.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.bokudarjan.R
import com.example.bokudarjan.saving.Saving
import com.example.bokudarjan.saving.SavingViewModel

/**
 * A simple [Dialog] allowing the user to add a new saving project
 */
class AddSavingDialog() : DialogFragment()  {

    private lateinit var savingViewModel: SavingViewModel

    /**
     * Setting up the interface
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction

            val builder = AlertDialog.Builder(it)
            savingViewModel = ViewModelProvider(this)[SavingViewModel::class.java]
            builder.setView(R.layout.dialog_add_saving)
            builder.setMessage("Ajouter un projet")
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

    /**
     * Setting up the color of the button
     */
    override fun onStart() {
        super.onStart()
        (dialog as AlertDialog)!!.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#4CAF50"))
        (dialog as AlertDialog)!!.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("red"))
    }

    private fun insertDataToDatabase(){
        var name = dialog!!.findViewById<EditText>(R.id.dialogSavingName).text.toString()
        var value = dialog!!.findViewById<EditText>(R.id.DialogSavingAmount).text.toString().toFloat()

        savingViewModel.addSaving(Saving(name,value));
    }
}