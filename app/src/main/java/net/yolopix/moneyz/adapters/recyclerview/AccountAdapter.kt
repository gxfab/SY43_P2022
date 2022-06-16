package net.yolopix.moneyz.adapters.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import net.yolopix.moneyz.EXTRA_MESSAGE
import net.yolopix.moneyz.ExpensesActivity
import net.yolopix.moneyz.R
import net.yolopix.moneyz.model.entities.Account

/**
 * Account adapter: this class manages display of accounts in a RecyclerView
 *
 * @param accountList The list of accounts to display
 */
class AccountAdapter(private val accountList: List<Account>) :
    RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    /**
     * A nested class for the view holder
     */
    class AccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accountNameTextView: TextView = itemView.findViewById(R.id.account_name)
        val cardContainer: CardView = itemView.findViewById(R.id.card_container)
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_account, parent, false)
        return AccountViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the ViewHolder.itemView to reflect the item at the given
     * position.
     *
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(viewHolder: AccountViewHolder, position: Int) {
        viewHolder.accountNameTextView.text = accountList[position].name

        // When the account item is clicked, open a specific account passed in extra
        viewHolder.cardContainer.setOnClickListener {
            val intent = Intent(it.context, ExpensesActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, accountList[position].uid)
            }
            it.context.startActivity(intent)
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return accountList.size
    }

}