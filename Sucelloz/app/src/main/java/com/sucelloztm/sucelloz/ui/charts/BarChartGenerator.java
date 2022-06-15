package com.sucelloztm.sucelloz.ui.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.Savings;
import com.sucelloztm.sucelloz.ui.savings.SavingsAdapter;

import java.util.ArrayList;
import java.util.List;

public class BarChartGenerator {
    private List<Savings> currentSavingsArrayList;

    public BarChartGenerator(List<Savings> currentSavingsArrayList) {
        this.currentSavingsArrayList = currentSavingsArrayList;
    }

    private BarChart barChart;


    public BarChart getBarChart() {
        return barChart;
    }

    public void createBarChart(Context context, FrameLayout parent) {

        barChart = new BarChart(context);

        barChart.getDescription().setEnabled(false);


        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);

        Typeface tf = Typeface.create((Typeface) null,Typeface.NORMAL);

        barChart.setData(generateBarData());

        Legend l = barChart.getLegend();
        l.setEnabled(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setAxisMaximum(100f);

        barChart.getAxisRight().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);


        parent.addView(barChart);
    }

    private BarData generateBarData() {

        ArrayList<BarEntry> entries = new ArrayList<>();

        SavingsAdapter adapter = new SavingsAdapter(currentSavingsArrayList);

        final Observer<List<Savings>> entriesObserver =  new Observer<List<Savings>>() {
            @Override
            public void onChanged(List<Savings> savingsArrayList) {
                currentSavingsArrayList.clear();
                currentSavingsArrayList.addAll(savingsArrayList);
                adapter.notifyDataSetChanged();

            }
        };

        List<Float> entriesList = generateBarEntry();

        for(int i = 0; i<entriesList.size();i++){
            entries.add(new BarEntry(i,entriesList.get(i)));
        }

        BarDataSet set = new BarDataSet(entries,"Savings");
        set.setColor(Color.rgb(0, 150, 255));
        set.setValueTextColor(Color.rgb(0,0,0));
        set.setValueTextSize(10f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);


        float barWidth = 0.45f; // x2 dataset

        BarData d = new BarData(set);
        d.setBarWidth(barWidth);


        return d;

    }

    private List<Float> generateBarEntry(){

        List<Float> percentageArrayList = new ArrayList<>();
        for (Savings savings:
             this.currentSavingsArrayList) {
            percentageArrayList.add(savings.getPercentage());

        }
        return percentageArrayList;
    }

}