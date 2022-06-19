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
import com.example.alovemony_ip.model.DetteModel
import com.example.alovemony_ip.popup.DettePopup
import com.example.alovemony_ip.repository.DetteRepository

class DetteAdapter(
    val context: MainActivity,
    private val detteList: List<DetteModel>,
    val layoutId : Int,
    val type : String,
    val final : Boolean
) : RecyclerView.Adapter<DetteAdapter.ViewHolder>() {

    // boit pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val detteImage = view.findViewById<ImageView>(R.id.image_item)
        val detteName: TextView? = view.findViewById(R.id.name_item)
        val detteDescription: TextView? = view.findViewById(R.id.description_item)
        val detteMontant: TextView? = view.findViewById(R.id.montant_item)
        val progressBar: ProgressBar? = view.findViewById(R.id.progressBarProjet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations de la dette
        val currentDette = detteList[position]


        //utiliser glide pour recuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentDette.imageUrl)).into(holder.detteImage)

        // mettre à jour le nom de la dette
        holder.detteName?.text = currentDette.name
        // metre à jour la description de la dette
        holder.detteDescription?.text = currentDette.description

        // mettre à jour le montant de la dette
        holder.detteMontant?.text = currentDette.montantGlobal.toString()
        //interaction lors du clique sur une dette
        holder.progressBar?.progress = currentDette.montant
        holder.itemView.setOnClickListener {
            //afficher la popup
            DettePopup(this, currentDette).show()
        }
    }

    override fun getItemCount(): Int = detteList.size
}