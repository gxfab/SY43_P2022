package net.yolopix.moneyz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class ExpensesActivity : AppCompatActivity() {
    private lateinit var depensesRV : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses)
        depensesRV = findViewById(R.id.expenses_recy_view)
    }
}