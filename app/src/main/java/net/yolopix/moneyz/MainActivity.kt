package net.yolopix.moneyz

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase

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