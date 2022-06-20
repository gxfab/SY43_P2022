package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.models.Savings;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * DialogFragment to add a dialog in SavingsFragment
 */
public class AddSavingsDialogFragment extends DialogFragment {
    private AddSavingsDialogViewModel addSavingsDialogViewModel;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        addSavingsDialogViewModel = new ViewModelProvider(this).get(AddSavingsDialogViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.add_savings_dialog_fragment, null))
                .setTitle(R.string.title_savings)
                .setPositiveButton(R.string.ok_savings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                        String nameOfSaving;
                        String deadlineOfSaving;
                        int initialAmountOfSaving;

                        EditText nameEditText = AddSavingsDialogFragment.this.getDialog().findViewById(R.id.name_savings);
                        nameOfSaving = nameEditText.getText().toString();

                        DatePicker deadlineDatePicker = AddSavingsDialogFragment.this.getDialog().findViewById(R.id.date_picker_savings);
                        int day = deadlineDatePicker.getDayOfMonth();
                        int month = deadlineDatePicker.getMonth();
                        int year = deadlineDatePicker.getYear();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = sdf.format(calendar.getTime());
                        deadlineOfSaving = formattedDate;

                        EditText goalEditText = AddSavingsDialogFragment.this.getDialog().findViewById(R.id.goal_savings);
                        initialAmountOfSaving = Integer.parseInt(goalEditText.getText().toString());
                        Savings savingToInsert = new Savings(nameOfSaving, deadlineOfSaving, initialAmountOfSaving);
                        addSavingsDialogViewModel.insert(savingToInsert);

                    }
                })
                .setNegativeButton(R.string.cancel_savings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        AddSavingsDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public static String TAG = "AddSavingsDialog";
}
