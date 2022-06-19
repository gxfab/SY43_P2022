package com.example.alovemony_ip.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alovemony_ip.*
import com.example.alovemony_ip.model.DepenseModel
import com.example.alovemony_ip.popup.DepensePopup
import com.example.alovemony_ip.repository.DepenseRepository

class DepenseAdapter(
    val context: MainActivity,
    private val depenseList: List<DepenseModel>,
    val layoutId : Int
) : RecyclerView.Adapter<DepenseAdapter.ViewHolder>() {

    // boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val depenseImage = view.findViewById<ImageView>(R.id.image_item)
        val depenseName: TextView? = view.findViewById(R.id.name_item)
        val depenseCategorie : TextView? = view.findViewById(R.id.categorie_item)
        val depenseDescription: TextView? = view.findViewById(R.id.description_item)
        val depenseMontant: TextView? = view.findViewById(R.id.montant_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations de la depense
        val currentDepense = depenseList[position]

        for(i in depenseList)
        {
            System.out.println("Depense : " + i)
        }
        //utiliser glide pour recuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentDepense.imageUrl)).into(holder.depenseImage)

        // mettre à jour le nom de la depense
        holder.depenseName?.text = currentDepense.name
        holder.depenseCategorie?.text = currentDepense.categorie
        // metre à jour la description de la depense
        holder.depenseDescription?.text = currentDepense.description

        // mettre à jour le montant de la depense
        holder.depenseMontant?.text = currentDepense.montant.toString()

        //interaction lors du clique sur une depense
        holder.itemView.setOnClickListener {
            //afficher la popup
            DepensePopup(this, currentDepense).show()
        }
    }

    override fun getItemCount(): Int = depenseList.size
}