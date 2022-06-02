package com.example.sy43;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Home extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MyFragmentAdapter adapter;
    PieChart pieChart;

    //TODO lezm e3mel nafes l chi michen popups l subcategories
    // w zabit l subcategories ma3 l icons,
    // w zabit l switch ben categories w home,
    // w chakel l home mech zabit l scroll w l tabs

    //Lezm chouf lech l spinners ma 3am ye2ro l data.. check l arraylist adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toCategories();
        addToPieChart(40,25,15,20);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2= findViewById(R.id.child_fragment_container);

        tabLayout.addTab(tabLayout.newTab().setText("Latest Activity"));
        tabLayout.addTab(tabLayout.newTab().setText("Add new expense"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter= new MyFragmentAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

    private void addToPieChart(int i1, int i2, int i3, int i4){
        PieChart chart = findViewById(R.id.piechart);
        chart.addPieSlice(new PieModel("Housing",i1, Color.parseColor("#FFA726")));
        chart.addPieSlice(new PieModel("Transportation",i2, Color.parseColor("#66BB6A")));
        chart.addPieSlice(new PieModel("Finance",i3, Color.parseColor("#EF5350")));
        chart.addPieSlice(new PieModel("Communication",i4, Color.parseColor("#2986F6")));

        chart.startAnimation();
        chart.setClickable(false);

    }

    private void toCategories(){
        ImageView categories_icon = findViewById(R.id.categories_icon);
        categories_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Categories.class));
            }
        });
    }
}