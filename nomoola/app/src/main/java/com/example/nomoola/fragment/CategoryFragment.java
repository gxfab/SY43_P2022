package com.example.nomoola.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.adapter.CategoryAdapter;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.fragment.dialog.AddCategoryDialog;
import com.example.nomoola.viewModel.CategoryViewModel;

public class CategoryFragment extends Fragment {

    private CategoryViewModel mCatViewModel;
    private RecyclerView categoryIncomeRecyclerView, categoryOutcomeRecyclerView, categoryProjectRecyclerView;
    private CategoryAdapter categoryIncomeAdapter, categoryOutcomeAdapter, categoryProjectAdapter;
    private AppCompatButton addCategoryButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " started");
        super.onCreateView(inflater, container, savedInstanceState);

        this.mCatViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        this.categoryIncomeAdapter = new CategoryAdapter(new CategoryAdapter.CategoryDiff(), this.getParentFragmentManager(), this.mCatViewModel);
        this.categoryOutcomeAdapter = new CategoryAdapter(new CategoryAdapter.CategoryDiff(), this.getParentFragmentManager(), this.mCatViewModel);
        this.categoryProjectAdapter = new CategoryAdapter(new CategoryAdapter.CategoryDiff(), this.getParentFragmentManager(), this.mCatViewModel);
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        this.categoryIncomeRecyclerView = view.findViewById(R.id.category_INCOME_recyclerView);
        this.categoryOutcomeRecyclerView = view.findViewById(R.id.category_OUTCOME_recyclerView);
        this.categoryProjectRecyclerView = view.findViewById(R.id.category_PROJECT_recyclerView);
        this.categoryIncomeRecyclerView.setAdapter(this.categoryIncomeAdapter);
        this.categoryOutcomeRecyclerView.setAdapter(this.categoryOutcomeAdapter);
        this.categoryProjectRecyclerView.setAdapter(this.categoryProjectAdapter);

        this.addCategoryButton = view.findViewById(R.id.add_category_button);
        this.addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCategoryDialog addCategoryDialog = new AddCategoryDialog();
                addCategoryDialog.show(getParentFragmentManager(), "Category_dialog");
            }
        });

        mCatViewModel.getCategoriesOfType(Category.CategoryType.INCOME).observe(getViewLifecycleOwner(), categories -> {
            categoryIncomeAdapter.submitList(categories);
        });

        mCatViewModel.getCategoriesOfType(Category.CategoryType.OUTCOME).observe(getViewLifecycleOwner(), categories -> {
            categoryOutcomeAdapter.submitList(categories);
        });

        mCatViewModel.getCategoriesOfType(Category.CategoryType.PROJECT).observe(getViewLifecycleOwner(), categories -> {
            categoryProjectAdapter.submitList(categories);
        });

        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " finished");


        return view;

    }
}
