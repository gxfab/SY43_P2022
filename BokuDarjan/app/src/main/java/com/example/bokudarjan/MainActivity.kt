package com.example.bokudarjan

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.bokudarjan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.delay


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val bottom = findViewById<BottomNavigationView>(R.id.bottomNavigationView);

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController;

        bottom.setupWithNavController(navController);

        val button = findViewById<ImageButton>(R.id.menuButton);
        val drawer = findViewById<DrawerLayout>(R.id.my_drawer_layout);

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        button.setOnClickListener {
            if (drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }else{
                drawer.openDrawer(GravityCompat.START);
            }
            // Code here executes on main thread after user presses button
        }

        val truc = drawer.findViewById<NavigationView>(R.id.nav);
        val machin = truc.getHeaderView(0);
        val bidule = machin.findViewById<LinearLayout>(R.id.buttonlay);

        for(i in 0..5){
            val child: View = layoutInflater.inflate(R.layout.fragment_month, null);
            child.findViewById<TextView>(R.id.txt).text = "Mois n°" + (i+1).toString();
            bidule.addView(child);
        }

        val scrl = machin.findViewById<HorizontalScrollView>(R.id.scrollview);
        scrl.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            Toast.makeText(applicationContext, "Cringe", Toast.LENGTH_SHORT).show()
        }






        Log.i("[CRINGE]", bidule.toString());
    }
}
