package net.yolopix.moneyz

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Account

class ExpensesActivity : AppCompatActivity() {

    private lateinit var account: Account
    private lateinit var db: AppDatabase
    private lateinit var expensesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses)

        db = DatabaseFactory.getDB(applicationContext)

        val accountUid: Int? = intent.getStringExtra(EXTRA_MESSAGE)?.toInt()
        lifecycleScope.launch {
            if (accountUid == null) {
                finish()
            } else {
                loadAccount(accountUid)
            }
        }

        val addExpenseButton = findViewById<Button>(R.id.button_add_expense)
        addExpenseButton.setOnClickListener {
            AddExpenseBottomSheet(db).apply {
                show(supportFragmentManager, tag)
            }
        }

        // RecylerView
        expensesRecyclerView = findViewById(R.id.expenses_recy_view)
        expensesRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        lifecycleScope.launch {
            loadExpenses()
        }
    }

    /**
     * @param uid: Account identifier in the database
     */
    private suspend fun loadAccount(uid: Int) {
        account = db.accountDao().getAccountById(uid)
        title = getString(R.string.expenses_title,account.name)
        //supportActionBar?.subtitle = "mettre le mois ici ???"
    }

    private suspend fun loadExpenses() {
        expensesRecyclerView.adapter = ExpensesAdapter(db.expenseDao().getAll())
    }
}