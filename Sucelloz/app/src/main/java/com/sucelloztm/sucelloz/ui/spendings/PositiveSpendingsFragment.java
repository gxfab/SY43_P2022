package com.sucelloztm.sucelloz.ui.spendings;



import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.sucelloztm.sucelloz.databinding.PositiveSpendingsFragmentBinding;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.ui.dialogs.AddSpendingDialogFragment;


import java.util.ArrayList;
import java.util.List;


public class PositiveSpendingsFragment extends Fragment {
    private PositiveSpendingsFragmentBinding binding;
    private SpendingsViewModel spendingsViewModel;
    private List<Spendings> currentPositiveSpendingsList;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public PieChart createPieChart()
    {

        Context context = getContext();
        PieChart pieChart = new PieChart(context);
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
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        pieChart.setData(spendingsViewModel.generatePieData(tf));

        FrameLayout parent = binding.frameLayoutPositiveSpendings;
        parent.addView(pieChart);

        return pieChart;

    }


    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Revenues\nQuarters 2015");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=PositiveSpendingsFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        spendingsViewModel = new ViewModelProvider(this).get(SpendingsViewModel.class);
        currentPositiveSpendingsList = new ArrayList<>();
        PositiveSpendingsAdapter adapter = new PositiveSpendingsAdapter(currentPositiveSpendingsList);

        PieChart pieChart = createPieChart();
        pieChart.invalidate();

        final Observer<List<InfrequentExpensesAndIncome>> positiveSpendingsDataSet = new Observer<List<InfrequentExpensesAndIncome>>() {
            @Override
            public void onChanged(List<InfrequentExpensesAndIncome> infrequentExpensesAndIncomes) {
                currentPositiveSpendingsList.clear();
                infrequentExpensesAndIncomes.forEach(spending -> {
                    String subCategoryName = spendingsViewModel.getSubCategoryNameWithId(spending.getSubCategoriesId());
                    currentPositiveSpendingsList.add(new Spendings(spending.getName(),
                            spending.getAmount(),
                            spending.getDate(),
                            subCategoryName
                            )
                    );
                });
                adapter.notifyDataSetChanged();
            }


        };
        spendingsViewModel.getAllPositiveSpendings().observe(getViewLifecycleOwner(),positiveSpendingsDataSet);
        recyclerView = binding.spendingsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addSpendingButtonSpendings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSpendingDialogFragment().show(getChildFragmentManager(),AddSpendingDialogFragment.TAG);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    
}