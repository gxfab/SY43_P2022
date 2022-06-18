package com.sucelloztm.sucelloz.ui.zerobudget;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.ZeroBudgetFragmentBinding;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.ui.miscellaneous.ItemClickSupport;
import com.sucelloztm.sucelloz.ui.subcategories.SubCategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * fragment for the zero budget
 */
public class ZeroBudgetFragment extends Fragment {

    private ZeroBudgetFragmentBinding binding;
    private List<SubCategories> zeroBudgetSubCategoriesList;
    private ZeroBudgetViewModel zeroBudgetViewModel;
    private TextView zeroBudgetTextView;
    private ZeroBudget zeroBudget;

    /**
     * on create view method
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState saved instance state
     * @return view
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zeroBudgetViewModel =
                new ViewModelProvider(this).get(ZeroBudgetViewModel.class);
        binding = ZeroBudgetFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        zeroBudgetTextView = binding.zeroBudgetTextView;
        zeroBudget = new ZeroBudget();
        RecyclerView recyclerView = binding.zeroBudgetRecyclerView;
        zeroBudgetSubCategoriesList = new ArrayList<>();
        zeroBudgetTextView = binding.zeroBudgetTextView;
        String[] zeroBudgetNameList = new String[]{"Incomes", "Bills", "Envelopes",
                "Sinking Funds", "Extra Debt", "Extra Savings"};
        for (String name:zeroBudgetNameList
             ) {
            zeroBudgetSubCategoriesList.add(zeroBudgetViewModel.getSubCategoryByName(name));
        }


        ZeroBudgetAdapter zeroBudgetAdapter = new ZeroBudgetAdapter(zeroBudgetSubCategoriesList);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(zeroBudgetAdapter);
        registerForContextMenu(recyclerView);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> {
            TextView currentZeroBudgetCategoryTextView= v.findViewById(R.id.zero_budget_text_view);
            String currentZeroBudgetCategoryName = currentZeroBudgetCategoryTextView.getText().toString();
            SubCategories currentSubCategory = zeroBudgetViewModel.getSubCategoryByName(currentZeroBudgetCategoryName);
            zeroBudgetViewModel.setCurrentSubCategory(currentSubCategory);
            NavHostFragment.findNavController(ZeroBudgetFragment.this).navigate(R.id.action_navigation_zero_budget_to_navigation_sub_zero_budget);
        });


        final Observer<Integer> infrequentExpensesObserver= newInfrequentExpenses -> { zeroBudget.setInfrequentExpenses(newInfrequentExpenses); zeroBudget.setResultBudgetZero(zeroBudgetTextView);};

        final Observer<Integer> infrequentIncomesObserver= newInfrequentIncomes ->{zeroBudget.setInfrequentIncomes(newInfrequentIncomes); zeroBudget.setResultBudgetZero(zeroBudgetTextView);};

        final Observer<Integer> stableExpensesObserver= newStableExpenses -> {zeroBudget.setStableExpenses(newStableExpenses); zeroBudget.setResultBudgetZero(zeroBudgetTextView);};

        final Observer<Integer> stableIncomesObserver= newStableIncomes -> {zeroBudget.setStableIncomes(newStableIncomes); zeroBudget.setResultBudgetZero(zeroBudgetTextView);};

        final Observer<Integer> savingsObserver = newSavings -> {zeroBudget.setSavings(newSavings);zeroBudget.setResultBudgetZero(zeroBudgetTextView);};

        zeroBudgetViewModel.getInfrequentExpenses().observe(getViewLifecycleOwner(),infrequentExpensesObserver);
        zeroBudgetViewModel.getInfrequentIncomes().observe(getViewLifecycleOwner(),infrequentIncomesObserver);
        zeroBudgetViewModel.getStableExpenses().observe(getViewLifecycleOwner(),stableExpensesObserver);
        zeroBudgetViewModel.getStableIncomes().observe(getViewLifecycleOwner(),stableIncomesObserver);
        zeroBudgetViewModel.getSavings().observe(getViewLifecycleOwner(),savingsObserver);







        return root;
    }

    /**
     * on view created method
     * @param view view
     * @param savedInstanceState saved instance state
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * on destroy view method
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}