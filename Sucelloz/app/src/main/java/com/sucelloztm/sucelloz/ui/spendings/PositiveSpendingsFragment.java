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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=PositiveSpendingsFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        spendingsViewModel = new ViewModelProvider(this).get(SpendingsViewModel.class);
        currentPositiveSpendingsList = new ArrayList<>();
        PositiveSpendingsAdapter adapter = new PositiveSpendingsAdapter(currentPositiveSpendingsList);
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