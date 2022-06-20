package com.example.gestimali

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun seeMonthOverview(view : View){
        val intent = Intent(this,MonthOverviewActivity::class.java)
        startActivity(intent)
    }

    fun seeWishOverview(view : View){
        val intent = Intent(this,WishActivity::class.java)
        startActivity(intent)
    }

}