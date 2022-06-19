package com.example.testbare.fragment.budget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testbare.R
import com.example.testbare.database.entities.Revenu
import com.example.testbare.database.AppDatabase

class RevenuView(val listRevenus: ArrayList<Revenu>)
    : RecyclerView.Adapter<RevenuView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val ma_ligne = LayoutInflater.from(parent.context).inflate(R.layout.item_revenu, parent, false)
        return ViewHolder(ma_ligne)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listRevenus[position], listRevenus, this, position)
    }
    override fun getItemCount(): Int = listRevenus.size

    class ViewHolder(val item : View) : RecyclerView.ViewHolder (item) {
        fun bind(revenu : Revenu, listRevenus: ArrayList<Revenu>, viewAdapter : RevenuView, position: Int) = with(itemView){
            itemView.findViewById<TextView>(R.id.itemRevenu_tv_source).text = revenu.rev_source
            itemView.findViewById<TextView>(R.id.itemRevenu_tv_montant).text = revenu.rev_montant.toString() + " €"
            itemView.findViewById<ImageButton>(R.id.itemRevenu_btn_supprimer).setOnClickListener {
                val revenuDao = AppDatabase.getDatabase(context).RevenuDao()
                revenuDao.deleteRevenu(revenu.rev_id)
                listRevenus.remove(revenu)
                viewAdapter.notifyItemRemoved(position)
            }
        }
    }
}
