package com.example.sy43_p2022.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.database.entity.Category

class ButtonAdapter(private val layoutId: Int, private val category: Category, private val onClickListener: OnClickListener)
    : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    class OnClickListener(private val recyclerView: RecyclerView, private val layoutType: String) {
        fun onClick(category: Category) {
            var id: Int = if (layoutType == "white") R.layout.item_sub_white else R.layout.item_sub_gray
            recyclerView.adapter = TextEditAdapter(id, category)
        }
    }

    inner class ButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var category: Category
        var title: TextView = view.findViewById<TextView>(R.id.subcategory_title)
        var amount: TextView = view.findViewById<TextView>(R.id.subcategory_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        category.getSubCategory(position).let {
            holder.category = it
            holder.title.text = it.getName()
            holder.amount.text = it.getGlobalObjectiveAmount().toString()
            holder.itemView.setOnClickListener() {
                onClickListener.onClick(holder.category)
            }
        }

    }

    override fun getItemCount(): Int = category.getAllSubCategories().size

}