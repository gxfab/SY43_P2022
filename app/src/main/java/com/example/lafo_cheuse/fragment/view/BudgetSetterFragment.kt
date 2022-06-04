package com.example.lafo_cheuse.fragment.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.lafo_cheuse.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BudgetSetterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())

        exitTransition = inflater.inflateTransition(R.transition.fade_out)
        enterTransition = inflater.inflateTransition(R.transition.fade_in)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val budgetSetterView : View = inflater.inflate(R.layout.fragment_budget_setter, container, false)

        val deleteFragment = { dialog: DialogInterface, which: Int ->
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            Toast.makeText(activity,
                "Suppression réussie", Toast.LENGTH_SHORT).show()
        }

        val cancel = { dialog: DialogInterface, which: Int ->
            Toast.makeText(activity,
                "Annulation de la suppression", Toast.LENGTH_LONG).show()
        }

        val deleteButton : View = budgetSetterView.findViewById<FloatingActionButton>(R.id.deleteButton)

        deleteButton.setOnClickListener {
            val alertDialog : AlertDialog.Builder = AlertDialog.Builder(activity)

            with(alertDialog)
            {
                alertDialog.setTitle("Suppression définitive")
                alertDialog.setMessage("Êtes-vous sûr de vouloir supprimer cet élément ?")
                alertDialog.setCancelable(true)
                setPositiveButton("Oui", DialogInterface.OnClickListener(function = deleteFragment))
                setNeutralButton("Annuler",DialogInterface.OnClickListener(function = cancel))
                show()
            }
            //activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()

        }

        return budgetSetterView
    }

}