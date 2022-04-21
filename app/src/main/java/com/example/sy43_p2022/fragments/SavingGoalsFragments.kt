package com.example.sy43_p2022.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.adapter.ButtonAdapter

class SavingGoalsFragments: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_spendings, container, false)

        //ici, on va récupérer le recyclerview pour afficher la lsite des boutons
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = ButtonAdapter(R.layout.item_vertical_white)

        //Ici, on implémente le bouton du retour
        val clickReturn = view.findViewById<ImageView>(R.id.fragment_return)
        clickReturn.setOnClickListener {
            val transaction = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view

        //QUESTION A POSER AU PROF / JULIEN
        //Ne fonctionne pas. Je pense que le recyclerview créé plein de sub_category_button avec des noms différents.
        val clickSub = view.findViewById<Button>(R.id.sub_category_button)
        clickSub.setOnClickListener {
            val transaction = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, EditFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}