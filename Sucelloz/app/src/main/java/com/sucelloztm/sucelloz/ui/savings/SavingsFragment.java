package com.sucelloztm.sucelloz.ui.savings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.sucelloztm.sucelloz.databinding.SavingsFragmentBinding;
import com.sucelloztm.sucelloz.models.Savings;
import com.sucelloztm.sucelloz.ui.charts.BarChartGenerator;
import com.sucelloztm.sucelloz.ui.dialogs.AddSavingsDialogFragment;

import java.util.ArrayList;
import java.util.List;



public class SavingsFragment extends Fragment implements LifecycleOwner {

    private SavingsFragmentBinding binding;
    private SavingsViewModel savingsViewModel;
    private List<Savings> currentSavingsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = SavingsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        savingsViewModel = new ViewModelProvider(this).get(SavingsViewModel.class);
        currentSavingsList = new ArrayList<>();
        SavingsAdapter adapter = new SavingsAdapter(currentSavingsList);

        BarChartGenerator barGen = new BarChartGenerator();

        BarChart barChart = barGen.createBarChart(getContext(),binding.frameLayoutSavings);
        barChart.invalidate();

        final Observer<List<Savings>> savingsDataSet = new Observer<List<Savings>>() {
            @Override
            public void onChanged(List<Savings> savingsList) {
                currentSavingsList.clear();
                currentSavingsList.addAll(savingsList);
                adapter.notifyDataSetChanged();
            }
        };
        savingsViewModel.getAllSavings().observe(getViewLifecycleOwner(), savingsDataSet);
        RecyclerView recyclerView = binding.savingsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addSavingsButtonSavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSavingsDialogFragment().show(getChildFragmentManager(), AddSavingsDialogFragment.TAG);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}