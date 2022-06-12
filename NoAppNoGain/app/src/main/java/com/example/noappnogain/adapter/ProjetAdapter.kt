package com.example.noappnogain.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.model.Projet
import com.example.noappnogain.R
import java.text.SimpleDateFormat
import java.util.*

class ProjetAdapter(private val projetList: ArrayList<Projet>) :
    RecyclerView.Adapter<ProjetAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.projet_recycler_view,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = projetList[position]

        val SDFormat: SimpleDateFormat = SimpleDateFormat("d/M/yyyy")
        val mDate = SDFormat.format(Date())
        val todayDate = SDFormat.parse(mDate)
        val projetDate = SDFormat.parse(currentitem.date)

        val compare = todayDate.compareTo(projetDate)
        when{
            compare < 0 -> {
                holder.datePicker.setTextColor(Color.parseColor("#0dff00"));
            }
            compare < 0 -> {
                holder.datePicker.setTextColor(Color.parseColor("#ff0000"));
            }
            else -> {
                holder.datePicker.setTextColor(Color.parseColor("#ff0000"));
            }

        }

        holder.datePicker.text = currentitem.date
        holder.name.text = currentitem.name
        holder.totalAmount.text = currentitem.totalAmount.toString()
        holder.actualAmount.text = currentitem.actualAmount.toString()

    }

    override fun getItemCount(): Int {
        return projetList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.nom_projet)
        val totalAmount: TextView = itemView.findViewById(R.id.totalMontant_projet)
        val actualAmount: TextView = itemView.findViewById(R.id.actualMontant_projet)
        val datePicker: TextView = itemView.findViewById(R.id.date_limite_projet)

        init {
            itemView.setOnClickListener {

                var position: Int = adapterPosition
                val context = itemView.context

                // Modifier_projet.xml
            }
        }

    }

}