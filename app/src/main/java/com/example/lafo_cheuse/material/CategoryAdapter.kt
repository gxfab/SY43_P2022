package com.example.lafo_cheuse.material

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.CategoryChooserActivity
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.models.Category

/**
 * [RecyclerView.Adapter] for the CategoryChooserActivity [RecyclerView]
 *
 * @property context - [Activity] context of the adapter
 * @property mCategories - list of [Category] containing the [Category] to display
 */
class CategoryAdapter(var context : Activity) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var mCategories: List<Category> = ArrayList<Category>()

    /**
     * Inner class that will hold the widgets of one [RecyclerView] item.
     *
     * @param itemView - a [View] representing an item of the layout
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById<TextView>(R.id.categoryName)
        val categoryEmojiButton: Button = itemView.findViewById<Button>(R.id.emojiButton)
    }

    /**
     * Callback function called when the [ViewHolder] is created to let it initialized himself correctly
     *
     * @return a [ViewHolder] initialized
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val categoryItemView = inflater.inflate(R.layout.category_item, parent, false)
        // Return a new holder instance
        return ViewHolder(categoryItemView)
    }

    /**
     * Callback function called when the [holder] is initialized to display data on elements at [position]
     *
     * @param holder - [ViewHolder] holding all the elements
     * @param position - [Int] containing the index of the [View] in [mCategories]
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val category: Category = mCategories[position]
        // Set item views based on your views and data model
        holder.categoryName.text = category.categoryName
        holder.categoryEmojiButton.text = category.categoryEmoji

        holder.categoryEmojiButton.setOnClickListener {
            (context as CategoryChooserActivity).chooseCategory(category)
        }
    }

    /**
     * Callback function which send the number of elements in the [RecyclerView]
     *
     * @return [Int] - the size of [mCategories]
     */
    override fun getItemCount(): Int {
        return mCategories.size

    }

    /**
     * Update categories in the [RecyclerView] by updating [mCategories]
     *
     * @param mCategories - a list of [Category]
     */
    fun setCategories(mCategories : List<Category>) {
        this.mCategories = mCategories
        notifyDataSetChanged()
    }

}