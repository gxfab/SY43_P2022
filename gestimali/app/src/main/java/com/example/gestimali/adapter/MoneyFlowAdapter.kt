package com.example.gestimali.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.example.gestimali.R
import com.example.gestimali.income.Income

class MoneyFlowAdapter (var categoryInt : Int): RecyclerView.Adapter<MoneyFlowAdapter.ViewHolder>() {
    private var incomeList = emptyList<Income>()

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_money_flow,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoneyFlowAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}