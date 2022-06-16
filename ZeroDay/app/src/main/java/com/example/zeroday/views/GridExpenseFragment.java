package com.example.zeroday.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.zeroday.R;
import com.example.zeroday.models.ExpenseCategory;
import com.example.zeroday.services.ExpenseCategoryService;

import java.util.List;

public class GridExpenseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense_grid, container, false);

        ExpenseCategoryService service = new ExpenseCategoryService(getActivity());
        List<ExpenseCategory> categoriesList = service.getAll();

        GridView gridView = view.findViewById(R.id.grid);


        gridView.setAdapter(new GridExpenseAdapter(getActivity(),categoriesList));


        return view;

    }
}