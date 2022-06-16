package com.example.sy43;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Expenses;
import com.example.sy43.database.SubCategory;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class chart2 extends Fragment {


    BarDataSet barDataSet;
    BarData barData;
    Double total_expenses_bycat = 0.0;
    List<Expenses> expensesbysubcat;
    List <String> labels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart2, container, false);

        AppDatabase db = AppDatabase.getInstance(getContext());
        List<SubCategory> list = db.subCategoryDao().getAll();

        BarChart barChart = view.findViewById(R.id.chart2);
        ArrayList<BarEntry> expenses = new ArrayList<>();

        labels = new ArrayList<>();
        for (int i =0 ; i < list.size(); i++ ){
            expensesbysubcat = new ArrayList<>();
            expensesbysubcat = db.expensesDao().findBySubCategory(list.get(i).id);
            total_expenses_bycat = 0.0;
            for (int j = 0; j<expensesbysubcat.size(); j++){
                total_expenses_bycat+= expensesbysubcat.get(j).value;
            }


            if(total_expenses_bycat.intValue()!=0){
                expenses.add( new BarEntry(list.get(i).id, total_expenses_bycat.intValue()));
                labels.add(list.get(i).name);
            }
        }

        barDataSet = new BarDataSet(expenses, "Expenses by Subcategories");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        barData = new BarData(barDataSet);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        //barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(2000);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        return view;
    }
}