package com.example.nomoola.fragment.dialog;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nomoola.R;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.viewModel.SubcategoryViewModel;

public class AddSubCategoryDialog extends DialogFragment {

    private Category category;
    private SubCategory subCategory;
    private EditText editSubcatName;
    private ImageButton exitbutton, confirmButton, deleteButton;

    private SubcategoryViewModel subcategoryViewModel;

    public AddSubCategoryDialog(Category category){
        super();
        this.category = category;
        this.subCategory = new SubCategory();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.subcategoryViewModel = new ViewModelProvider(this).get(SubcategoryViewModel.class);

        View view = inflater.inflate(R.layout.dialog_subcategory, container, false);
        this.editSubcatName = view.findViewById(R.id.dialog_subcat_editTextSubCategoryName);
        this.exitbutton = view.findViewById(R.id.dialog_subcat_exitButton);
        this.confirmButton = view.findViewById(R.id.dialog_subcat_confirmEditButton);
        this.deleteButton = view.findViewById(R.id.dialog_subcat_deleteSubCategoryButton);
        this.deleteButton.setVisibility(View.INVISIBLE);

        this.editSubcatName.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        this.exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        this.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subCategory.setM_SUBCAT_NAME(editSubcatName.getText().toString());
                subCategory.setM_CAT_ID(category.getM_CAT_ID());
                subcategoryViewModel.insert(subCategory);
                dismiss();
            }
        });

        return view;
    }
}
