package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.models.Categories;

/**
 * DialogFragment to add a dialog to CategoriesFragment
 */
public class AddCategoryDialogFragment extends DialogFragment {
    private AddCategoryDialogViewModel addCategoryDialogViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        addCategoryDialogViewModel = new ViewModelProvider(this).get(AddCategoryDialogViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.add_category_dialog_fragment, null))
                .setTitle(R.string.title_category)
                .setPositiveButton(R.string.ok_category, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        String nameOfCategory;
                        EditText nameEditText = AddCategoryDialogFragment.this.getDialog().findViewById(R.id.name_category);
                        nameOfCategory = nameEditText.getText().toString();
                        Categories categoryToInsert = new Categories(nameOfCategory, false);
                        addCategoryDialogViewModel.insert(categoryToInsert);
                    }
                })
                .setNegativeButton(R.string.cancel_category, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        AddCategoryDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public static String TAG = "AddCategoryDialog";
}