package com.example.sy43;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.SubCategory;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class chart2 extends Fragment {

    ArrayList<BarEntry> expenses = new ArrayList<>();
    BarDataSet barDataSet;
    BarData barData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart1, container, false);

        AppDatabase db = AppDatabase.getInstance(getContext());
        List<SubCategory> list = db.subCategoryDao().getAll();

        BarChart barChart = view.findViewById(R.id.chart1);

        for (int i=0; i<list.size(); i++){
            expenses.add( new BarEntry(list.get(i).id, (int)list.get(i).envelope));
        }
        barDataSet = new BarDataSet(expenses, "Expense tracker by subcategory");
        barDataSet.setColor(ColorTemplate.MATERIAL_COLORS[3]);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Expenses");
        barChart.animateY(2000);

        return view;
    }
}