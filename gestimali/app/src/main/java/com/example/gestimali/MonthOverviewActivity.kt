package com.example.gestimali

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.gestimali.adapter.MonthAdapter

/**
 * Activity on which we can see each month planned and an sum up of the budget we plan
 */
class MonthOverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_month_overview)

        //Set up the recycler view
        val monthRecyclerView = this.findViewById<RecyclerView>(R.id.month_recycler_view)
        monthRecyclerView.adapter = MonthAdapter(this)

    }

    /**
     * Supposed to see the in detail the month budget but not implemented yet
     */
    fun seeIncome(view : View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }


}