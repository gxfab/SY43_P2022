package com.example.gestimali.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestimali.R
import com.example.gestimali.wish.Wish

class WishAdapter: RecyclerView.Adapter<WishAdapter.ViewHolder>() {

    private var wishList = emptyList<Wish>()

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val wishName = itemView.findViewById<TextView>(R.id.wish_name)
        val wishAmount = itemView.findViewById<TextView>(R.id.wish_amount)
        val wishAmountNeeded = itemView.findViewById<TextView>(R.id.wish_amount_needed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_wish,parent,false))
    }

    override fun onBindViewHolder(holder: WishAdapter.ViewHolder, position: Int) {
        val currentItem = wishList[position]
        holder.itemView.findViewById<TextView>(R.id.wish_name).text = currentItem.wis_name.toString()
        holder.itemView.findViewById<TextView>(R.id.wish_amount).text = currentItem.wis_amount_collected.toString()
        holder.itemView.findViewById<TextView>(R.id.wish_amount_needed).text = currentItem.wis_amount_needed.toString()
    }

    override fun getItemCount(): Int {
        return wishList.size
    }

}

