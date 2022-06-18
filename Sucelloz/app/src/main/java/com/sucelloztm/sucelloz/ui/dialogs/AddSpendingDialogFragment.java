package com.sucelloztm.sucelloz.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.AddSpendingDialogFragmentBinding;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * class to add a spending dialog to a fragment
 */
public class AddSpendingDialogFragment extends DialogFragment {

    private AddSpendingDialogViewModel addSpendingDialogViewModel;
    private List<String> subCategoriesList;

    /**
     * on create dialog method
     * @param savedInstanceState savid instance state
     * @return dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);
        addSpendingDialogViewModel = new ViewModelProvider(this).get(AddSpendingDialogViewModel.class);
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_spending_dialog_fragment,null);
        builder.setView(view);
        Spinner spinnerSpendings = (Spinner) view.findViewById(R.id.spinner_spendings);
        subCategoriesList = addSpendingDialogViewModel.getSubCategoriesNames();
        subCategoriesList.remove("Incomes");
        subCategoriesList.remove("Bills");
        subCategoriesList.remove("Envelopes");
        subCategoriesList.remove("Sinking Funds");
        subCategoriesList.remove("Extra Debt");
        subCategoriesList.remove("Extra Savings");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, subCategoriesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpendings.setAdapter(arrayAdapter);

        builder.setTitle(R.string.title_spending)
                .setPositiveButton(R.string.ok_spending, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        String nameOfSpending;
                        int amountOfSpending;
                        String signOfSpending;
                        String dateOfSpending;
                        long idOfSubCategoryOfSpending;

                        EditText nameEditText = AddSpendingDialogFragment.this.getDialog().findViewById(R.id.name_spending);
                        nameOfSpending = nameEditText.getText().toString();

                        EditText amountEditText = AddSpendingDialogFragment.this.getDialog().findViewById(R.id.amount_spending);
                        amountOfSpending = Integer.parseInt(amountEditText.getText().toString());

                        CheckBox signCheckBox = AddSpendingDialogFragment.this.getDialog().findViewById(R.id.check_box_spending);
                        boolean checked = signCheckBox.isChecked();
                        if (checked){
                            signOfSpending="+";
                        }
                        else{
                            signOfSpending="-";
                        }

                        Date date = Calendar.getInstance().getTime();
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                        dateOfSpending = dateFormat.format(date);

                        String subCategoryNameOfSpending = spinnerSpendings.getSelectedItem().toString();
                        idOfSubCategoryOfSpending = addSpendingDialogViewModel.getSubCategoryWithName(subCategoryNameOfSpending).getId();
                        InfrequentExpensesAndIncome subCategoryToInsert = new InfrequentExpensesAndIncome(nameOfSpending,amountOfSpending,signOfSpending,dateOfSpending,idOfSubCategoryOfSpending);
                        addSpendingDialogViewModel.insert(subCategoryToInsert);
                    }
                })
                .setNegativeButton(R.string.cancel_spending, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        AddSpendingDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
    public static String TAG = "AddSpendingDialog";
}
