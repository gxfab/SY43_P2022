package com.example.nomoola.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.adapter.CategoryAdapter;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.viewModel.CategoryViewModel;
import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryViewModel mCatViewModel;
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " started");
        super.onCreateView(inflater, container, savedInstanceState);

        this.mCatViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        this.categoryAdapter = new CategoryAdapter(new CategoryAdapter.CategoryDiff(), this.getParentFragmentManager());
        this.categoryRecyclerView = view.findViewById(R.id.category_recyclerView);

        this.categoryRecyclerView.setAdapter(this.categoryAdapter);


        mCatViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            categoryAdapter.submitList(categories);
        });

        mCatViewModel.insert(new Category("Health", 50));
        mCatViewModel.insert(new Category("Hobby", 50));
        mCatViewModel.insert(new Category("Party", 100));
        mCatViewModel.insert(new Category("Subscription", 35));
        mCatViewModel.insert(new Category("School", 20));

        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " finished");


        return view;

    }
}
