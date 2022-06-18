package com.example.bokudarjan.envelope

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.R
import com.example.bokudarjan.category.Category
import kotlinx.android.synthetic.main.envelope_card.view.*

/**
 * ListAdapter of the [Envelope], allowing compatibility with recyclerViews.
 */
class ListAdapterEnvelope : RecyclerView.Adapter<ListAdapterEnvelope.MyViewHolder>() {

    private var envelopeList = emptyList<Envelope>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    /**
     * Inflate the envelope_card layout in the recyclerView and returns the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.envelope_card, parent, false))
    }

    /**
     * Setup the content of the previously inflated view to reflect an entry in the list
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = envelopeList[position]
        holder.itemView.nameEnvelope.text = currentItem.name
        holder.itemView.amountEnvelope.text = currentItem.amount.toString()

    }


    override fun getItemCount(): Int {
        return envelopeList.size
    }


    /**
     * Set up the list of [Envelope] that will be use to generate the recyclerView
     */
    fun setData(envelope: List<Envelope>){
        this.envelopeList = envelope
        notifyDataSetChanged()
    }



}