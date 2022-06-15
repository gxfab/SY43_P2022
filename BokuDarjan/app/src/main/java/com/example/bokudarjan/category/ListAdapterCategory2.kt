package com.example.bokudarjan.category

import android.annotation.SuppressLint
import android.content.Context
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
import com.example.bokudarjan.database.BokudarjanDatabase
import com.example.bokudarjan.expense.ExpenseRepository
import com.example.bokudarjan.expense.ExpenseViewModel
import com.example.bokudarjan.month
import kotlinx.android.synthetic.main.envelope_category_card.view.*
import kotlinx.android.synthetic.main.expense_category.view.*
import kotlinx.android.synthetic.main.fragment_expenses.view.*

//Display category and sum of expenses associated
class ListAdapterCategory2 : RecyclerView.Adapter<ListAdapterCategory2.MyViewHolder>() {
    private var categoryList = emptyList<Category>()
    private lateinit var expenseViewModel: ExpenseViewModel



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        expenseViewModel = ViewModelProvider(parent.findViewTreeViewModelStoreOwner()!!).get(ExpenseViewModel::class.java)
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.expense_category, parent, false))
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        val manager: FragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager

        val currentItem = categoryList[position]
        holder.itemView.back.setColorFilter(Color.parseColor(currentItem.color))
        holder.itemView.catName.text = currentItem.categoryName;


        var pref = holder.itemView.context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val month = pref.getInt("month", -1)

        // Display sum of expense by category
        expenseViewModel.getSumOfExpenseByCategory(currentItem.categoryName, month).observeForever{
            if(it != null){
                holder.itemView.catSumExp.text = it.toString() + " € de dépensé"
            }else{
                holder.itemView.catSumExp.text = "0€ de dépensé"
            }
        }

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