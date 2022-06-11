package com.example.bokudarjan.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.R
import kotlinx.android.synthetic.main.envelope_category_card.view.*


class ListAdapterCategory : RecyclerView.Adapter<ListAdapterCategory.MyViewHolder>() {
    private var categoryList = emptyList<Category>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.envelope_category_card, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.itemView.nameCategory.text = currentItem.categoryName
    }


    override fun getItemCount(): Int {
        return categoryList.size
    }


    fun setData(category: List<Category>){
        this.categoryList = category
        notifyDataSetChanged()
    }



}