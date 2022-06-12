package com.example.noappnogain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.model.Category
import com.example.noappnogain.R

class CategoryAdapter(private val categoryList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.categorie_recycler_view,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = categoryList[position]

        holder.type.text = currentitem.nom

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val type: TextView = itemView.findViewById(R.id.type_txt_category)


    }

}