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
import com.example.gestimali.envelope.Envelope
import com.example.gestimali.fixexpense.FixedExpense
import com.example.gestimali.income.Income
import com.example.gestimali.wish.Wish

class MoneyFlowAdapter (var categoryInt : Int): RecyclerView.Adapter<MoneyFlowAdapter.ViewHolder>() {
    private var incomeList = emptyList<Income>()
    private var expenseList = emptyList<FixedExpense>()
    private var wishList = emptyList<Wish>()
    private var envelopeList = emptyList<Envelope>()



    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_money_flow,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoneyFlowAdapter.ViewHolder, position: Int) {
        if(categoryInt == 0 ){
            var list = incomeList
            val currentItem = list[position]
            holder.itemView.findViewById<TextView>(R.id.name_flow).text = currentItem.inc_name
            holder.itemView.findViewById<TextView>(R.id.money_flow_date).text = currentItem.inc_day_transaction.toString()+"/"+currentItem.inc_month.toString()+"/"+currentItem.inc_year.toString()
            holder.itemView.findViewById<TextView>(R.id.plan_amount).text = currentItem.inc_planned_amount.toString()
            holder.itemView.findViewById<TextView>(R.id.real_amount).text = currentItem.inc_real_amount.toString()

        }
        else if(categoryInt == 1){
            var list = expenseList
            val currentItem = list[position]
            holder.itemView.findViewById<TextView>(R.id.name_flow).text = currentItem.exp_name

        }
        else if(categoryInt == 2){
            var list = wishList
            val currentItem = list[position]
            holder.itemView.findViewById<TextView>(R.id.name_flow).text = currentItem.wis_name

        }
        else{
            var list = envelopeList
            val currentItem = list[position]
            holder.itemView.findViewById<TextView>(R.id.name_flow).text = currentItem.env_name

        }

    }

    @JvmName("setData1")
    fun setData(income : List<Income>){
        this.incomeList = income
        notifyDataSetChanged()
    }
    @JvmName("setData2")
    fun setData(expense : List<FixedExpense>){
        this.expenseList = expense
        notifyDataSetChanged()
    }
    @JvmName("setData3")
    fun setData(wish : List<Wish>){
        this.wishList = wish
        notifyDataSetChanged()
    }
    @JvmName("setData4")
    fun setData(envelope : List<Envelope>){
        this.envelopeList = envelope
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = getSize(categoryInt)

    private fun getSize(categoryInt : Int) : Int{
        if(categoryInt == 0 ){
            return incomeList.size
        }
        else if(categoryInt == 1){
            return expenseList.size
        }
        else if(categoryInt == 2){
            return wishList.size
        }
        else{
            return expenseList.size
        }
    }
}