package com.example.sy43;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class add_expense extends Fragment {
    protected EditText et_name, et_amount;
    protected Button addBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
       /* et_amount = view.findViewById(R.id.add_expense_amount);
        et_name = view.findViewById(R.id.add_expense_name);
        addBtn= view.findViewById(R.id.add_expense_btn);*/
        return view;
    }
}