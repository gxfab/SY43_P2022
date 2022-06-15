package net.yolopix.moneyz.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.yolopix.moneyz.R
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Month
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HistoryAdapter(private val db: AppDatabase, private val monthList: List<Month>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    /**
     * A nested class for the view holder
     */
    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val monthNameTextView: TextView = itemView.findViewById(R.id.textview_month_name)
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent
     * an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_month, parent, false)
        return HistoryViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the ViewHolder.itemView to reflect the item at the given
     * position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val month: Month = monthList[position]
        val monthAsLocalDate: LocalDate =
            LocalDate.of(month.yearNumber, month.monthNumber, month.payday)
        val monthFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        holder.monthNameTextView.text = monthAsLocalDate.format(monthFormat)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return monthList.size
    }
}