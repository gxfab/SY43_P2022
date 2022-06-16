package com.sucelloztm.sucelloz.ui.subZeroBudget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.SubCategoriesFragmentBinding;
import com.sucelloztm.sucelloz.databinding.SubZeroBudgetFragmentBinding;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;
import com.sucelloztm.sucelloz.ui.dialogs.AddStableDialogFragment;
import com.sucelloztm.sucelloz.ui.subcategories.SubCategoriesViewModel;

import java.util.ArrayList;
import java.util.List;

public class SubZeroBudgetFragment extends Fragment {

    private SubZeroBudgetFragmentBinding binding;
    private SubZeroBudgetViewModel subZeroBudgetViewModel;
    private List<StableExpensesAndIncome> currentStableList;
    private RecyclerView recyclerView;
    private  int itemIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SubZeroBudgetFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        subZeroBudgetViewModel = new ViewModelProvider(this).get(SubZeroBudgetViewModel.class);
        currentStableList = new ArrayList<>();
        SubZeroBudgetAdapter adapter = new SubZeroBudgetAdapter(currentStableList);

        final Observer<List<StableExpensesAndIncome>> subZeroBudgetListObserver = new Observer<List<StableExpensesAndIncome>>() {
            @Override
            public void onChanged(List<StableExpensesAndIncome> stableExpensesAndIncomes) {
                currentStableList.clear();
                currentStableList.addAll(stableExpensesAndIncomes);
                adapter.notifyDataSetChanged();
            }
        };
        subZeroBudgetViewModel.getAllStableFromSubCategory(SubCategoriesRepository.getCurrentSubCategory().getId()).observe(getViewLifecycleOwner(),subZeroBudgetListObserver);
        recyclerView = binding.subzerobudgetRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.returnZerobudgetButtonSubzerobudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SubZeroBudgetFragment.this).navigate(R.id.action_navigation_sub_zero_budget_to_navigation_zero_budget);
            }
        });
        binding.addStableButtonSubzerobudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddStableDialogFragment().show(getChildFragmentManager(),AddStableDialogFragment.TAG);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
