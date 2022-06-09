package com.example.noappnogain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.noappnogain.Model.MouvementFinancier
import com.example.noappnogain.R

class MouvementFinancierAdapter(
    private val mouvementList: List<MouvementFinancier>,
): RecyclerView.Adapter<MouvementFinancierAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //REMPLACER ICI PAR LES BONS ID
        val mouvementDescr:TextView? = view.findViewById(R.id.description)
        val mouvementMontant:TextView? = view.findViewById(R.id.montant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //REMPLACER ICI PAR LE BON LAYOUT
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical_depenses, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //récupérer les infos du mouvement financier
        val currentMouvementFinancier = mouvementList[position]

        //Mise à jour des données du mouvement
        holder.mouvementDescr?.text = currentMouvementFinancier.description
        holder.mouvementMontant?.text = currentMouvementFinancier.montant.toString()
    }

    override fun getItemCount(): Int = mouvementList.size
}