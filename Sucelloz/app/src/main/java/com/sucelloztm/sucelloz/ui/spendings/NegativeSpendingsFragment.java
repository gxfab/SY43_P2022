package com.sucelloztm.sucelloz.ui.spendings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.sucelloztm.sucelloz.databinding.NegativeSpendingsFragmentBinding;
import com.sucelloztm.sucelloz.databinding.PositiveSpendingsFragmentBinding;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.ui.charts.PieChartGenerator;
import com.sucelloztm.sucelloz.ui.dialogs.AddSpendingDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class NegativeSpendingsFragment extends Fragment {
    private NegativeSpendingsFragmentBinding binding;
    private SpendingsViewModel spendingsViewModel;
    private List<Spendings> currentNegativeSpendingsList;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=NegativeSpendingsFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        spendingsViewModel = new ViewModelProvider(this).get(SpendingsViewModel.class);
        currentNegativeSpendingsList = new ArrayList<>();
        NegativeSpendingsAdapter adapter = new NegativeSpendingsAdapter(currentNegativeSpendingsList);

        PieChartGenerator pieGen = new PieChartGenerator();

        PieChart pieChart = pieGen.createPieChart(getContext(), binding.frameLayoutNegativeSpendings);
        pieChart.invalidate();

        final Observer<List<InfrequentExpensesAndIncome>> negativeSpendingsDataSet = new Observer<List<InfrequentExpensesAndIncome>>() {
            @Override
            public void onChanged(List<InfrequentExpensesAndIncome> infrequentExpensesAndIncomes) {
                currentNegativeSpendingsList.clear();
                infrequentExpensesAndIncomes.forEach(spending -> {
                    String subCategoryName = spendingsViewModel.getSubCategoryNameWithId(spending.getSubCategoriesId());
                    currentNegativeSpendingsList.add(new Spendings(spending.getName(),
                                    spending.getAmount(),
                                    spending.getDate(),
                                    subCategoryName
                            )
                    );
                });
                adapter.notifyDataSetChanged();
            }
        };
        spendingsViewModel.getAllNegativeSpendings().observe(getViewLifecycleOwner(),negativeSpendingsDataSet);
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
