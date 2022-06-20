package com.sucelloztm.sucelloz.ui.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.github.mikephil.charting.animation.Easing;
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

/**
 * class to generate bar charts for the savings fragment
 */
public class BarChartGenerator {
    private List<Savings> currentSavingsArrayList;
    private BarChart barChart;

    /**
     * custom constructor
     * @param currentSavingsArrayList list of the current savings
     */
    public BarChartGenerator(List<Savings> currentSavingsArrayList) {
        this.currentSavingsArrayList = currentSavingsArrayList;
    }


    /**
     * getter
     * @return bar chart
     */
    public BarChart getBarChart() {
        return barChart;
    }


    /**
     * creates the bar chart
     * @param context context
     * @param parent frame layout
     */
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
        barChart.animateY(1400, Easing.EaseInOutQuad);

        parent.addView(barChart);



    }

    /**
     * generates the data for the bar chart
     * @return bar chart data
     */
    public BarData generateBarData() {

        ArrayList<BarEntry> entries = new ArrayList<>();

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

    /**
     * generates an entry for the bar chart
     * @return list of the bar chart entries
     */
    private List<Float> generateBarEntry(){

        List<Float> percentageArrayList = new ArrayList<>();
        for (Savings savings:
             this.currentSavingsArrayList) {
            percentageArrayList.add(savings.getPercentage());

        }
        return percentageArrayList;
    }

}