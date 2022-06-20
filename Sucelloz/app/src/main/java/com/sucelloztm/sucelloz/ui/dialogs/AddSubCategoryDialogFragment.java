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
import com.sucelloztm.sucelloz.models.SubCategories;

/**
 * DialogFragment to add SubCategories to SubCategoriesFragment
 */
public class AddSubCategoryDialogFragment extends DialogFragment {
    private AddSubCategoryDialogViewModel addSubCategoryDialogViewModel;

    /**
     * On create dialog method
     *
     * @param savedInstanceState saved instance state
     * @return dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        addSubCategoryDialogViewModel = new ViewModelProvider(this).get(AddSubCategoryDialogViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.add_sub_category_dialog_fragment, null))
                .setTitle(R.string.title_subcategory)
                .setPositiveButton(R.string.ok_category, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        String nameOfSubCategory;
                        long categoriesIdOfSubCategory;
                        EditText nameEditText = AddSubCategoryDialogFragment.this.getDialog().findViewById(R.id.name_subcategory);
                        nameOfSubCategory = nameEditText.getText().toString();
                        categoriesIdOfSubCategory = addSubCategoryDialogViewModel.getCurrentCategoryId();
                        SubCategories subCategoryToInsert = new SubCategories(nameOfSubCategory, categoriesIdOfSubCategory);
                        addSubCategoryDialogViewModel.insert(subCategoryToInsert);
                    }
                })
                .setNegativeButton(R.string.cancel_category, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        AddSubCategoryDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public static String TAG = "AddSubCategoryDialog";
}