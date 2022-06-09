package com.example.noappnogain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.MainActivity
import com.example.noappnogain.Model.MouvementFinancier
import com.example.noappnogain.R

class MouvementFinancierAdapter (
    private val context: MainActivity,
    private val mouvementList: List<MouvementFinancier>,
    private val layoutId: Int
): RecyclerView.Adapter<MouvementFinancierAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val mouvementDate:TextView? = view.findViewById(R.id.date)
        val mouvementDescr:TextView? = view.findViewById(R.id.description)
        val mouvementMontant:TextView? = view.findViewById(R.id.montant)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_financier, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //récupérer les infos du mouvement financier
        val currentMouvementFinancier = mouvementList[position]

        //Mise à jour des données du mouvement
        holder.mouvementDate?.text = currentMouvementFinancier.date
        holder.mouvementDescr?.text = currentMouvementFinancier.description
        holder.mouvementMontant?.text = currentMouvementFinancier.montant.toString()
    }

    override fun getItemCount(): Int = mouvementList.size
}