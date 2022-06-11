package com.example.gestimali

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.gestimali.adapter.MonthAdapter

class MonthOverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_month_overview)

        val monthRecyclerView = this.findViewById<RecyclerView>(R.id.month_recycler_view)
        monthRecyclerView.adapter = MonthAdapter()
    }
}