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
import net.yolopix.moneyz.model.database.Account


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "moneyz_db"
        ).fallbackToDestructiveMigration().build()

        lifecycleScope.launch {
            loadAccounts(db)
            initStubData(db)
        }
    }

    private suspend fun loadAccounts(db: AppDatabase) {
        Log.d("MoneyZ.MainActivity", "Début chargement comptes")

        val recyclerView = findViewById<RecyclerView>(R.id.account_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        val adapter = AccountAdapter(db.accountDao().getAll())
        recyclerView.adapter = adapter

    }

    private suspend fun initStubData(db: AppDatabase) {
        val test = Account(1,"test compte");
    }

    fun openAccount(view: View) {
        val intent = Intent(this, AccountActivity::class.java).apply {}
        startActivity(intent)
    }


}