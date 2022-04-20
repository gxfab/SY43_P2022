package com.example.sy43_p2022.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R

class ButtonAdapter : RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {

    //On range ici tous les composants à controller
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Button à ranger
        val grayButton = view.findViewById<Button>(R.id.button_gray_items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context).
                inflate(R.layout.item_vertical_grey, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun getItemCount(): Int = 13
}