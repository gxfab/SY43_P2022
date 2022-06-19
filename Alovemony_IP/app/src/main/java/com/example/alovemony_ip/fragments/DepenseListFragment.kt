package com.example.alovemony_ip.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alovemony_ip.*
import com.example.alovemony_ip.repository.DepenseRepository.Singleton.depenseList
import com.example.alovemony_ip.adapter.DepenseAdapter
import com.example.alovemony_ip.adapter.ProjetItemDecoration

class DepenseListFragment(private val context: MainActivity): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_depense_list, container, false)

        //recuperer le recyclerview vertical
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.popup_depense_recycler_list)
        verticalRecyclerView.adapter = DepenseAdapter(context, depenseList, R.layout.item_verticale_depense)
        verticalRecyclerView.layoutManager = LinearLayoutManager(context)
        verticalRecyclerView.addItemDecoration(ProjetItemDecoration())

        // on assigne le bouton back pour retourner au framgent dépense
        val button_back = view.findViewById<ImageButton>(R.id.popup_depense_list_backbutton)
        button_back.setOnClickListener {
            context.loadFragment(DepenseFragment(context),R.string.page_depense_titre)
        }
        // on assigne le bouton add, permettant d'ajouter une dépense en affichant le frgment AddDepense
        val button_add = view.findViewById<Button>(R.id.popup_depense_list_addbutton)
        button_add.setOnClickListener{
            context.loadFragment(AddDepenseFragment(context),R.string.page_add_depense_tirre)
        }
        return view
    }
}