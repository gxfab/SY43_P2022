package com.sucelloztm.sucelloz.ui.subZeroBudget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.databinding.SubCategoriesFragmentBinding;
import com.sucelloztm.sucelloz.databinding.SubZeroBudgetFragmentBinding;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
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
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
