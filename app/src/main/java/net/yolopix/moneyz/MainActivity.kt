package net.yolopix.moneyz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val db = Room.databaseBuilder(
			applicationContext,
			AppDatabase::class.java, "moneyz_db"
		).fallbackToDestructiveMigration().build()

		lifecycleScope.launch {
			loadAccounts(db)
		}
	}

	private suspend fun loadAccounts(db: AppDatabase) {
		Log.d("MoneyZ.MainActivity", "Début chargement comptes")

		val recyclerView = findViewById<RecyclerView>(R.id.account_recycler_view)
		recyclerView.layoutManager = LinearLayoutManager(applicationContext)

		val adapter = AccountAdapter(db.accountDao().getAll())
		recyclerView.adapter = adapter

	}

	fun openAccount(view: View) {
		val intent = Intent(this, AccountActivity::class.java).apply {}
		startActivity(intent)
	}

	fun openAccountCreation(view: View) {
		AddAccountBottomSheet().apply {
			show(supportFragmentManager, tag)
		}
	}

	class AddAccountBottomSheet : BottomSheetDialogFragment() {

		override fun onCreateView(
			inflater: LayoutInflater,
			container: ViewGroup?,
			savedInstanceState: Bundle?
		): View? {
			return inflater.inflate(R.layout.bottom_sheet_add_account, container, false)
		}


	}

}