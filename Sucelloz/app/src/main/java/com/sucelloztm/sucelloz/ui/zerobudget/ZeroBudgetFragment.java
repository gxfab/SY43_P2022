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


/**
 * Fragment for ZeroBudget
 */
public class ZeroBudgetFragment extends Fragment {

    private ZeroBudgetFragmentBinding binding;
    private ArrayList<SubCategories> zeroBudgetSubCategoriesList;
    private ZeroBudgetViewModel zeroBudgetViewModel;
    private TextView zeroBudgetTextView;
    private ZeroBudget zeroBudget;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*
          Instantiate ZeroBudgetViewModel and assign it to a local variable
         */
        zeroBudgetViewModel =
                new ViewModelProvider(this).get(ZeroBudgetViewModel.class);

        /*
          Get binding from ZeroBudgetFragmentViewBinding
         */
        binding = ZeroBudgetFragmentBinding.inflate(inflater, container, false);

        /*
          Get outermost view in the associated layout file
         */
        View root = binding.getRoot();

        /*
          Instantiating ZeroBudget
         */
        zeroBudget = new ZeroBudget();

        /*
          Get recyclerView from binding
         */
        RecyclerView recyclerView = binding.zeroBudgetRecyclerView;

        /*
          Create a new ArrayList of subCategories
         */
        zeroBudgetSubCategoriesList = new ArrayList<>();

        /*
          Get fragment's TextView from binding
         */
        zeroBudgetTextView = binding.zeroBudgetTextView;

        /*
          Populate zeroBudgetSubCategoriesList from zeroBudgetViewModel
         */
        String[] zeroBudgetNameList = new String[]{"Incomes", "Bills", "Envelopes",
                "Sinking Funds", "Extra debt", "Extra Savings"};
        for (String name:zeroBudgetNameList
             ) {
            zeroBudgetSubCategoriesList.add(zeroBudgetViewModel.getSubCategoryByName(name));
        }

        /*
          Create Adapter for subCategories recycler view
         */
        SubCategoriesAdapter subCategoriesAdapter = new SubCategoriesAdapter(zeroBudgetSubCategoriesList);

        /*
          Set layout manager and adapter of recycler view
         */
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(subCategoriesAdapter);

        registerForContextMenu(recyclerView);

        /*
          Navigation into sub-categories fragment on click
         */
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> {
            TextView currentZeroBudgetCategoryTextView= v.findViewById(R.id.text_view_subcategories);
            String currentZeroBudgetCategoryName = currentZeroBudgetCategoryTextView.getText().toString();
            SubCategories currentSubCategory = zeroBudgetViewModel.getSubCategoryByName(currentZeroBudgetCategoryName);
            zeroBudgetViewModel.setCurrentSubCategory(currentSubCategory);
            NavHostFragment.findNavController(ZeroBudgetFragment.this).navigate(R.id.action_navigation_zero_budget_to_navigation_sub_zero_budget);
        });


        /*
          Set of observers to retrieve results of queries concerning expenses, incomes and savings
         */
        final Observer<Integer> infrequentExpensesObserver= newInfrequentExpenses -> { zeroBudget.setInfrequentExpenses(newInfrequentExpenses); zeroBudget.setResultBudgetZero(zeroBudgetTextView);};

        final Observer<Integer> infrequentIncomesObserver= newInfrequentIncomes ->{zeroBudget.setInfrequentIncomes(newInfrequentIncomes); zeroBudget.setResultBudgetZero(zeroBudgetTextView);};

        final Observer<Integer> stableExpensesObserver= newStableExpenses -> {zeroBudget.setStableExpenses(newStableExpenses); zeroBudget.setResultBudgetZero(zeroBudgetTextView);};

        final Observer<Integer> stableIncomesObserver= newStableIncomes -> {zeroBudget.setStableIncomes(newStableIncomes); zeroBudget.setResultBudgetZero(zeroBudgetTextView);};

        final Observer<Integer> savingsObserver = newSavings -> {zeroBudget.setSavings(newSavings);zeroBudget.setResultBudgetZero(zeroBudgetTextView);};

        /*
          Observing expenses, incomes and savings
         */
        zeroBudgetViewModel.getInfrequentExpenses().observe(getViewLifecycleOwner(),infrequentExpensesObserver);
        zeroBudgetViewModel.getInfrequentIncomes().observe(getViewLifecycleOwner(),infrequentIncomesObserver);
        zeroBudgetViewModel.getStableExpenses().observe(getViewLifecycleOwner(),stableExpensesObserver);
        zeroBudgetViewModel.getStableIncomes().observe(getViewLifecycleOwner(),stableIncomesObserver);
        zeroBudgetViewModel.getSavings().observe(getViewLifecycleOwner(),savingsObserver);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}