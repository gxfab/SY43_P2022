package com.example.bokudarjan.category

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.AddExpenseDialog
import com.example.bokudarjan.R
import kotlinx.android.synthetic.main.envelope_category_card.view.*
import kotlinx.android.synthetic.main.expense_category.view.*


class ListAdapterCategory2 : RecyclerView.Adapter<ListAdapterCategory2.MyViewHolder>() {
    private var categoryList = emptyList<Category>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.expense_category, parent, false))
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val manager: FragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager

        val currentItem = categoryList[position]
        holder.itemView.back.setColorFilter(Color.parseColor(currentItem.color))
        holder.itemView.catName.text = currentItem.categoryName;


        holder.itemView.back.setOnClickListener {
            var dialog = AddExpenseDialog(currentItem.categoryName)
            dialog.show(manager, "")
        }
        }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(category: List<Category>){
        this.categoryList = category
        notifyDataSetChanged()
    }
}