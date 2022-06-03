package com.sucelloztm.sucelloz.ui.savings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.databinding.SavingsFragmentBinding;
import com.sucelloztm.sucelloz.ui.dialogs.AddSpendingDialogFragment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class SavingsFragment extends Fragment {

    private SavingsFragmentBinding binding;
    private ArrayList<Savings> dataSet;
    private static final int DATASET_COUNT = 60;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = SavingsFragmentBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        RecyclerView recyclerView = binding.savingsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SavingsAdapter adapter = new SavingsAdapter(dataSet);
        recyclerView.setAdapter(adapter);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addSavingsButtonSavings.setOnClickListener(new View.OnClickListener() {
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
    //TODO : put this method in the MVP Pattern
    private String getDate() {
        Date actualDate = new Date();

        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT);
        return shortDateFormat.format(actualDate);
    }
//TODO : Put this method in the MVP Pattern

    private void initDataset() {
        dataSet = new ArrayList<>();
        String name;
        String deadline;
        int initialAmount;
        int reachedAmount;
        Savings savings;
        for (int i = 0; i < DATASET_COUNT; i++) {
            name = "Savings #" + i;
            deadline = getDate();
            initialAmount=DATASET_COUNT;
            reachedAmount=i;
            savings=new Savings(name,initialAmount,reachedAmount,deadline);
            dataSet.add(savings);
        }
    }
}