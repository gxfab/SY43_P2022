package com.example.zeroday.test;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zeroday.R;
import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.repositories.IncomeCategoryRepository;
import com.example.zeroday.services.IncomeCategoryService;

/**
 * A fragment representing a list of Items.
 */
public class IncomesCategoryFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 3;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public IncomesCategoryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static IncomesCategoryFragment newInstance(int columnCount) {
        IncomesCategoryFragment fragment = new IncomesCategoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // IncomeCategoryRepository incomeCategoryRepository = new IncomeCategoryRepository(new DbHelper(this.getContext()).getWritableDatabase());
        IncomeCategoryService incomeCategoryService = new IncomeCategoryService(this.getContext());
        View view = inflater.inflate(R.layout.fragment_incomes_category_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount)); 
            }
            recyclerView.setAdapter(new IncomesCategoryViewAdapter(incomeCategoryService.getAll()));
        }
        return view;
    }


}