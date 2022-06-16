package com.example.bokudarjan

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.*
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bokudarjan.bmonth.BMonth
import com.example.bokudarjan.bmonth.BMonthViewModel
import com.example.bokudarjan.bmonth.ListAdapterBMonth
import com.example.bokudarjan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_side_bar.view.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Setup the binding
        val binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        val host = findViewById<ConstraintLayout>(R.id.main_layout);

        val pref = getSharedPreferences("pref" ,Context.MODE_PRIVATE)
        val month = pref.getInt("month", -1)

        Log.d("[MONTH]", month.toString())


        val bottom = findViewById<BottomNavigationView>(R.id.bottomNavigationView);

        val handler = Handler();


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController;


        val drawer = findViewById<DrawerLayout>(R.id.my_drawer_layout);

        val drawerView = drawer.findViewById<NavigationView>(R.id.nav).getHeaderView(0);
        val monthRecycler = drawerView.monthRecycler
        val monthAdapter = ListAdapterBMonth()
        monthRecycler.adapter = monthAdapter
        monthRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



        // Display sidebar on click
        val button = findViewById<ImageButton>(R.id.menuButton);
        button.setOnClickListener {
            if (drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }else{
                monthRecycler.smoothScrollToPosition(pref.getInt("month", 1) - 1)
                drawer.openDrawer(GravityCompat.START);
            }
            // Code here executes on main thread after user presses button
        }


        // Display graph on click
        drawer.findViewById<NavigationView>(R.id.nav).menu.get(1).setOnMenuItemClickListener {
            displayChart()
            true
        }


        var monthViewModel = ViewModelProvider(this).get(BMonthViewModel::class.java)




        monthViewModel.readAllData.observe(this, Observer {
            if (it.isEmpty()){
                monthViewModel.addMonth(BMonth("Premier mois"))
                pref.edit().putInt("month", 1).apply();
            }
            monthAdapter.setData(it);
        })

        // Add month on click
        drawer.findViewById<NavigationView>(R.id.nav).menu.get(0).setOnMenuItemClickListener {
            Toast.makeText(applicationContext, "Mois ajouté !", Toast.LENGTH_SHORT).show();
            monthViewModel.addMonth(BMonth("new month"))
            monthRecycler.smoothScrollToPosition(pref.getInt("month", 1) - 1)
            true
        }





        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_out_up);


        var fragement = findViewById<FragmentContainerView>(R.id.nav_host_fragment)

        drawer.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED);

        drawer.setOnTouchListener { view, ev ->
            if (ev.actionMasked == MotionEvent.ACTION_DOWN && (ev.x > drawer.x || ev.y > drawer.y)){
                drawer.closeDrawer(GravityCompat.START);
            }
            fragement.dispatchTouchEvent(ev);
            onTouchEvent(ev)
        }

        val callback = this.onBackPressedDispatcher.addCallback(this) {
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START)
            }else {
                finish();
            }
        }

        bottom.setupWithNavController(navController);

    }



    fun displayChart(){
        val intent = Intent(this, ChartActivity::class.java).apply {
        }
        startActivity(intent)
    }


}
