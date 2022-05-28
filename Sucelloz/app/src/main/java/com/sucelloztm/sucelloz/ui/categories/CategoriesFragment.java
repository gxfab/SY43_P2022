package com.sucelloztm.sucelloz.ui.categories;


import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.CategoriesFragmentBinding;
import com.sucelloztm.sucelloz.ui.dialogs.AddCategoryDialogFragment;

public class CategoriesFragment extends Fragment {

    private CategoriesFragmentBinding binding;
    private String[] dataSet;
    private static final int DATASET_COUNT = 60;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = CategoriesFragmentBinding.inflate(inflater,container,false);

        View root = binding.getRoot();
        RecyclerView recyclerView = binding.outerRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ParentAdapter adapter = new ParentAdapter(dataSet);
        recyclerView.setAdapter(adapter);

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

    private void initDataset() {
        dataSet = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            dataSet[i] = "This is element #" + i;
        }
    }



}