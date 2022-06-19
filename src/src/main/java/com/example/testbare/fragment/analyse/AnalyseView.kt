package com.example.testbare.fragment.analyse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anychart.AnyChartView
import com.anychart.charts.Pie
import com.example.testbare.R

class AnalyseView(val listGraph : ArrayList<Pie>)
    :RecyclerView.Adapter<AnalyseView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ma_ligne = LayoutInflater.from(parent.context).inflate(R.layout.item_caroussel, parent, false)
        return ViewHolder(ma_ligne)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGraph[position])
    }

    override fun getItemCount(): Int = listGraph.size

    class ViewHolder(val elementDeListe: View) : RecyclerView.ViewHolder(elementDeListe) {
        fun bind(pie: Pie) = with(itemView){
            itemView.findViewById<AnyChartView>(R.id.itemCaroussel_acv_graph).setChart(pie)
        }
    }
}