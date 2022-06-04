package net.yolopix.moneyz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.yolopix.moneyz.model.entities.Category

class CategoryAdapter(private val categoryList: List<Category>, private val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryNameTextView: TextView = itemView.findViewById(R.id.categoryName)
        val categoryPriceTextView: TextView = itemView.findViewById(R.id.categoryPrice)
        val buttonDelete: Button = itemView.findViewById(R.id.button_delete_category)

    }

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.categoryNameTextView.text = categoryList[position].name
        viewHolder.categoryPriceTextView.text = context.getString(
            R.string.money_format,
            String.format("%.2f", categoryList[position].predictedAmount)
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_category_prevision, parent, false)
        return CategoryViewHolder(view)
    }

}

