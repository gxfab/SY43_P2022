package com.sucelloztm.sucelloz.ui.subcategories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.SubCategoriesFragmentBinding;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.ui.dialogs.AddCategoryDialogFragment;
import com.sucelloztm.sucelloz.ui.dialogs.AddSubCategoryDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class SubCategoriesFragment extends Fragment {

    private SubCategoriesFragmentBinding binding;
    private SubCategoriesViewModel subCategoriesViewModel;
    private List<SubCategories> currentSubCategoriesList;
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
        binding = SubCategoriesFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        subCategoriesViewModel = new ViewModelProvider(this).get(SubCategoriesViewModel.class);
        currentSubCategoriesList = new ArrayList<>();
        SubCategoriesAdapter adapter = new SubCategoriesAdapter(currentSubCategoriesList);

        final Observer<List<SubCategories>> subCategoriesDataSet = new Observer<List<SubCategories>>() {
            @Override
            public void onChanged(List<SubCategories> subCategories) {
                currentSubCategoriesList.clear();
                currentSubCategoriesList.addAll(subCategories);
                adapter.notifyDataSetChanged();
            }
        };
        subCategoriesViewModel.getSubCategories().observe(getViewLifecycleOwner(),subCategoriesDataSet);
        RecyclerView recyclerView = binding.innerRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        binding.returnCategoriesButtonSubcategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SubCategoriesFragment.this).navigate(R.id.action_navigation_sub_categories_to_navigation_categories);
            }
        });
        binding.addSubcategoryButtonSubcategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSubCategoryDialogFragment().show(getChildFragmentManager(),AddCategoryDialogFragment.TAG);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}



