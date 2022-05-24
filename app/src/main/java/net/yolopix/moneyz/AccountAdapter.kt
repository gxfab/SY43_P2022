package net.yolopix.moneyz

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import net.yolopix.moneyz.model.entities.Account


class AccountAdapter(private val accountList: List<Account>) :
    RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    // Inner class for the view holder
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
                putExtra("net.yolopix.moneyz.ACCOUNT_UID", accountList[position].uid)
            }
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

}