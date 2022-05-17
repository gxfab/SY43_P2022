package net.yolopix.moneyz

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import net.yolopix.moneyz.model.AppDatabase


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "moneyz_db"
        ).build()

        lifecycleScope.launch {
            loadAccounts(db)
        }
    }

    private suspend fun loadAccounts(db: AppDatabase) {
        Log.d("MoneyZ.MainActivity", "Début chargement comptes")

        val recyclerView = findViewById<RecyclerView>(R.id.account_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)


        runBlocking {
            val adapter = AccountAdapter(db.accountDao().getAll())
            recyclerView.adapter = adapter
        }

    }

    fun openAccount(view: View) {
        val intent = Intent(this, AccountActivity::class.java).apply {}
        startActivity(intent)
    }


}