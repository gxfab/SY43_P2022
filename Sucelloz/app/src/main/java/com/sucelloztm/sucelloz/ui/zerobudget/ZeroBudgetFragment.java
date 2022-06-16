package com.sucelloztm.sucelloz.ui.zerobudget;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.sucelloztm.sucelloz.databinding.ZeroBudgetFragmentBinding;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.ui.categories.CategoriesAdapter;

import java.util.ArrayList;


public class ZeroBudgetFragment extends Fragment {

    private ZeroBudgetFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ZeroBudgetViewModel zeroBudgetViewModel =
                new ViewModelProvider(this).get(ZeroBudgetViewModel.class);

        binding = ZeroBudgetFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*RecyclerView recyclerView = binding.zeroBudgetRecyclerView;


        ArrayList<Categories> zeroBudgetCategoriesList = new ArrayList<>();
        String[] zeroBudgetNameList = new String[]{"Incomes", "Bills", "Envelopes",
                "Sinking Funds", "Extra debt", "Extra Savings"};
        for (String name: zeroBudgetNameList
        ) {
            Categories currentCat = new Categories(name,true);
            zeroBudgetCategoriesList.add()

        }


        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(zeroBudgetCategoriesList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(categoriesAdapter);*/

        final TextView textView = binding.zeroBudgetTextView;
        zeroBudgetViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}