package com.sucelloztm.sucelloz.ui.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.FrameLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to generate pie charts for the subcategories fragment
 */
public class PieChartSubCategoriesGenerator {

    private PieChart pieChart;
    private List<SubCategoriesWithInfrequentSum> subCategoriesWithInfrequentSumList;


    /**
     * Custom constructor
     *
     * @param subCategoriesWithInfrequentSumList list of subcategories
     */
    public PieChartSubCategoriesGenerator(List<SubCategoriesWithInfrequentSum> subCategoriesWithInfrequentSumList) {
        this.subCategoriesWithInfrequentSumList = subCategoriesWithInfrequentSumList;
    }

    /**
     * Get pie chart
     *
     * @return pie chart
     */
    public PieChart getPieChart() {
        return pieChart;
    }

    /**
     * Create pie chart
     *
     * @param context context
     * @param parent  frame layout
     * @return created pie chart
     */
    public PieChart createPieChart(Context context, FrameLayout parent) {

        pieChart = new PieChart(context);
        pieChart.getDescription().setEnabled(false);

        Typeface tf = Typeface.create((Typeface) null, Typeface.NORMAL);

        pieChart.setCenterTextTypeface(tf);
        //pieChart.setCenterText(generateCenterText());
        pieChart.setCenterTextSize(10f);
        pieChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        pieChart.setHoleRadius(45f);
        pieChart.setTransparentCircleRadius(50f);

        pieChart.setUsePercentValues(true);


        //enabling the user to rotate the chart, default true
        pieChart.setRotationEnabled(true);
        //adding friction when rotating the pie chart
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        //setting the first entry start from right hand side, default starting from top
        pieChart.setRotationAngle(0);

        //highlight the entry when it is tapped, default true if not set
        pieChart.setHighlightPerTapEnabled(true);
        //adding animation so the entries pop up from 0 degree
        pieChart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = pieChart.getLegend();
        l.setEnabled(false);

        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);

        l.setOrientation(Legend.LegendOrientation.VERTICAL);

        l.setDrawInside(false);

        pieChart.setData(generatePieData());

        parent.addView(pieChart);

        return pieChart;

    }


    /**
     * Generates data for the pie chart
     *
     * @return pie data
     */
    public PieData generatePieData() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        Map<String, Integer> subCategoriesWithInfrequentSumMap = generatePieEntry();
        String label = "type";

        for (String type : subCategoriesWithInfrequentSumMap.keySet()) {
            pieEntries.add(new PieEntry(subCategoriesWithInfrequentSumMap.get(type).intValue(), type));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);

        PieData pieData = new PieData(pieDataSet);

        pieData.setValueFormatter(new PercentFormatter());

        return pieData;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("XXX");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }

    /**
     * Generates a pie chart entry
     *
     * @return map of the entries
     */
    public Map<String, Integer> generatePieEntry() {
        Map<String, Integer> subCategoriesWithInfrequentSumMap = new HashMap<>();
        for (SubCategoriesWithInfrequentSum subCategory :
                this.subCategoriesWithInfrequentSumList) {
            subCategoriesWithInfrequentSumMap.put(subCategory.getNameOfSubCategory(), subCategory.getSumOfInfrequent());
        }
        return subCategoriesWithInfrequentSumMap;
    }
}
