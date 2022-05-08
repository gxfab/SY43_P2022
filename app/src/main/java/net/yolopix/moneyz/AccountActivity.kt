package net.yolopix.moneyz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class AccountActivity : AppCompatActivity() {
    lateinit var DepensesRV : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        DepensesRV = findViewById(R.id.RV_Spending)
    }
}