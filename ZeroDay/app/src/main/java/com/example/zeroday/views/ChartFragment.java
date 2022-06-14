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
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class ChartFragment extends Fragment {

    private PieChart pieChart;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        pieChart = view.findViewById(R.id.piechart);
        configPieChart();
        loadPieChart();

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
