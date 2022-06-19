package com.example.testbare.fragment.depense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.testbare.R
import com.example.testbare.database.entities.Categorie

class FiltreView(val listCategories : ArrayList<Categorie>, val listener: OnItemCheckListener)
    : RecyclerView.Adapter<FiltreView.ViewHolder>() {

    interface OnItemCheckListener : View.OnClickListener{
        fun onItemCheck(item : String)
        fun onItemUncheck(item: String)
        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }

    var onItemCheckListener : OnItemCheckListener = listener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ma_ligne = LayoutInflater.from(parent.context).inflate(R.layout.item_filtre,parent,false)
        return ViewHolder(ma_ligne)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCategories[position], onItemCheckListener)
    }

    override fun getItemCount(): Int = listCategories.size


    class ViewHolder(val item: View) : RecyclerView.ViewHolder (item) {
        fun bind(categorie : Categorie, listener : OnItemCheckListener) = with(itemView){
            itemView.findViewById<CheckBox>(R.id.itemFiltre_cb_categorie).text = categorie.cat_categorie
            itemView.findViewById<CheckBox>(R.id.itemFiltre_cb_categorie).setOnClickListener {
                if (itemView.findViewById<CheckBox>(R.id.itemFiltre_cb_categorie).isChecked)
                    listener.onItemCheck(categorie.cat_categorie)
                else
                    listener.onItemUncheck(categorie.cat_categorie)
            }
            itemView.setOnClickListener(listener)
        }
    }
}