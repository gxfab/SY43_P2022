package com.example.bokudarjan.bmonth

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.ExpensesFragment
import com.example.bokudarjan.R
import com.example.bokudarjan.category.CategoryViewModel
import com.example.bokudarjan.expense.Expense
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.expense_card.view.*
import kotlinx.android.synthetic.main.fragment_month.view.*

class ListAdapterBMonth: RecyclerView.Adapter<ListAdapterBMonth.MyViewHolder>() {

    private var monthList = emptyList<BMonth>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_month, parent, false))
    }

    override fun getItemCount(): Int {
        return monthList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = monthList[position]
        holder.itemView.txtMonth.text = "Mois n°" + currentItem.id.toString()
        if(currentItem.id == holder.itemView.context.getSharedPreferences("pref",Context.MODE_PRIVATE).getInt("month", -1)){
            holder.itemView.imageView4.setColorFilter(Color.WHITE)
        }else{
            holder.itemView.imageView4.setColorFilter(Color.TRANSPARENT)
        }
        holder.itemView.imageView4.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Changement de mois", Toast.LENGTH_SHORT).show()
            holder.itemView.context.getSharedPreferences("pref",Context.MODE_PRIVATE).edit().putInt("month", currentItem.id).apply()
            notifyDataSetChanged()
            var hostFragement = holder.itemView.rootView.findViewById<FragmentContainerView>(R.id.nav_host_fragment)[0]

            if (hostFragement.findViewById<RecyclerView>(R.id.recyclerViewCategory) != null){
                hostFragement.findViewById<RecyclerView>(R.id.recyclerViewCategory).adapter!!.notifyDataSetChanged()
            }
            if (hostFragement.findViewById<RecyclerView>(R.id.recyclerViewExpense) != null){


            }



        }

    }

    fun setData(month: List<BMonth>){
        this.monthList = month
        notifyDataSetChanged()
    }

}