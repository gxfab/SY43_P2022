package com.example.alovemony_ip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alovemony_ip.popup.CategorieModifMontant
import com.example.alovemony_ip.MainActivity
import com.example.alovemony_ip.R
import com.example.alovemony_ip.repository.CategorieRepository
import com.example.alovemony_ip.repository.CategorieRepository.Singleton.categorieList

class CategorieAdapter(
    val context: MainActivity,
    val layoutId : Int
    ) : RecyclerView.Adapter<CategorieAdapter.ViewHolder>() {


    // boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
       val nameCategorie : TextView?  = view.findViewById(R.id.name_categorie)
       val montantCategorie : TextView? = view.findViewById(R.id.montant_categorie)
       val montantPrevCategorie : TextView? = view.findViewById(R.id.montant_prev_categorie)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations de la categorie

        val currentCategorie = categorieList[position]

    //on récupère les textes des  champs de saisies du layout, qu'on assigne à la catégorie correspondante
       holder.montantCategorie?.text = currentCategorie.montant.toString()
        holder.nameCategorie?.text = currentCategorie.name
        holder.montantPrevCategorie?.text = currentCategorie.montantPrev.toString()

        //interaction lors du clique sur une catégorie
        holder.itemView.setOnClickListener {
            //afficher la popup
            CategorieModifMontant(context,currentCategorie)

        }

    }

    override fun getItemCount(): Int = categorieList.size

}