package com.sy43_p22.piggy_bank.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sy43_p22.piggy_bank.R
import com.sy43_p22.piggy_bank.database.PiggyBankDatabase
import com.sy43_p22.piggy_bank.database.entities.Category
import com.sy43_p22.piggy_bank.database.entities.SubCategory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @description Adapter for the recycler view of the categories
 * @param categories List of categories
 * @param layoutId Layout id of the recycler view
 * @param onClickListener specific class to determines what should happen on click
 * @param layoutType "white" | "gray" determines what layout is currently displayed/used
 */
class ButtonAdapter(
    private val layoutId: Int,
    private val categories: List<Category>,
    private val onClickListener: OnClickListener,
    private val layoutType: String,
) : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    // database access
    private lateinit var db: PiggyBankDatabase

    /**
     * @description Run when a button is clicked
     * @param recyclerView mandatory to change the adapter (recursively)
     * @param layoutType "white" | "gray" determines what layout should be used/displayed
     */
    class OnClickListener(private val recyclerView: RecyclerView, private val layoutType: String) {
        fun onClick(subCategories: List<SubCategory>) {
            val id: Int = if (layoutType == "white") R.layout.item_sub_white else R.layout.item_sub_gray
            val save_or_spend: String = if (layoutType == "white") "saving" else "spending"
            recyclerView.adapter = TextEditAdapter(id, subCategories, save_or_spend)
        }
    }

    /**
     * @description Fetch view & request the database when the view holder is created
     * @param parent
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        db = PiggyBankDatabase.getDatabase(view.context);

        return ButtonViewHolder(view)
    }

    /**
     * @description Fetch items to modify later
     * @param view the actual view
     */
    inner class ButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var category: Category
        var title: TextView = view.findViewById(R.id.subcategory_title)
        var amount: TextView = view.findViewById(R.id.subcategory_amount)
        var button: Button = view.findViewById(R.id.sub_category_button)
        var progress: ProgressBar? = view.findViewById(R.id.vertical_align_progress)
    }

    /**
     * @description Setup all actions / data for each buttons
     * @param holder the button itself
     * @param position the index of the actual button
     */
    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        categories[position].let {
            holder.category = it
            holder.title.text = it.name

            // display registered value
            holder.amount.text = if (layoutType == "white") it.saving.toString() else it.spending.toString()

            // update value
            MainScope().launch {
                val saving = db.piggyBankDAO().getCategorySaving(holder.category.catid)
                val spending = db.piggyBankDAO().getCategorySpending(holder.category.catid)

                holder.amount.text = holder.button.context.getString(R.string.amount, if (layoutType == "white") saving else spending)
                if (layoutType != "white") holder.progress?.progress = if (saving > 0) (spending * 100) / saving else 0

                if (layoutType == "white") db.piggyBankDAO().updateCategorySaving(holder.category.catid,  holder.amount.text.toString().replace("€", "").toInt())
                else db.piggyBankDAO().updateCategorySpending(holder.category.catid,  holder.amount.text.toString().replace("€", "").toInt())
            }

            // what should happens when we click the button
            holder.button.setOnClickListener() {
                MainScope().launch {
                    val subCategories: List<SubCategory> = db.piggyBankDAO().getSubCategoriesByCategoryId(holder.category.catid)
                    onClickListener.onClick(subCategories)
                }
            }
        }
    }

    /** @description determines the number of items to be displayed */
    override fun getItemCount(): Int = categories.size
}