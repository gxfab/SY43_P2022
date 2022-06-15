package net.yolopix.moneyz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import net.yolopix.moneyz.adapters.recyclerview.HistoryAdapter
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Month
import net.yolopix.moneyz.utils.DatabaseFactory

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        title = getString(R.string.title_history_screen)

        // Initialize the recyclerview
        val historyRecyclerView: RecyclerView = findViewById(R.id.recyclerview_history)
        historyRecyclerView.layoutManager = LinearLayoutManager(applicationContext)

        // Get the database
        val db: AppDatabase = DatabaseFactory.getDB(applicationContext)

        // Fetch the account from the uid passed to the activity as extra
        val accountUid: Int = intent.getIntExtra(EXTRA_MESSAGE, 0)
        lifecycleScope.launch {
            if (accountUid == 0) {
                finish()
            } else {
                // Populate the recyclerview
                lifecycleScope.launch {
                    val monthList: List<Month> = db.monthDao().getMonthsForAccountUid(accountUid)
                    historyRecyclerView.adapter = HistoryAdapter(db,monthList)
                }
            }
        }

    }
}