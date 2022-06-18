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
import com.example.bokudarjan.dialog.AddExpenseDialog
import com.example.bokudarjan.R
import com.example.bokudarjan.envelope.EnvelopeViewModel
import com.example.bokudarjan.expense.ExpenseViewModel
import kotlinx.android.synthetic.main.expense_category.view.*

/**
 * second ListAdapter of the [Category], allowing compatibility with recyclerViews.
 * It uses a different layout
 */
class ListAdapterCategory2 : RecyclerView.Adapter<ListAdapterCategory2.MyViewHolder>() {
    private var categoryList = emptyList<Category>()
    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var envelopeViewModel: EnvelopeViewModel


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}
    /**
     * Inflate the expense_category layout in the recyclerView and returns the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        expenseViewModel = ViewModelProvider(parent.findViewTreeViewModelStoreOwner()!!).get(ExpenseViewModel::class.java)
        envelopeViewModel = ViewModelProvider(parent.findViewTreeViewModelStoreOwner()!!).get(EnvelopeViewModel::class.java)
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.expense_category, parent, false))
    }

    /**
     * Setup the content of the previously inflated view to reflect an entry in the list
     * Display categories when clicking on + button in planification fragment
     */
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

        // Display sum of envelope by category
        envelopeViewModel.getSumOfEnvelopeByCategory(currentItem.categoryName, month).observeForever{
            if(it != null){
                holder.itemView.catSumEnv.text = it.toString() + " € de prévu"
            }else{
                holder.itemView.catSumEnv.text = "0€ de prévu"
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

    /**
     * Set up the list of [Category] that will be use to generate the recyclerView
     */
    fun setData(category: List<Category>){
        this.categoryList = category
        notifyDataSetChanged()
    }
}