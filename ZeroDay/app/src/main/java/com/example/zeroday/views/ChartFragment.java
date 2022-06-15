package com.example.zeroday.views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.zeroday.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class ChartFragment extends Fragment {

    private PieChart pieChart;
    private RadarChart radarChart;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        pieChart = view.findViewById(R.id.piechart);
        configPieChart();
        loadPieChart();

        radarChart = view.findViewById(R.id.radarchart);
        configRadarChart();
        loadRadarChart();


        return view;
    }

    private void configPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setRotationEnabled(false);
    }

    private void configRadarChart(){

        radarChart.setRotationEnabled(false);
        radarChart.getDescription().setEnabled(false);
    }

    private void loadRadarChart(){

        ArrayList<RadarEntry> currentDataVals = new ArrayList<>();
        currentDataVals.add(new RadarEntry(3));
        currentDataVals.add(new RadarEntry(7));
        currentDataVals.add(new RadarEntry(2));
        currentDataVals.add(new RadarEntry(5));
        currentDataVals.add(new RadarEntry(3));
        currentDataVals.add(new RadarEntry(1));

        ArrayList<RadarEntry> lastDataVals = new ArrayList<>();
        lastDataVals.add(new RadarEntry(2));
        lastDataVals.add(new RadarEntry(6));
        lastDataVals.add(new RadarEntry(3));
        lastDataVals.add(new RadarEntry(4));
        lastDataVals.add(new RadarEntry(6));
        lastDataVals.add(new RadarEntry(4));

        RadarDataSet currentDataSet = new RadarDataSet(currentDataVals, "Current expenses");
        RadarDataSet lastDataSet = new RadarDataSet(lastDataVals, "Last cycle expenses");

        currentDataSet.setFillColor(ContextCompat.getColor(getContext(), R.color.zero_purple));
        currentDataSet.setColor(ContextCompat.getColor(getContext(), R.color.zero_purple));
        currentDataSet.setDrawFilled(true);

        lastDataSet.setFillColor(ContextCompat.getColor(getContext(), R.color.zero_pink0));
        lastDataSet.setColor(ContextCompat.getColor(getContext(), R.color.zero_pink0));
        lastDataSet.setDrawFilled(true);

        RadarData data = new RadarData();
        data.addDataSet(currentDataSet);
        data.addDataSet(lastDataSet);

        String[] labels = {"Hobbies","Health","Transports","Food","House","Custom"};
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        radarChart.setData(data);
        radarChart.invalidate();
        radarChart.animateX(800, Easing.EaseInOutQuad);
        radarChart.animateY(800, Easing.EaseInOutQuad);


    }




    private void loadPieChart(){

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.2f, "Hobbies"));
        entries.add(new PieEntry(0.1f, "Health"));
        entries.add(new PieEntry(0.1f, "Transports"));
        entries.add(new PieEntry(0.05f, "Food"));
        entries.add(new PieEntry(0.15f, "House"));
        entries.add(new PieEntry(0.4f, "Custom"));

        final int[] MY_COLORS = {ContextCompat.getColor(getContext(), R.color.hobbies_color)
                ,ContextCompat.getColor(getContext(), R.color.health_color)
                ,ContextCompat.getColor(getContext(), R.color.transports_color)
                ,ContextCompat.getColor(getContext(), R.color.food_color)
                ,ContextCompat.getColor(getContext(), R.color.house_color)
                ,ContextCompat.getColor(getContext(), R.color.custom_expense_color)};

        ArrayList<Integer> colors = new ArrayList<>();
        for(int c: MY_COLORS) colors.add(c);

        PieDataSet pieDataSet = new PieDataSet(entries, "Current expenses");
        pieDataSet.setColors(colors);

        PieData data = new PieData(pieDataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.animateY(800, Easing.EaseInOutQuad);

    }
}
