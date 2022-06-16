package com.example.sy43_p2022.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.database.PiggyBankDatabase
import com.example.sy43_p2022.database.entities.SubCategory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TextEditAdapter(
    private val layoutId: Int,
    private val subCategories: List<SubCategory>,
    private val type: String,
) : RecyclerView.Adapter<TextEditAdapter.TextEditViewHolder>() {

    private lateinit var db: PiggyBankDatabase

    inner class TextEditViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var subCategory: SubCategory
        var title: TextView = view.findViewById<TextView>(R.id.subcategory_title)
        var amount: TextView = view.findViewById<TextView>(R.id.editText)
        var button: Button = view.findViewById<Button>(R.id.validate_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextEditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        db = PiggyBankDatabase.getDatabase(view.context);

        return TextEditViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextEditViewHolder, position: Int) {
        subCategories[position].let {
            holder.subCategory = it
            holder.title.text = it.name
            holder.amount.text = if (type == "saving") it.saving.toString() else it.spending.toString()
            holder.button.setOnClickListener() {
                MainScope().launch {
                    if (type == "saving") db.piggyBankDAO().updateSubCategorySaving(holder.subCategory.subid, holder.amount.text.toString().toInt())
                    else db.piggyBankDAO().updateSubCategorySpending(holder.subCategory.subid, holder.amount.text.toString().toInt())
                }
            }
        }
    }

    override fun getItemCount(): Int = subCategories.size
}
