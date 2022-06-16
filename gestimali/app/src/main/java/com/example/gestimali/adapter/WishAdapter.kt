package com.example.gestimali.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestimali.R

class WishAdapter: RecyclerView.Adapter<WishAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val wishName = itemView.findViewById<TextView>(R.id.wish_name)
        val wishAmount = itemView.findViewById<TextView>(R.id.wish_amount)
        val wishAmountNeeded = itemView.findViewById<TextView>(R.id.wish_amount_needed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WishAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}