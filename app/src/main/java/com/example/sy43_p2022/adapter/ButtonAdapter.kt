package com.example.sy43_p2022.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.database.PiggyBankDatabase
import com.example.sy43_p2022.database.entities.Category
import androidx.lifecycle.lifecycleScope
import com.example.sy43_p2022.database.entities.SubCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ButtonAdapter(private val layoutId: Int, private val subCategories: List<SubCategory>, private val onClickListener: OnClickListener)
    : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {
    private lateinit var db: PiggyBankDatabase

    class OnClickListener(private val recyclerView: RecyclerView, private val layoutType: String) {
        fun onClick(category: SubCategory) {
            val id: Int = if (layoutType == "white") R.layout.item_sub_white else R.layout.item_sub_gray
            recyclerView.adapter = TextEditAdapter(id, category)
        }
    }

    inner class ButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById<TextView>(R.id.subcategory_title)
        var amount: TextView = view.findViewById<TextView>(R.id.subcategory_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        db = PiggyBankDatabase.getDatabase(view.context);

        return ButtonViewHolder(view)
    }


    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        /*
        // TODO: should be the following (untested)
        GlobalScope.launch {
           val category = db.subcategoryDAO().getSubcategoryById(position)
            holder.title.text = category.name
            holder.amount.text = category.amount.toString()
            holder.itemView.setOnClickListener() {
                onClickListener.onClick(category)
            }
        }
        // OLD WAY
        category.getSubCategory(position).let {
            holder.category = it
            holder.title.text = it.getName()
            holder.amount.text = it.getGlobalObjectiveAmount().toString()
            holder.itemView.setOnClickListener() {
                onClickListener.onClick(holder.category)
            }
        }
        */
    }

    override fun getItemCount(): Int = subCategories.size
}