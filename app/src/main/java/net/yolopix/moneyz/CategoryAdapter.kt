package net.yolopix.moneyz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.yolopix.moneyz.model.entities.Category

class CategoryAdapter(private val categoryList : MutableList<Category>)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>()

{

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryNameTextView: TextView = itemView.findViewById(R.id.categoryName)
        val categoryPriceTextView: TextView = itemView.findViewById(R.id.categoryPrice)
        val linearLayoutCategory: LinearLayout = itemView.findViewById(R.id.linearLayoutContainerCategory)
        val buttonDelete : Button = itemView.findViewById(R.id.button_delete_category)
    }




    override fun onBindViewHolder(viewHolder: CategoryAdapter.CategoryViewHolder, position: Int) {
        viewHolder.categoryNameTextView.text  = categoryList[position].name
        viewHolder.categoryPriceTextView.text = categoryList[position].predictedAmount.toString()
        viewHolder.buttonDelete.setOnClickListener{deleteItem(position)}

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
    fun deleteItem(index: Int){
        categoryList.removeAt(index)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.item_category_prevision, parent, false)
        return CategoryAdapter.CategoryViewHolder(view)
    }



}