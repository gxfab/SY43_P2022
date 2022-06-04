package com.example.bokudarjan

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.fragment.app.setFragmentResult

class AddExpenseDialog() : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction

            val builder = AlertDialog.Builder(it)
            builder.setView(R.layout.fragment_add_expense)
            builder.setMessage("Ajouter une dépense ?")
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        var bd = Bundle();
                        bd.putString("string","CECI EST UN TEST")
                        setFragmentResult("dialog",bd);
                    })
                .setNegativeButton("Annuler",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}