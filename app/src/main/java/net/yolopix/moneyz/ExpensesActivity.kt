package net.yolopix.moneyz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Account

class ExpensesActivity : AppCompatActivity() {

    private lateinit var account: Account
    private lateinit var db: AppDatabase
    private lateinit var depensesRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses)

        //depensesRV = findViewById(R.id.expenses_recy_view)

        db = DatabaseFactory.getDB(applicationContext)

        val accountUid: Int? = intent.getStringExtra(EXTRA_MESSAGE)?.toInt()
        lifecycleScope.launch {
            if (accountUid == null) {
                finish()
            } else {
                loadAccount(accountUid)
            }
        }

    }

    /**
     * @param uid: Account identifier in the database
     */
    private suspend fun loadAccount(uid: Int) {
        account = db.accountDao().getAccountById(uid)
        title = getString(R.string.expenses_title,account.name)
        supportActionBar?.subtitle = "mettre le mois ici ???"
    }
}