package com.sy43_p22.piggy_bank.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sy43_p22.piggy_bank.R
import com.sy43_p22.piggy_bank.database.PiggyBankDatabase
import com.sy43_p22.piggy_bank.database.entities.SubCategory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @param layoutId Layout id of the recycler view
 * @param subCategories Sub Categories to be displayed/used
 * @param type "white" | "gray" determines what layout is currently displayed/used
 */
class TextEditAdapter(
    private val layoutId: Int,
    private val subCategories: List<SubCategory>,
    private val type: String,
) : RecyclerView.Adapter<TextEditAdapter.TextEditViewHolder>() {

    // database access
    private lateinit var db: PiggyBankDatabase

    /**
     * Fetch view & request the database when the view holder is created
     * @param parent
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextEditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        db = PiggyBankDatabase.getDatabase(view.context);

        return TextEditViewHolder(view)
    }

    /**
     * Fetch items to modify later
     * @param view the actual view
     */
    inner class TextEditViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var subCategory: SubCategory
        var title: TextView = view.findViewById<TextView>(R.id.subcategory_title)
        var amount: TextView = view.findViewById<TextView>(R.id.editText)
        var button: Button = view.findViewById<Button>(R.id.validate_button)
    }

    /**
     * Setup all actions / data for each textEdit
     * @param holder the textEdit itself
     * @param position the index of the actual textEdit
     */
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

    // determines the number of items to be displayed
    override fun getItemCount(): Int = subCategories.size
}
