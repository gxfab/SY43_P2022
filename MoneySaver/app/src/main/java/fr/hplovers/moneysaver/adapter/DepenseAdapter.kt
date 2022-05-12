package fr.hplovers.moneysaver.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import fr.hplovers.moneysaver.R

class DepenseAdapter(private val layoutId: Int) : RecyclerView.Adapter<DepenseAdapter.ViewHolder>() {

    // On range tout les composants qu'on veut controler
    class ViewHolder(view: View ) : RecyclerView.ViewHolder(view){
        val depenseImage = view.findViewById<ImageView>(R.id.depense_category_image)
    }

    //Injecter notre layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    //On indique le nombre d'item à afficher dynamiquement (Ici 5)
    override fun getItemCount(): Int = 5
}