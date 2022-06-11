package com.sucelloztm.sucelloz;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.mikephil.charting.charts.LineChart;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Test bar Chart");

        ExampleFragment test1 = new ExampleFragment();
        ExampleFragment test2 = new ExampleFragment();

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .replace(R.id.fragment_container_view, test1, null)
                    .replace(R.id.fragment_container_view2,test2, null)
                    .setReorderingAllowed(true)
                    .commit();

            test1.testChanges();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, test1, null)
                    .commit();

        }



    }
}