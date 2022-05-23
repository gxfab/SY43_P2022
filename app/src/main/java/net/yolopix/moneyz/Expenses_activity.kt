package net.yolopix.moneyz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class Expenses_activity : AppCompatActivity() {
    lateinit var DepensesRV : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses)
        DepensesRV = findViewById(R.id.expenses_recy_view)
    }
}