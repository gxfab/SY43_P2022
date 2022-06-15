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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_account, parent, false)
        return AccountViewHolder(view)
    }

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

    override fun getItemCount(): Int {
        return accountList.size
    }

}