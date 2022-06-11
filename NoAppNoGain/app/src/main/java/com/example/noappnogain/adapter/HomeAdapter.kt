package com.example.noappnogain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.R
import com.example.noappnogain.model.Data

class HomeAdapter(private val dataList: ArrayList<Data>) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.home_recycler_view,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = dataList[position]

        holder.date.text = currentitem.date
        holder.type.text = currentitem.type
        holder.amount.text = currentitem.amount.toString()

    }

    override fun getItemCount(): Int {

        return dataList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val date: TextView = itemView.findViewById(R.id.date_txt_home)
        val type: TextView = itemView.findViewById(R.id.type_txt_home)
        val amount: TextView = itemView.findViewById(R.id.amount_txt_home)

    }

}
