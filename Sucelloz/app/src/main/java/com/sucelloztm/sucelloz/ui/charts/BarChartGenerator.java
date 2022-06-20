package com.sucelloztm.sucelloz.ui.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.FrameLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.sucelloztm.sucelloz.models.Savings;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to generate bar charts for the savings fragment
 */
public class BarChartGenerator {
    private List<Savings> currentSavingsArrayList;
    private BarChart barChart;

    /**
     * Custom constructor
     *
     * @param currentSavingsArrayList list of the current savings
     */
    public BarChartGenerator(List<Savings> currentSavingsArrayList) {
        this.currentSavingsArrayList = currentSavingsArrayList;
    }


    /**
     * Get bar chart
     *
     * @return barChart
     */
    public BarChart getBarChart() {
        return barChart;
    }


    /**
     * Create a bar chart
     *
     * @param context context
     * @param parent  frame layout
     */
    public void createBarChart(Context context, FrameLayout parent) {
        /*
            Instantiate Bar Chart
         */
        barChart = new BarChart(context);

        /*
            Hide description
         */
        barChart.getDescription().setEnabled(false);

        /*
            Hide Grid and Bar's shadow
         */
        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);

        /*
            Instantiate a type face
         */
        Typeface tf = Typeface.create((Typeface) null, Typeface.NORMAL);

        /*
            Set data to represent
         */
        barChart.setData(generateBarData());

        /*
            Hide chart's legend
         */
        Legend l = barChart.getLegend();
        l.setEnabled(false);

        /*
            Modify left axis parameters
         */
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setAxisMaximum(100f);

        /*
            Hide left axis
         */
        barChart.getAxisRight().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);
        /*
            Animate barchart on generation
         */
        barChart.animateY(1400, Easing.EaseInOutQuad);

        /*
            Add bar chart to fragment
         */
        parent.addView(barChart);


    }

    /**
     * Generates the data for the bar chart
     *
     * @return barChartData
     */
    public BarData generateBarData() {

        ArrayList<BarEntry> entries = new ArrayList<>();

        List<Float> entriesList = generateBarEntry();
        /*
            Assign Float data as bar chart entries
         */
        for (int i = 0; i < entriesList.size(); i++) {
            entries.add(new BarEntry(i, entriesList.get(i)));
        }

        /*
            Set entries
         */
        BarDataSet set = new BarDataSet(entries, "Savings");

        /*
            Set text color, size and dependency to axis
         */
        set.setColor(Color.rgb(0, 150, 255));
        set.setValueTextColor(Color.rgb(0, 0, 0));
        set.setValueTextSize(10f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);


        float barWidth = 0.45f; // x2 dataset

        BarData d = new BarData(set);
        d.setBarWidth(barWidth);


        return d;

    }

    /**
     * Generates an entry for the bar chart
     *
     * @return list of the bar chart entries
     */
    private List<Float> generateBarEntry() {

        List<Float> percentageArrayList = new ArrayList<>();
        for (Savings savings :
                this.currentSavingsArrayList) {
            percentageArrayList.add(savings.getPercentage());

        }
        return percentageArrayList;
    }

}