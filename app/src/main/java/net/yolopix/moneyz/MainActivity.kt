package net.yolopix.moneyz

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase


class MainActivity : AppCompatActivity() {

	private lateinit var db: AppDatabase

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		db = Room.databaseBuilder(
			applicationContext,
			AppDatabase::class.java, "moneyz_db"
		).fallbackToDestructiveMigration().build()

		lifecycleScope.launch {
			loadAccounts()
		}

		// Show the account creation fragment when clicking on the new account button
		val addAccountButton = findViewById<Button>(R.id.button_add_account)
		addAccountButton.setOnClickListener {
			AddAccountBottomSheet(db).apply {
				show(supportFragmentManager, tag)
			}
		}
	}

	// Asynchronously load all accounts from the database and display them in a RecyclerView
	private suspend fun loadAccounts() {
		val recyclerView = findViewById<RecyclerView>(R.id.account_recycler_view)
		recyclerView.layoutManager = LinearLayoutManager(applicationContext)

		val adapter = AccountAdapter(db.accountDao().getAll())
		recyclerView.adapter = adapter
	}

}