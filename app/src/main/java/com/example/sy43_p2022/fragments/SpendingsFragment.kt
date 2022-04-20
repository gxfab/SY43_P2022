package com.example.sy43_p2022.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.adapter.ButtonAdapter

class SpendingsFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_spendings, container, false)

        //ici, on va récupérer le recyclerview pour afficher la lsite des boutons
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = ButtonAdapter()
        return view
    }
}