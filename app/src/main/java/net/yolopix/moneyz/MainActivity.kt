package net.yolopix.moneyz

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.utils.DatabaseFactory

const val EXTRA_MESSAGE = "net.yolopix.moneyz.ACCOUNT_UID"

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseFactory.getDB(applicationContext)

        // Show the account creation fragment when clicking on the new account button
        val addAccountButton: Button = findViewById(R.id.button_add_account)
        addAccountButton.setOnClickListener {
            AddAccountBottomSheet(db).apply {
                show(supportFragmentManager, tag)
            }
        }

        // Set up recycler view
        recyclerView = findViewById(R.id.account_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        val swipeToDeleteExpense = object : SwipeToDeleteExpense() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                lifecycleScope.launch {
                    val accountList = db.accountDao().getAll()
                    val position = viewHolder.adapterPosition
                    db.accountDao().deleteAccount(accountList[position])
                    loadAccounts()
                    Snackbar.make(
                        recyclerView,
                        R.string.info_deleted_account,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteExpense)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        lifecycleScope.launch {
            loadAccounts()
        }
    }

    // Asynchronously load all accounts from the database and display them in a RecyclerView
    suspend fun loadAccounts() {
        val adapter = AccountAdapter(db.accountDao().getAll())
        recyclerView.adapter = adapter
    }

}