package com.sucelloztm.sucelloz.ui.subZeroBudget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.SubZeroBudgetFragmentBinding;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;
import com.sucelloztm.sucelloz.ui.dialogs.AddStableDialogFragment;
import com.sucelloztm.sucelloz.ui.miscellaneous.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;

public class SubZeroBudgetFragment extends Fragment {

    private SubZeroBudgetFragmentBinding binding;
    private SubZeroBudgetViewModel subZeroBudgetViewModel;
    private List<StableExpensesAndIncome> currentStableList;
    private RecyclerView recyclerView;
    private int itemIndex;

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
        registerForContextMenu(recyclerView);
        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                itemIndex = position;
                return false;
            }
        });

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
        binding =null;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.modify_menu_item:
                dialogForModifyStable(getActivity(),currentStableList.get(itemIndex).getId()).show();
                return true;
            case R.id.delete_menu_item:
                subZeroBudgetViewModel.deleteStable(currentStableList.get(itemIndex));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public Dialog dialogForModifyStable(Activity activity, long idOfStable){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final EditText amountEditText = new EditText(activity);

        builder.setTitle("Modify").setMessage("New Amount").setView(amountEditText);
        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final int amountOfStable = Integer.valueOf(amountEditText.getText().toString());
                final StableExpensesAndIncome stableToInsert = subZeroBudgetViewModel.getStableById(idOfStable);
                stableToInsert.setAmount(amountOfStable);
                subZeroBudgetViewModel.updateStable(stableToInsert);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }
}
