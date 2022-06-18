package com.example.bokudarjan

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.bokudarjan.expense.ExpenseViewModel
import com.example.bokudarjan.saving.Saving
import com.example.bokudarjan.saving.SavingViewModel

/**
 * A simple [Dialog] allowing the user to add funds to a saving project
 */
class EditSavingDialog(name: String, oldValue: Float) : DialogFragment()  {

    private lateinit var savingViewModel: SavingViewModel
    private var name : String = name;
    private var oldValue : Float = oldValue;

    /**
     * Setting up the interface
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction

            val builder = AlertDialog.Builder(it)
            savingViewModel = ViewModelProvider(this)[SavingViewModel::class.java]
            builder.setView(R.layout.dialog_edit_saving)
            builder.setMessage("Mettre de côté pour " + name)
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

        var value = dialog!!.findViewById<EditText>(R.id.dialogAddAmount).text.toString().toFloat()
        savingViewModel.updateSum(oldValue + value, name)

    }
}