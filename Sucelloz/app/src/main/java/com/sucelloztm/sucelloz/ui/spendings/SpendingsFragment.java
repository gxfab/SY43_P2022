package com.sucelloztm.sucelloz.ui.spendings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.databinding.SpendingsFragmentBinding;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class SpendingsFragment extends Fragment {

    private SpendingsFragmentBinding binding;
    private ArrayList<Spendings> dataSet;
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
        binding = SpendingsFragmentBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        RecyclerView recyclerView = binding.spendingsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SpendingsAdapter adapter = new SpendingsAdapter(dataSet);
        recyclerView.setAdapter(adapter);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private String getDate() {
        Date actualDate = new Date();

        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT);
        return shortDateFormat.format(actualDate);
    }

    private void initDataset() {
        dataSet = new ArrayList<>();
        String name;
        String date;
        String amount;
        Spendings spendings;
        for (int i = 0; i < DATASET_COUNT; i++) {
            name = "Spendings #" + i;
            date =getDate();
            amount = i+"€";
            spendings=new Spendings(name,date,amount);
            dataSet.add(spendings);
        }
    }
}