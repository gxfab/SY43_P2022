package com.example.sy43;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Category;
import com.example.sy43.database.Expenses;
import com.example.sy43.database.SubCategory;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class chart1 extends Fragment {

    PieDataSet pieDataSet;
    PieData pieData;
    List<Expenses> expensesbycat;
    Double total_expenses_bycat = 0.0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart1, container, false);

        AppDatabase db = AppDatabase.getInstance(getContext());
        List<Category> list = db.categoryDao().getAll();

        PieChart pieChart = view.findViewById(R.id.chart1);
        ArrayList<PieEntry> expenses = new ArrayList<>();

        for (int i=0; i<list.size(); i++){

            expensesbycat = new ArrayList<>();
            expensesbycat = db.expensesDao().findByCategory(list.get(i).id);
            for (int j = 0; j<expensesbycat.size(); j++){
                total_expenses_bycat+=expensesbycat.get(j).value;
            }
            if(total_expenses_bycat.intValue()!=0){
                expenses.add( new PieEntry(total_expenses_bycat.intValue(), list.get(i).name));
            }
            total_expenses_bycat = 0.0;
        }

        pieDataSet = new PieDataSet(expenses, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setText("");
        pieChart.setCenterText("Expenses per categories");
        pieChart.animate();


        return view;
    }
}