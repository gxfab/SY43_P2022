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
import com.example.alovemony_ip.model.ProjetModel
import com.example.alovemony_ip.popup.ProjetPopup
import com.example.alovemony_ip.repository.ProjetRepository

class ProjetAdapter(
    val context: MainActivity,
    private val projetList: List<ProjetModel>,
    val layoutId : Int,
    val type : String,
    val final : Boolean
    ) : RecyclerView.Adapter<ProjetAdapter.ViewHolder>() {


    // boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val projetImage = view.findViewById<ImageView>(R.id.image_item)
        val projetName:TextView? = view.findViewById(R.id.name_item)
        val projetDescription:TextView? = view.findViewById(R.id.description_item)
        val projetMontant:TextView? = view.findViewById(R.id.montant_item)
        val progressBar:ProgressBar? = view.findViewById(R.id.progressBarProjet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations du projet
        val currentProjet = projetList[position]


        //utiliser glide pour recuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentProjet.imageUrl)).into(holder.projetImage)

        // mettre à jour le nom du projet
        holder.projetName?.text = currentProjet.name

        // metre à jour la description du projet
        holder.projetDescription?.text = currentProjet.description

        // mettre à jour le montant du projet
        holder.projetMontant?.text = currentProjet.montantGlobal.toString()

        holder.progressBar?.progress = currentProjet.montant

        //interaction lors du clique sur un projet
       holder.itemView.setOnClickListener {
            //afficher la popup
            ProjetPopup(this, currentProjet).show()
        }
        holder.itemView


    }
    override fun getItemCount(): Int = projetList.size
}