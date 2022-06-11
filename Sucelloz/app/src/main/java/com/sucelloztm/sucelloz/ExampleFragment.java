package com.sucelloztm.sucelloz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.sucelloztm.sucelloz.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class ExampleFragment extends Fragment {

    private ActivityMainBinding binding;
    private LineChart chart;

    public ExampleFragment(){
        super(R.layout.activity_main);
    }

    public LineChart getChart()
    {
        return chart;
    }

    public void setChart(LineChart chart) {
        this.chart = chart;
    }

    public void testChanges()
    {
        LineChart c = getChart();
        if(c != null)
        {
            c.getLegend().setTextColor(Color.RED);
        }
    }

    public LineChart generateRandomLineChart(View view){
        /*
          List of entries (x,y) with x,y floats
         */


        ArrayList<Entry> entries = new ArrayList<>();

        int i = 0;
        while (i < 10){
            Random r = new Random();
            Float randomX = r.nextFloat() * 15;
            Float randomY = r.nextFloat() * 30;
            entries.add(new Entry(randomX,randomY));
            i+=1;
        }

        entries.sort(new EntryXComparator());

        //entries.forEach(System.out::println);


        /*
          import chart from xml file
         */
        //LineChart chart = new LineChart(getContext());

        LineChart lChart = new LineChart(getActivity());


        LineDataSet dataSet = new LineDataSet(entries, "Label");

        LineData lineData = new LineData(dataSet);

        lChart.getLegend().setTextColor(Color.WHITE);
        lChart.getXAxis().setTextColor(Color.WHITE);
        lChart.getAxisLeft().setTextColor(Color.WHITE);
        lChart.getAxisRight().setTextColor(Color.WHITE);

       dataSet.setValueTextColor(Color.WHITE);

        lChart.setData(lineData);
        lChart.getDescription().setEnabled(false);

        FrameLayout parent = view.findViewById(R.id.parentLayout);
        parent.addView(lChart);
        return lChart;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(inflater,container,false);
        View view = inflater.inflate(R.layout.example_fragment,null);

        setChart(generateRandomLineChart(view));
        getChart().invalidate();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public ActivityMainBinding getBinding() {
        return binding;
    }

    public void setBinding(ActivityMainBinding binding) {
        this.binding = binding;
    }
}
