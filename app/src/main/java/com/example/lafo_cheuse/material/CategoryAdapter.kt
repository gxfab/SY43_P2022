package com.example.lafo_cheuse.material

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.models.Category

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
class CategoryAdapter(var context : Activity) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var mCategories: List<Category> = ArrayList<Category>()

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val categoryName: TextView = itemView.findViewById<TextView>(R.id.categoryName)
        val categoryEmojiButton: Button = itemView.findViewById<Button>(R.id.emojiButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val categoryItemView = inflater.inflate(R.layout.category_item, parent, false)
        // Return a new holder instance
        return ViewHolder(categoryItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val category: Category = mCategories[position]
        // Set item views based on your views and data model
        holder.categoryName.text = category.categoryName
        holder.categoryEmojiButton.text = category.categoryEmoji

        holder.categoryEmojiButton.setOnClickListener {
            chooseCategory(category)
        }
    }

    override fun getItemCount(): Int {
        return mCategories.size

    }

    fun setCategories(mCategories : List<Category>) {
        this.mCategories = mCategories
        notifyDataSetChanged()
    }

    fun chooseCategory(categoryChosen : Category) {
        val resultIntent = Intent()
        resultIntent.putExtra("categoryId", categoryChosen.categoryId)
        resultIntent.putExtra("categoryName", categoryChosen.categoryName)
        resultIntent.putExtra("categoryEmoji",categoryChosen.categoryEmoji)
        context.setResult(AppCompatActivity.RESULT_OK, resultIntent)
        context.finish()
    }
}