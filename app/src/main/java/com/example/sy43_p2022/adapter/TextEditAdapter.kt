package com.example.sy43_p2022.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.database.PiggyBankDatabase
import com.example.sy43_p2022.database.entities.SubCategory

class TextEditAdapter(
    private val layoutId: Int,
    private val subCategories: List<SubCategory>)
    : RecyclerView.Adapter<TextEditAdapter.TextEditViewHolder>() {

    private lateinit var db: PiggyBankDatabase

    inner class TextEditViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var category: SubCategory
        var title: TextView = view.findViewById<TextView>(R.id.subcategory_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextEditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)



        return TextEditViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextEditViewHolder, position: Int) {
        subCategories[position].let {
            holder.category = it
            holder.title.text = it.name
        }
    }

    override fun getItemCount(): Int = subCategories.size
}
