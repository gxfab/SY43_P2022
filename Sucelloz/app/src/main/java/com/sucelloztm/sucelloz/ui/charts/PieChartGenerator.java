package com.sucelloztm.sucelloz.ui.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.FrameLayout;



import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartGenerator {

    private PieChart pieChart;

    public PieChart getPieChart() {
        return pieChart;
    }

    public PieChart createPieChart(Context context, FrameLayout parent)
    {

        pieChart = new PieChart(context);
        pieChart.getDescription().setEnabled(false);


        Typeface tf = Typeface.create((Typeface) null,Typeface.NORMAL);

        pieChart.setCenterTextTypeface(tf);
        pieChart.setCenterText(generateCenterText());
        pieChart.setCenterTextSize(10f);
        pieChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        pieChart.setHoleRadius(45f);
        pieChart.setTransparentCircleRadius(50f);

        Legend l = pieChart.getLegend();
        l.setEnabled(false);

        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);

        l.setOrientation(Legend.LegendOrientation.VERTICAL);

        l.setDrawInside(false);



        pieChart.setData(generatePieData(tf));

        parent.addView(pieChart);

        return pieChart;

    }



    private PieData generatePieData(Typeface tf) {

        int count = 4;

        ArrayList<PieEntry> entries1 = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            entries1.add(new PieEntry((float) ((Math.random() * 60) + 40), "Quarter " + (i+1)));
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Quarterly Revenues 2015");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Revenues\nQuarters 2015");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }
}
