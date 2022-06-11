package com.example.noappnogain.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.R
import com.example.noappnogain.model.Data

class ProjetAdapter(private val dataList: ArrayList<Data>) :
    RecyclerView.Adapter<ProjetAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.projet_recycler_view,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = dataList[position]

        holder.dateLimite.text = currentitem.note
        holder.type.text = currentitem.type
        holder.amount.text = currentitem.amount.toString()

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val type: TextView = itemView.findViewById(R.id.nom_projet)
        val amount: TextView = itemView.findViewById(R.id.montant_projet)
        val dateLimite: TextView = itemView.findViewById(R.id.date_limite_projet)

        init {
            itemView.setOnClickListener {

                var position: Int = adapterPosition
                val context = itemView.context

                // Modifier_projet.xml
            }
        }

    }

}