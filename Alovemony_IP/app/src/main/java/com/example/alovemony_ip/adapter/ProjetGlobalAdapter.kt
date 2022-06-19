package com.example.alovemony_ip.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alovemony_ip.*
import com.example.alovemony_ip.model.ProjetGlobalModel
import com.example.alovemony_ip.repository.ProjetGlobalPopup
import com.example.alovemony_ip.repository.ProjetGlobalRepository

class ProjetGlobalAdapter(
    val context: MainActivity,
    private val projetGlobalList: List<ProjetGlobalModel>,
    val layoutId : Int,
    val type : String,
    val final : Boolean
) : RecyclerView.Adapter<ProjetGlobalAdapter.ViewHolder>() {

    // boit pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val projetGlobalImage = view.findViewById<ImageView>(R.id.image_item)
        val projetGlobalName: TextView? = view.findViewById(R.id.name_item)
        val projetGlobalDescription: TextView? = view.findViewById(R.id.description_item)
        val projetGlobalMontant: TextView? = view.findViewById(R.id.montant_item)
        val progressBar: ProgressBar? = view.findViewById(R.id.progressBarProjet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations du projet global
        val currentProjetGlobal = projetGlobalList[position]


        //utiliser glide pour recuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentProjetGlobal.imageUrl)).into(holder.projetGlobalImage)

        // mettre à jour le nom du projet global
        holder.projetGlobalName?.text = currentProjetGlobal.name
        // metre à jour la description du projet global
        holder.projetGlobalDescription?.text = currentProjetGlobal.description

        // mettre à jour le montant du projet global
        holder.projetGlobalMontant?.text = currentProjetGlobal.montantGlobal.toString()
        //interaction lors du clique sur un projet global
        holder.progressBar?.progress = currentProjetGlobal.montant
        holder.itemView.setOnClickListener {
            //afficher la popup
            ProjetGlobalPopup(this, currentProjetGlobal).show()
        }
    }

    override fun getItemCount(): Int = projetGlobalList.size
}