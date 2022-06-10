package com.sucelloztm.sucelloz.ui.categories;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.CategoriesFragmentBinding;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.ui.dialogs.AddCategoryDialogFragment;
import com.sucelloztm.sucelloz.ui.miscellaneous.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment implements LifecycleOwner {

    private CategoriesFragmentBinding binding;
    private CategoriesViewModel categoriesViewModel;
    private List<Categories> currentCategoriesList;
    private RecyclerView recyclerView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CategoriesFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        categoriesViewModel = new ViewModelProvider(this).get(CategoriesViewModel.class);
        currentCategoriesList = new ArrayList<>();
        CategoriesAdapter adapter=new CategoriesAdapter(currentCategoriesList);

        final Observer<List<Categories>> categoriesDataSet= new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categoriesList) {
                currentCategoriesList.clear();
                currentCategoriesList.addAll(categoriesList);
                adapter.notifyDataSetChanged();
            }
        };
        categoriesViewModel.getAllCategories().observe(getViewLifecycleOwner(),categoriesDataSet);
        recyclerView = binding.outerRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                TextView currentCategoryTextView=(TextView) v.findViewById(R.id.text_view_categories);
                String currentCategoryName = currentCategoryTextView.getText().toString();
                Categories currentCategory = categoriesViewModel.getCategoryByName(currentCategoryName);
                categoriesViewModel.setCurrentCategory(currentCategory);
                NavHostFragment.findNavController(CategoriesFragment.this).navigate(R.id.action_navigation_categories_to_navigation_sub_categories);
                //Log.d("CategoriesFragment",currentCategoryName);
            }
        });


        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        binding.returnHomeButtonCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CategoriesFragment.this).navigate(R.id.action_navigation_categories_to_navigation_home);
            }
        });
        binding.addCategoryButtonCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddCategoryDialogFragment().show(getChildFragmentManager(),AddCategoryDialogFragment.TAG);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}