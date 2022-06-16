package com.example.nomoola.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.adapter.SubcategoryAdapter;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.fragment.dialog.AddSubCategoryDialog;
import com.example.nomoola.viewModel.SubcategoryViewModel;

public class SubcategoryFragment extends Fragment {

    private SubcategoryViewModel mSubcategoryViewModel;
    private RecyclerView subcategoryRecyclerView;
    private SubcategoryAdapter subcategoryAdapter;
    private AppCompatButton addSubcatButton;
    private AppCompatImageButton navBackButton;
    private TextView category_titlename, category_budget, category_budgetLeft, category_percentUsed;
    private ProgressBar progressBar;
    private Category category;

    public SubcategoryFragment(){
        super();
        this.category = new Category();
    }

    public SubcategoryFragment(Category category){
        super();
        this.category = category;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " started");
        super.onCreateView(inflater, container, savedInstanceState);
        this.mSubcategoryViewModel = new ViewModelProvider(this).get(SubcategoryViewModel.class);
        this.subcategoryAdapter = new SubcategoryAdapter(new SubcategoryAdapter.SubcategoryDiff(),
                this.getParentFragmentManager(),
                this.mSubcategoryViewModel);
        View view = inflater.inflate(R.layout.fragment_subcategory, container, false);

        this.subcategoryRecyclerView = view.findViewById(R.id.subcategory_recyclerView);
        this.subcategoryRecyclerView.setAdapter(this.subcategoryAdapter);

        this.category_titlename = view.findViewById(R.id.category_categoryTitle);
        this.category_titlename.setText(category.getM_CAT_NAME());

        this.addSubcatButton = view.findViewById(R.id.subcategory_addSubcategory_button);
        this.addSubcatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubCategoryDialog addSubCategoryDialog = new AddSubCategoryDialog(category);
                addSubCategoryDialog.show(getParentFragmentManager(), "AddSubCat_dialog");
            }
        });

        this.navBackButton = view.findViewById(R.id.subcategory_nav_back);
        this.navBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction trans = getParentFragmentManager().beginTransaction();
                trans.replace(R.id.fragmentContainerView, new CategoryFragment());
                trans.setReorderingAllowed(true);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        this.category_percentUsed = view.findViewById(R.id.subcat_cat_percentUsedView);
        this.progressBar = view.findViewById(R.id.subcat_cat_progressBar);
        this.mSubcategoryViewModel.getPercentUsedOfCategory(this.category).observe((LifecycleOwner) this.getContext(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer percentUsed) {
                if(percentUsed == null){
                    percentUsed = 0;
                }
                category_percentUsed.setText(percentUsed + "%");
                progressBar.setProgress(percentUsed);
            }
        });

        this.category_budget = view.findViewById(R.id.subcategory_category_budgetAmount);
        this.mSubcategoryViewModel.getCategoryBudget(this.category).observe((LifecycleOwner) this.getContext(), new Observer<Double>() {
            @Override
            public void onChanged(Double budget) {
                category_budget.setText(budget + "€");
            }
        });

        this.category_budgetLeft = view.findViewById(R.id.subcat_cat_budgetLeft);
        this.mSubcategoryViewModel.getBudgetLeftOf(this.category).observe((LifecycleOwner) this.getContext(), new Observer<Double>(){

            @Override
            public void onChanged(Double budgetLeft) {
                if(budgetLeft == null){
                    budgetLeft = 0.;
                }
                category_budgetLeft.setText(budgetLeft + "€");
            }
        });

        mSubcategoryViewModel.getSubCategoriesOf(category.getM_CAT_ID()).observe(getViewLifecycleOwner(), subCategories -> {
            subcategoryAdapter.submitList(subCategories);
        });

        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " finished");
        return view;
    }
}
