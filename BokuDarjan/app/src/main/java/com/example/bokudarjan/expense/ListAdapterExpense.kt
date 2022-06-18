package com.example.bokudarjan.expense

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.R
import com.example.bokudarjan.category.Category
import com.example.bokudarjan.category.CategoryViewModel
import kotlinx.android.synthetic.main.expense_card.view.*

/**
 * first ListAdapter of the [Expense], allowing compatibility with recyclerViews.
 */
class ListAdapterExpense: RecyclerView.Adapter<ListAdapterExpense.MyViewHolder>() {

    private var expenseList = emptyList<Expense>()
    private lateinit var categoryViewModel: CategoryViewModel


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}
    /**
     * Inflate the expense_card layout in the recyclerView and returns the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        categoryViewModel = ViewModelProvider(parent.findViewTreeViewModelStoreOwner()!!).get(CategoryViewModel::class.java)
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.expense_card, parent, false))
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    @SuppressLint("SetTextI18n")
    /**
     * Setup the content of the previously inflated view to reflect an entry in the list
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = expenseList[position]
        holder.itemView.savingName.text = currentItem.name
        holder.itemView.nameCategoryExpense.text = currentItem.categoryName + ", le " + currentItem.date + " du mois"
        holder.itemView.savingAmount.text =  String.format("%.2f", currentItem.amount) + "€"
        categoryViewModel.getCategory(currentItem.categoryName).observeForever{
            holder.itemView.categoryCircle.setColorFilter(Color.parseColor(it[0].color));
        }



    }
    /**
     * Set up the list of [Expense] that will be use to generate the recyclerView
     */
    fun setData(expense: List<Expense>){
        this.expenseList = expense
        notifyDataSetChanged()
    }

}