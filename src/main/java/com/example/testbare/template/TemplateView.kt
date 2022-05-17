package com.example.testbare.budget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.testbare.R
import com.example.testbare.database.entities.Depense

class TemplateView(val listeDepenes: Array<Depense>, val listener: (String) -> Unit)
    : RecyclerView.Adapter<TemplateView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val ma_ligne = LayoutInflater.from(parent.context).inflate(R.layout.item,parent, false)
        return ViewHolder(ma_ligne)
    }

    override fun getItemCount(): Int = listeDepenes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listeDepenes[position],listener)
    }

    class ViewHolder(elementDeListe : View) : RecyclerView.ViewHolder(elementDeListe)
    {
        fun bind(depense: Depense, listener: (String) -> Unit) = with(itemView)
        {
            itemView.findViewById<TextView>(R.id.frgmItem_tv_categorie).text = depense.getDepenseCategorie()
            itemView.findViewById<TextView>(R.id.frgmItem_tv_montant).text = depense.getDepenseMontant().toString() + " €"

            setOnClickListener { listener(depense.getDepenseCategorie()) }
        }
    }



 }