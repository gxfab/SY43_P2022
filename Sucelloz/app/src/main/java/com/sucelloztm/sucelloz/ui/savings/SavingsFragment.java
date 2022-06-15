package com.sucelloztm.sucelloz.ui.savings;


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
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.SavingsFragmentBinding;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.Savings;
import com.sucelloztm.sucelloz.ui.charts.BarChartGenerator;
import com.sucelloztm.sucelloz.ui.dialogs.AddSavingsDialogFragment;
import com.sucelloztm.sucelloz.ui.miscellaneous.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;



public class SavingsFragment extends Fragment implements LifecycleOwner {

    private SavingsFragmentBinding binding;
    private SavingsViewModel savingsViewModel;
    private List<Savings> currentSavingsList;
    private RecyclerView recyclerView;
    private int itemIndex;
    private BarChartGenerator barGen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = SavingsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        savingsViewModel = new ViewModelProvider(this).get(SavingsViewModel.class);
        currentSavingsList = new ArrayList<>();
        SavingsAdapter adapter = new SavingsAdapter(currentSavingsList);


        final Observer<List<Savings>> savingsDataSet = new Observer<List<Savings>>() {
            @Override
            public void onChanged(List<Savings> savingsList) {
                currentSavingsList.clear();
                currentSavingsList.addAll(savingsList);
                adapter.notifyDataSetChanged();
            }
        };
        savingsViewModel.getAllSavings().observe(getViewLifecycleOwner(), savingsDataSet);
        recyclerView = binding.savingsRecyclerView;
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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addSavingsButtonSavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSavingsDialogFragment().show(getChildFragmentManager(), AddSavingsDialogFragment.TAG);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.modify_menu_item:
                dialogForModifySaving(getActivity(),currentSavingsList.get(itemIndex).getId()).show();
                return true;
            case R.id.delete_menu_item:
                savingsViewModel.deleteSaving(currentSavingsList.get(itemIndex));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public Dialog dialogForModifySaving(Activity activity, long idOfSaving){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final EditText amountEditText = new EditText(activity);

        builder.setTitle("Change amount of Saving").setMessage("New Amount").setView(amountEditText);
        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final int amountOfSaving = Integer.valueOf(amountEditText.getText().toString());
                final Savings savingToModify = savingsViewModel.getSavingById(idOfSaving);
                savingToModify.setReachedAmount(amountOfSaving);
                savingToModify.setPercentage(((float)savingToModify.getReachedAmount()/savingToModify.getInitialAmount())*100);
                savingsViewModel.updateSaving(savingToModify);
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