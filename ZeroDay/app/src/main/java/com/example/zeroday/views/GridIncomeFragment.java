package com.example.zeroday.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.zeroday.R;
import com.example.zeroday.models.IncomeCategory;
import com.example.zeroday.services.IncomeCategoryService;

import java.util.List;

public class GridIncomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_income_grid, container, false);

        IncomeCategoryService service = new IncomeCategoryService(getActivity());
        List<IncomeCategory> categoriesList = service.getAll();

        GridView gridView = view.findViewById(R.id.grid);


        gridView.setAdapter(new GridIncomeAdapter(getActivity(),categoriesList));


        return view;

    }
}