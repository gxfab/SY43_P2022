package com.example.sy43_p2022.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.database.entity.Category
import com.example.sy43_p2022.fragments.EditFragment

class ButtonAdapter(private val layoutId: Int, private val categories: Category) : RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {

    //On range ici tous les composants à controller
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById<TextView>(R.id.subcategory_title)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context).
                inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        categories.getSubCategories(position).let {
            holder.title.text = it.getName()
            // holder.itemView.setOnClickListener {
            //    EditFragment.subcategory = it
            // }
        }
    }

    override fun getItemCount(): Int = 13
}