package com.example.sy43_projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//import android.app.Activity;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class MainActivity extends AppCompatActivity {

    PieChart chart;
    View v;

    public  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutFragment,fragment);
        fragmentTransaction.commit();
    }
    private void addToPieChart( int i1, int i2, int i3, int i4){
        PieChart chart = findViewById(R.id.piechart);
        chart.addPieSlice(new PieModel("Housing",i1, Color.parseColor("#FFA726")));
        chart.addPieSlice(new PieModel("Transportation",i2, Color.parseColor("#66BB6A")));
        chart.addPieSlice(new PieModel("Finance",i3, Color.parseColor("#EF5350")));
        chart.addPieSlice(new PieModel("Communication",i4, Color.parseColor("#2986F6")));

        chart.startAnimation();
        chart.setClickable(false);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView home,categories,overview;


        home = findViewById(R.id.home_icon);
        categories = findViewById(R.id.categories_icon);
        overview = findViewById(R.id.overview_icon);
        home_page home_page = new home_page();
        addToPieChart(40,15,25,20);
        categories cat = new categories();


        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutFragment,home_page).commit();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(home_page);

            }
        });

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(cat);
                //v = inflater.inflate(R.layout.fragment_home_page,fl,false);

            }
        });

    }

}
