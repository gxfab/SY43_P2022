package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.AddStableDialogFragmentBinding;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * class to add a stable dialog to a fragment
 */
public class AddStableDialogFragment extends DialogFragment {
    private AddStableDialogViewModel addStableDialogViewModel;
    private AddStableDialogFragmentBinding binding;

    /**
     * on create dialog method
     * @param savedInstanceState saved instance state
     * @return dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        addStableDialogViewModel = new ViewModelProvider(this).get(AddStableDialogViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflater = requireActivity().getLayoutInflater().inflate(R.layout.add_stable_dialog_fragment,null);
        NumberPicker frequencyStableNumberPicker = inflater.findViewById(R.id.frequency_stable_number_picker);
        frequencyStableNumberPicker.setMinValue(1);
        frequencyStableNumberPicker.setMaxValue(31);
        builder.setView(inflater)
                .setTitle("Add to "+addStableDialogViewModel.getCurrentSubCategory().getName())
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nameOfStable;
                        int amountOfStable;
                        int frequencyOfStable;
                        String dateOfStable;
                        String signOfStable;
                        long subCategoryIdOfStable;


                        EditText nameEditText = AddStableDialogFragment.this.getDialog().findViewById(R.id.name_stable_edit_text);
                        nameOfStable = nameEditText.getText().toString();

                        EditText amountEditText = AddStableDialogFragment.this.getDialog().findViewById(R.id.amount_stable_edit_text);
                        amountOfStable = Integer.parseInt(amountEditText.getText().toString());

                        NumberPicker frequencyStableNumberPicker = AddStableDialogFragment.this.getDialog().findViewById(R.id.frequency_stable_number_picker);
                        frequencyOfStable = frequencyStableNumberPicker.getValue();

                        Date date = Calendar.getInstance().getTime();
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                        dateOfStable = dateFormat.format(date);

                        if(addStableDialogViewModel.getCurrentSubCategory().getName().equals("Incomes")){
                            signOfStable="+";
                        }
                        else{
                            signOfStable="-";
                        }

                        subCategoryIdOfStable=addStableDialogViewModel.getCurrentSubCategory().getId();

                        StableExpensesAndIncome stableToInsert = new StableExpensesAndIncome(nameOfStable,amountOfStable,signOfStable,dateOfStable,frequencyOfStable,subCategoryIdOfStable);
                        addStableDialogViewModel.insert(stableToInsert);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AddStableDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
    public static String TAG="AddStableDialog";
}
