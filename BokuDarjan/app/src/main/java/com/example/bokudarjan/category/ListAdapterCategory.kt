package com.example.bokudarjan.category

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.R
import com.example.bokudarjan.bmonth.BMonth
import com.example.bokudarjan.envelope.EnvelopeViewModel
import kotlinx.android.synthetic.main.envelope_category_card.view.*


/**
 * first ListAdapter of the [Category], allowing compatibility with recyclerViews.
 */
class ListAdapterCategory : RecyclerView.Adapter<ListAdapterCategory.MyViewHolder>() {
    private var categoryList = emptyList<Category>()
    private lateinit var envelopeViewModel: EnvelopeViewModel

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    /**
     * Inflate the envelope_category_card layout in the recyclerView and returns the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        envelopeViewModel = ViewModelProvider(parent.findViewTreeViewModelStoreOwner()!!).get(EnvelopeViewModel::class.java)
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.envelope_category_card, parent, false))
    }


    @SuppressLint("SetTextI18n")
    /**
     * Setup the content of the previously inflated view to reflect an entry in the list
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.itemView.nameCategory.text = currentItem.categoryName
        holder.itemView.header.setColorFilter(Color.parseColor(currentItem.color))
        holder.itemView.expensesLayout.background.setTint(Color.parseColor( "#80" + currentItem.color.substring(1)))
        val context = holder.itemView.nameCategory.context;
        holder.itemView.expensesLayout.removeAllViews()

        var pref = holder.itemView.context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val month = pref.getInt("month", -1)


        envelopeViewModel.getMonthData(month).observeForever{
            var i = 0
            var total = 0f
            holder.itemView.expensesLayout.removeAllViews()
            for(item in it){
                Log.d("[DAO]",i.toString())
                if(currentItem.categoryName == item.categoryName){
                    var txt = TextView(context);
                    txt.text = item.name + " : " + String.format("%.2f",item.amount) + "€"
                    total += item.amount.toFloat()
                    txt.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f)
                    txt.setTextColor(Color.parseColor("#FFFFFF"))
                    holder.itemView.expensesLayout.addView(txt, i)
                    i++
                }
            }
            holder.itemView.nameCategory.text = currentItem.categoryName + " : " + String.format("%.2f",total) + "€"
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