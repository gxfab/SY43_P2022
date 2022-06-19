package com.example.alovemony_ip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alovemony_ip.*
import com.example.alovemony_ip.connexionactivities.ConnexionCategorieActivity
import com.example.alovemony_ip.model.CategorieModel
import com.example.alovemony_ip.popup.CategorieModifMontant

class ConnexionCategorieAdapter(
    val context: ConnexionCategorieActivity,
    private val categorieList : List<CategorieModel>
): RecyclerView.Adapter<ConnexionCategorieAdapter.ViewHolder>() {

    // boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameCategorie : TextView? = view.findViewById(R.id.connexion_categorie_name)
        val montantCategorie: TextView? = view.findViewById(R.id.connexion_categorie_montant_prev)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnexionCategorieAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.connexion_categorie_montant, parent, false)

        return ConnexionCategorieAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConnexionCategorieAdapter.ViewHolder, position: Int) {

        // recuperer les informations de la categorie
        val currentCategorie = categorieList[position]

        //on récupère les textes des  champs de saisies du layout, qu'on assigne à la catégorie correspondante
        holder.nameCategorie?.text = currentCategorie.name
        holder.montantCategorie?.text = currentCategorie.montantPrev.toString()

        //interaction lors du clique sur la catégorie
        holder.itemView.setOnClickListener {
            //afficher la popup
            CategorieModifMontant(context,currentCategorie).show()
        }
    }
    override fun getItemCount(): Int = categorieList.size


}