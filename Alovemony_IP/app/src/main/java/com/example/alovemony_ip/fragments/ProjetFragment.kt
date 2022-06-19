package com.example.alovemony_ip.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.alovemony_ip.FinalActivity
import com.example.alovemony_ip.MainActivity
import com.example.alovemony_ip.repository.ProjetRepository.Singleton.projetList
import com.example.alovemony_ip.R
import com.example.alovemony_ip.adapter.DetteAdapter
import com.example.alovemony_ip.adapter.ProjetAdapter
import com.example.alovemony_ip.adapter.ProjetGlobalAdapter
import com.example.alovemony_ip.adapter.ProjetItemDecoration
import com.example.alovemony_ip.repository.DetteRepository.Singleton.detteList
import com.example.alovemony_ip.repository.ProjetGlobalRepository.Singleton.projetGlobalList

class ProjetFragment(
    private val context: MainActivity
) : Fragment(){

    // appele onCreateView lors de la création du HomeFragment qui injecte fragment_home sur la page //
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_projet, container, false)

        //recuperer le recyclerview horizontal des trois listes différentes
        val projetMoisRecyclerView = view.findViewById<RecyclerView>(R.id.projet_mois_recycler_view)
        projetMoisRecyclerView.adapter = ProjetAdapter(context, projetList, R.layout.item_horizontal_projet,"Projet du mois",false)


        val projetGlobalRecyclerView = view.findViewById<RecyclerView>(R.id.projet_globaux_recycler_view)
        projetGlobalRecyclerView.adapter = ProjetGlobalAdapter(context,projetGlobalList, R.layout.item_horizontal_projet,"Projet global",false)
        projetGlobalRecyclerView.addItemDecoration(ProjetItemDecoration())

        val detteRecyclerView = view.findViewById<RecyclerView>(R.id.dette_recycler_view)
        detteRecyclerView.adapter = DetteAdapter(context,detteList,R.layout.item_horizontal_projet,"Dette",false)
        projetGlobalRecyclerView.addItemDecoration(ProjetItemDecoration())

        // on assigne le bouton add, permettant d'ajouter un projet
        val addButton = view.findViewById<Button>(R.id.add_projet)
        addButton.setOnClickListener {
            context.loadFragment(AddProjectFragment(context),R.string.page_projet_titre)
        }

        return view
    }
}