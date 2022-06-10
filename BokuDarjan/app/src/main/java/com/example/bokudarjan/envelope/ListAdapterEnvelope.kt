package com.example.bokudarjan.envelope

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.R
import kotlinx.android.synthetic.main.envelope_card.view.*


class ListAdapterEnvelope : RecyclerView.Adapter<ListAdapterEnvelope.MyViewHolder>() {

    private var envelopeList = emptyList<Envelope>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.envelope_card, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = envelopeList[position]
        holder.itemView.nameEnvelope.text = currentItem.name
        holder.itemView.amountEnvelope.text = currentItem.amount.toString()

    }


    override fun getItemCount(): Int {
        return envelopeList.size
    }



    fun setData(envelope: List<Envelope>){
        this.envelopeList = envelope
        notifyDataSetChanged()
    }



}