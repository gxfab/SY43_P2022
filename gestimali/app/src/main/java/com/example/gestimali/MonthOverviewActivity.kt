package com.example.gestimali

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.gestimali.adapter.MonthAdapter

class MonthOverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_month_overview)

        val monthRecyclerView = this.findViewById<RecyclerView>(R.id.month_recycler_view)
        monthRecyclerView.adapter = MonthAdapter(this)

    }

    fun seeIncome(view : View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }


}