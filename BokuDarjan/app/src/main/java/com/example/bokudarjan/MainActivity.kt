package com.example.bokudarjan

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.core.view.forEach
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.*
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.slidingpanelayout.widget.SlidingPaneLayout.LOCK_MODE_LOCKED
import com.example.bokudarjan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Setup the binding
        val binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        val host = findViewById<ConstraintLayout>(R.id.main_layout);


        val bottom = findViewById<BottomNavigationView>(R.id.bottomNavigationView);

        val handler = Handler();


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController;


        val button = findViewById<ImageButton>(R.id.menuButton);
        val drawer = findViewById<DrawerLayout>(R.id.my_drawer_layout);


        button.setOnClickListener {
            if (drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }else{
                drawer.openDrawer(GravityCompat.START);
            }
            // Code here executes on main thread after user presses button
        }

        //TODO: renames theses variables
        val truc = drawer.findViewById<NavigationView>(R.id.nav);
        val machin = truc.getHeaderView(0);
        val bidule = machin.findViewById<LinearLayout>(R.id.buttonlay);

        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_out_up);

        for(i in 0..5){
            val child: View = layoutInflater.inflate(R.layout.fragment_month, null);
            child.findViewById<TextView>(R.id.txtMonth).text = "Mois n°" + (i+1).toString();
            child.setOnClickListener { child.startAnimation(anim); handler.postDelayed({bidule.removeView(child)},500);}
            bidule.addView(child);
        }



        val scrl = machin.findViewById<HorizontalScrollView>(R.id.scrollview);

        var onScroll = false;
        var locked = false;

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


}
