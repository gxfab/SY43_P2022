package com.example.nomoola.fragment.dialog;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nomoola.R;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.viewModel.SubcategoryViewModel;

public class EditSubCategoryDialog extends DialogFragment {

    private SubCategory subCategory;
    private TextView subCategoryName;
    private EditText editSubcatName;
    private ImageButton exitButton, confirmButton, deleteButton;

    private SubcategoryViewModel subcategoryViewModel;

    public EditSubCategoryDialog(SubCategory subCategory){
        super();
        this.subCategory = subCategory;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.subcategoryViewModel = new ViewModelProvider(this).get(SubcategoryViewModel.class);

        View view = inflater.inflate(R.layout.dialog_subcategory, container, false);
        this.subCategoryName = view.findViewById(R.id.dialog_subcat_SubCategoryTitle);
        this.editSubcatName = view.findViewById(R.id.dialog_subcat_editTextSubCategoryName);
        this.exitButton = view.findViewById(R.id.dialog_subcat_exitButton);
        this.confirmButton = view.findViewById(R.id.dialog_subcat_confirmEditButton);
        this.deleteButton = view.findViewById(R.id.dialog_subcat_deleteSubCategoryButton);

        this.subCategoryName.setText(this.subCategory.getM_SUBCAT_NAME());
        this.editSubcatName.setText(this.subCategory.getM_SUBCAT_NAME());

        this.editSubcatName.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        this.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        this.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subCatName = editSubcatName.getText().toString();
                int catID = subCategory.getM_CAT_ID();
                int id = subCategory.getM_SUBCAT_ID();
                subcategoryViewModel.update(catID, subCatName, id);
                dismiss();
            }
        });

        this.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subcategoryViewModel.delete(subCategory);
                dismiss();
            }
        });

        return view;
    }
}
