package com.sucelloztm.sucelloz.ui.zerobudget;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sucelloztm.sucelloz.databinding.ZeroBudgetFragmentBinding;

public class ZeroBudgetFragment extends Fragment {

    private ZeroBudgetFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ZeroBudgetViewModel zeroBudgetViewModel =
                new ViewModelProvider(this).get(ZeroBudgetViewModel.class);

        binding = ZeroBudgetFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textZeroBudget;
        zeroBudgetViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}