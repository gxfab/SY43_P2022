package com.example.testbare

import android.R
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import java.util.*


class MonthYearPickerDialog : DialogFragment() {
    internal lateinit var listener: MonthListener

    interface MonthListener {
        fun onOkDialogMonthClick(moisChoisi : Int, anneeChoisi: Int)
    }

    fun setListener(fragmentListener : MonthListener){
        this.listener = fragmentListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(getActivity())
        val inflater: LayoutInflater = requireActivity().getLayoutInflater()

        val cal: Calendar = Calendar.getInstance()
        val months = arrayOf<String>("Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Décembre")
        val dialog: View = inflater.inflate(com.example.testbare.R.layout.month_picker, null)
        val monthPicker = dialog.findViewById(com.example.testbare.R.id.picker_month) as NumberPicker
        val yearPicker = dialog.findViewById(com.example.testbare.R.id.picker_year) as NumberPicker
        monthPicker.minValue = 0
        monthPicker.maxValue = 11
        monthPicker.value = cal.get(Calendar.MONTH)
        monthPicker.displayedValues = months
        val year: Int = cal.get(Calendar.YEAR)
        yearPicker.minValue = 2000
        yearPicker.maxValue = year
        yearPicker.value = year
        yearPicker.wrapSelectorWheel = false
        builder.setView(dialog)
            .setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                    listener.onOkDialogMonthClick(monthPicker.value, yearPicker.value)
                })
            .setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    this@MonthYearPickerDialog.requireDialog().cancel()
                })
        return builder.create()
    }
}