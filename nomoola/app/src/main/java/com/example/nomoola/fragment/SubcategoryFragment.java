package com.example.nomoola.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
    private TextView subcategory_titlename;
    private Category category;

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
        this.subcategoryAdapter = new SubcategoryAdapter(new SubcategoryAdapter.SubcategoryDiff(), this.getParentFragmentManager());
        View view = inflater.inflate(R.layout.fragment_subcategory, container, false);

        this.subcategoryRecyclerView = view.findViewById(R.id.subcategory_recyclerView);
        this.subcategoryRecyclerView.setAdapter(this.subcategoryAdapter);

        this.subcategory_titlename = view.findViewById(R.id.subcategory_categoryTitle);
        this.subcategory_titlename.setText(category.getM_CAT_NAME());

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
                trans.replace(R.id.fragment_container, new CategoryFragment());
                trans.addToBackStack(null);
                trans.commit();
            }
        });


        mSubcategoryViewModel.getSubCategoriesOf(category.getM_CAT_ID()).observe(getViewLifecycleOwner(), subCategories -> {
            subcategoryAdapter.submitList(subCategories);
        });

        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " finished");
        return view;
    }
}
