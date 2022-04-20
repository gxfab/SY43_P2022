package com.example.sy43_p2022.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.sy43_p2022.R

class HomeFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val button = inflater?.inflate(R.layout.fragment_home, container, false)
        //définition action bouton spending
        val clickSpendings = button.findViewById<Button>(R.id.home_fragment_button_spendings)
        clickSpendings.setOnClickListener {
            val transaction = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, SpendingsFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return button
    }
}