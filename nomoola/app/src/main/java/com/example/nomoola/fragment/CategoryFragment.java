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
import com.example.nomoola.fragment.dialog.AddCategoryDialog;
import com.example.nomoola.fragment.dialog.EditCategoryDialog;
import com.example.nomoola.viewModel.CategoryViewModel;

public class CategoryFragment extends Fragment {

    private CategoryViewModel mCatViewModel;
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private AppCompatButton addCategoryButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " started");
        super.onCreateView(inflater, container, savedInstanceState);
        this.mCatViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        this.categoryAdapter = new CategoryAdapter(new CategoryAdapter.CategoryDiff(), this.getParentFragmentManager());
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        this.categoryRecyclerView = view.findViewById(R.id.category_recyclerView);
        this.categoryRecyclerView.setAdapter(this.categoryAdapter);

        this.addCategoryButton = view.findViewById(R.id.add_category_button);
        this.addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCategoryDialog addCategoryDialog = new AddCategoryDialog();
                addCategoryDialog.show(getParentFragmentManager(), "Category_dialog");
            }
        });



        mCatViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            categoryAdapter.submitList(categories);
        });

        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " finished");
        return view;
    }
}
