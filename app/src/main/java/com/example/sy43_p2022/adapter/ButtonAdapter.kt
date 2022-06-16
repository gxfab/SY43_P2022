package com.example.sy43_p2022.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.database.PiggyBankDatabase
import com.example.sy43_p2022.database.entities.Category
import com.example.sy43_p2022.database.entities.SubCategory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ButtonAdapter(
    private val layoutId: Int,
    private val categories: List<Category>,
    private val onClickListener: OnClickListener,
) : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    private lateinit var db: PiggyBankDatabase

    class OnClickListener(private val recyclerView: RecyclerView, private val layoutType: String) {
        fun onClick(subCategories: List<SubCategory>) {
            val id: Int = if (layoutType == "white") R.layout.item_sub_white else R.layout.item_sub_gray
            val save_or_spend: String = if (layoutType == "white") "saving" else "spending"
            recyclerView.adapter = TextEditAdapter(id, subCategories, save_or_spend)
        }
    }

    inner class ButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var category: Category
        var title: TextView = view.findViewById<TextView>(R.id.subcategory_title)
        var amount: TextView = view.findViewById<TextView>(R.id.subcategory_amount)
        var button: Button = view.findViewById<Button>(R.id.sub_category_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        db = PiggyBankDatabase.getDatabase(view.context);

        return ButtonViewHolder(view)
    }


    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        categories[position].let {
            holder.category = it
            holder.title.text = it.name
            holder.amount.text = it.amount.toString()
            holder.button.setOnClickListener() {
                MainScope().launch {
                    val subCategories: List<SubCategory> = db.piggyBankDAO().getSubCategoriesByCategoryId(holder.category.catid)
                    onClickListener.onClick(subCategories)
                }
            }
        }
    }

    override fun getItemCount(): Int = categories.size
}