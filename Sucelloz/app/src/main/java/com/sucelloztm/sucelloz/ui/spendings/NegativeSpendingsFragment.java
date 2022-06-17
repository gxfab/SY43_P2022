package com.sucelloztm.sucelloz.ui.spendings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.sucelloztm.sucelloz.databinding.NegativeSpendingsFragmentBinding;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentSum;
import com.sucelloztm.sucelloz.ui.charts.PieChartSubCategoriesGenerator;
import com.sucelloztm.sucelloz.ui.dialogs.AddSpendingDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * class for the negative spendings fragment
 */
public class NegativeSpendingsFragment extends Fragment {
    private NegativeSpendingsFragmentBinding binding;
    private SpendingsViewModel spendingsViewModel;
    private List<Spendings> currentNegativeSpendingsList;
    private List<SubCategoriesWithInfrequentSum> currentSubCategoriesWithInfrequentSumList;
    private RecyclerView recyclerView;
    private PieChartSubCategoriesGenerator pieGen;

    /**
     * on create method
     * @param savedInstanceState saved instance state
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * on create view method
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState saved instance state
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=NegativeSpendingsFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        spendingsViewModel = new ViewModelProvider(this).get(SpendingsViewModel.class);
        currentNegativeSpendingsList = new ArrayList<>();
        NegativeSpendingsAdapter adapter = new NegativeSpendingsAdapter(currentNegativeSpendingsList);
        currentSubCategoriesWithInfrequentSumList = new ArrayList<>();
        pieGen = new PieChartSubCategoriesGenerator(currentSubCategoriesWithInfrequentSumList);
        pieGen.createPieChart(getContext(),binding.frameLayoutNegativeSpendings);
        final Observer<List<InfrequentExpensesAndIncome>> negativeSpendingsDataSet = new Observer<List<InfrequentExpensesAndIncome>>() {
            @Override
            public void onChanged(List<InfrequentExpensesAndIncome> infrequentExpensesAndIncomes) {
                currentNegativeSpendingsList.clear();
                infrequentExpensesAndIncomes.forEach(spending -> {
                    String subCategoryName = spendingsViewModel.getSubCategoryNameWithId(spending.getSubCategoriesId());
                    currentNegativeSpendingsList.add(new Spendings(spending.getName(),
                                    spending.getAmount(),
                                    spending.getDate(),
                                    subCategoryName
                            )
                    );
                });
                adapter.notifyDataSetChanged();
            }
        };

        final Observer<List<SubCategoriesWithInfrequentSum>> subCategoriesWithNegativeInfrequentSumObserver = new Observer<List<SubCategoriesWithInfrequentSum>>() {
            @Override
            public void onChanged(List<SubCategoriesWithInfrequentSum> subCategoriesWithInfrequentSumList) {
                currentSubCategoriesWithInfrequentSumList.clear();
                currentSubCategoriesWithInfrequentSumList.addAll(subCategoriesWithInfrequentSumList);

                pieGen.getPieChart().clear();
                pieGen.getPieChart().setData(pieGen.generatePieData());
                pieGen.getPieChart().animateY(1400, Easing.EaseInOutQuad);
                pieGen.getPieChart().invalidate();
            }
        };

        spendingsViewModel.getAllNegativeSpendings().observe(getViewLifecycleOwner(),negativeSpendingsDataSet);
        spendingsViewModel.getAllSubCategoriesWithNegativeInfrequentSum().observe(getViewLifecycleOwner(),subCategoriesWithNegativeInfrequentSumObserver);
        recyclerView = binding.spendingsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
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

        binding.addSpendingButtonSpendings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSpendingDialogFragment().show(getChildFragmentManager(),AddSpendingDialogFragment.TAG);
            }
        });
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
