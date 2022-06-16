package com.sucelloztm.sucelloz.ui.zerobudget;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.ZeroBudgetFragmentBinding;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.ui.categories.CategoriesAdapter;
import com.sucelloztm.sucelloz.ui.categories.CategoriesFragment;
import com.sucelloztm.sucelloz.ui.miscellaneous.ItemClickSupport;
import com.sucelloztm.sucelloz.ui.subcategories.SubCategoriesAdapter;

import java.util.ArrayList;


public class ZeroBudgetFragment extends Fragment {

    private ZeroBudgetFragmentBinding binding;
    private ArrayList<SubCategories> zeroBudgetSubCategoriesList;
    private ZeroBudgetViewModel zeroBudgetViewModel;
    private TextView zeroBudgetTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zeroBudgetViewModel =
                new ViewModelProvider(this).get(ZeroBudgetViewModel.class);

        binding = ZeroBudgetFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        zeroBudgetTextView = binding.zeroBudgetTextView;

        RecyclerView recyclerView = binding.zeroBudgetRecyclerView;

        zeroBudgetSubCategoriesList = new ArrayList<>();

        String[] zeroBudgetNameList = new String[]{"Incomes", "Bills", "Envelopes",
                "Sinking Funds", "Extra debt", "Extra Savings"};

        for (String name:zeroBudgetNameList
             ) {
            zeroBudgetSubCategoriesList.add(zeroBudgetViewModel.getSubCategoryByName(name));
        }


        SubCategoriesAdapter subCategoriesAdapter = new SubCategoriesAdapter(zeroBudgetSubCategoriesList);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(subCategoriesAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                TextView currentZeroBudgetCategoryTextView=(TextView) v.findViewById(R.id.text_view_subcategories);
                String currentZeroBudgetCategoryName = currentZeroBudgetCategoryTextView.getText().toString();
                SubCategories currentSubCategory = zeroBudgetViewModel.getSubCategoryByName(currentZeroBudgetCategoryName);
                zeroBudgetViewModel.setCurrentSubCategory(currentSubCategory);
                NavHostFragment.findNavController(ZeroBudgetFragment.this).navigate(R.id.action_navigation_zero_budget_to_navigation_sub_zero_budget);
                //Log.d("CategoriesFragment",currentCategoryName);
            }
        });

//        int zeroBudgetResult = zeroBudgetViewModel.getAllZeroBudgetResult();
//        String strZeroBudgetResult = String.valueOf(zeroBudgetResult);
//
//        zeroBudgetViewModel.setTextView(zeroBudgetTextView,strZeroBudgetResult);
//
//        if(zeroBudgetResult > 0){
//            zeroBudgetViewModel.setTextViewColor(zeroBudgetTextView,-16711936);
//        }else if (zeroBudgetResult == 0){
//            zeroBudgetViewModel.setTextViewColor(zeroBudgetTextView,-16776961);
//        }else{
//            zeroBudgetViewModel.setTextViewColor(zeroBudgetTextView,-65536);
//        }



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}