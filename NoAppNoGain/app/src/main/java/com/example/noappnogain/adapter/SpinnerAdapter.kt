package com.example.noappnogain.adapter

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment

class SpinnerAdapter : Fragment(), AdapterView.OnItemSelectedListener{
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val item = parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}