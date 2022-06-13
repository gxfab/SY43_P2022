package com.example.budgetzeroapp.fragment.cashflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budgetzeroapp.R;

public class CashFlowStableFragment extends Fragment {

    public CashFlowStableFragment() {
        // Required empty public constructor
    }

    public static CashFlowStableFragment newInstance(String param1, String param2) {
        CashFlowStableFragment fragment = new CashFlowStableFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cash_flow_stable, container, false);
    }
}