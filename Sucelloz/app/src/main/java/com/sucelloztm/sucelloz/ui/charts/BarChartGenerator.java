package com.sucelloztm.sucelloz.ui.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BarChartGenerator {

    BarChart barChart;

    public BarChart getBarChart() {
        return barChart;
    }

    public BarChart createBarChart(Context context, FrameLayout parent) {

        barChart = new BarChart(context);

        barChart.getDescription().setEnabled(false);


        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);

        Typeface tf = Typeface.create((Typeface) null,Typeface.NORMAL);

        barChart.setData(generateBarData());

        Legend l = barChart.getLegend();
        l.setTypeface(tf);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        barChart.getAxisRight().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);


        parent.addView(barChart);
        return barChart;

    }

    private BarData generateBarData() {

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();

        int count = 12;

        for (int index = 0; index < count; index++) {
            entries1.add(new BarEntry(0, getRandom(25, 25)));

            // stacked
            entries2.add(new BarEntry(0, new float[]{getRandom(13, 12), getRandom(13, 12)}));
        }

        BarDataSet set1 = new BarDataSet(entries1, "Bar 1");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(60, 220, 78));
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set2 = new BarDataSet(entries2, "");
        set2.setStackLabels(new String[]{"Stack 1", "Stack 2"});
        set2.setColors(Color.rgb(61, 165, 255), Color.rgb(23, 197, 255));
        set2.setValueTextColor(Color.rgb(61, 165, 255));
        set2.setValueTextSize(10f);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1, set2);
        d.setBarWidth(barWidth);

        // make this BarData object grouped
        d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;

    }

    protected float getRandom(float range, float start) {
        return (float) (Math.random() * range) + start;
    }
}