package com.example.sy43_p2022.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sy43_p2022.database.entities.Category
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.database.entities.SubCategory

class TextEditAdapter(private val layoutId: Int, private val category: Category)
    : RecyclerView.Adapter<TextEditAdapter.TextEditViewHolder>() {

    inner class TextEditViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var category: Category
        var title: TextView = view.findViewById<TextView>(R.id.subcategory_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextEditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return TextEditViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextEditViewHolder, position: Int) {
        /*
        category.getSubCategory(position).let {
            holder.category = it
            holder.title.text = it.getName()
        }
         */
    }

    override fun getItemCount(): Int = 13 // category.getAllSubCategories().size
}
