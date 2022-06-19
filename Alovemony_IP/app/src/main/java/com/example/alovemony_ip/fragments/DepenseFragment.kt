package com.example.alovemony_ip.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alovemony_ip.popup.AddCategoriePopup
import com.example.alovemony_ip.repository.DepenseRepository.Singleton.depenseList
import com.example.alovemony_ip.MainActivity
import com.example.alovemony_ip.R
import com.example.alovemony_ip.adapter.*


class DepenseFragment(
    val context: MainActivity
) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_depense, container, false)

        //recuperer le recyclerview verticale aux liste catégorie et dépenses

        val categorieRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_categorie_list)
        categorieRecyclerView.adapter = CategorieAdapter(context, R.layout.item_categorie_list)
        categorieRecyclerView.layoutManager = LinearLayoutManager(context)
        categorieRecyclerView.addItemDecoration(ProjetItemDecoration())

        val depenseRecyclerView = view.findViewById<RecyclerView>(R.id.depense_recycler_list)
        depenseRecyclerView.adapter = DepenseAdapter(context, depenseList, R.layout.item_verticale_depense)
        depenseRecyclerView.layoutManager = LinearLayoutManager(context)
        depenseRecyclerView.addItemDecoration(ProjetItemDecoration())

        // on assigne le bouton add, permettant d'ajouter une catégorie
        val bouton_categorie_add = view.findViewById<Button>(R.id.bouton_ajout_categorie)

        bouton_categorie_add.setOnClickListener {
            AddCategoriePopup(context).show()
        }

    // on assigne le bouton add, permettant d'afficher la liste des dépenses en grand
        val bouton_depense_list = view.findViewById<Button>(R.id.bouton_depense_list)

        bouton_depense_list.setOnClickListener {
                context.loadFragment(DepenseListFragment(context),R.string.page_liste_depense_tire)
        }


        return view

    }

}