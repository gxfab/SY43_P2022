package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Expenses;
import com.example.sy43.database.SubCategory;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;

import org.eazegraph.lib.charts.BaseBarChart;

import java.util.ArrayList;
import java.util.List;

public class Overview extends AppCompatActivity{


    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private OverviewFragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        toCategories();
        toHome();

        tabLayout = findViewById(R.id.chartTabLayout);
        viewPager2= findViewById(R.id.chartsContainer);

        tabLayout.addTab(tabLayout.newTab().setText("Category tracker"));
        tabLayout.addTab(tabLayout.newTab().setText("SubCategory tracker"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter= new OverviewFragmentAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(fragmentAdapter);

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

    private void toCategories(){
        ImageView categories = findViewById(R.id.categories_icon);
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Overview.this, Categories.class));
            }
        });
    }

    private void toHome(){
        ImageView home = findViewById(R.id.home_icon);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Overview.this, Home.class));
            }
        });
    }
}