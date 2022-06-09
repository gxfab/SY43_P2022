package com.example.noappnogain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.MainActivity
import com.example.noappnogain.MouvementFinancierRepo.Singleton.MouvementFinancierList
import com.example.noappnogain.R
import com.example.noappnogain.adapter.MouvementFinancierAdapter

class AccueilFragment(
    private val context : MainActivity
) : Fragment() {

    //Injecte le layout fragment_accueil
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_accueil,container,false)

        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter= MouvementFinancierAdapter(context, MouvementFinancierList, R.layout.item_financier)

        return view
    }

}