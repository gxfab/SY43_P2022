package com.example.nomoola.fragment.dialog;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nomoola.R;
import com.example.nomoola.database.converter.Converters;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.viewModel.CategoryViewModel;

import java.time.LocalDate;

public class AddCategoryDialog extends DialogFragment {

    private TextView view_CategoryTitle;
    private EditText edit_CategoryName, edit_CategoryBudgetAmount;
    private ImageButton exitButton, deleteButton, confirmEditButton;
    private Spinner spinner;

    private CategoryViewModel categoryViewModel;
    private Category category;

    public AddCategoryDialog(){
        this.category = new Category();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        View view = inflater.inflate(R.layout.dialog_category, container, false);

        this.view_CategoryTitle = view.findViewById(R.id.dialog_cat_title);
        this.edit_CategoryName = view.findViewById(R.id.dialog_cat_editName);
        this.edit_CategoryBudgetAmount = view.findViewById(R.id.dialog_cat_editAmount);
        this.exitButton = view.findViewById(R.id.dialog_cat_exitbutton);
        this.deleteButton = view.findViewById(R.id.dialog_cat_delete);
        this.confirmEditButton = view.findViewById(R.id.dialog_cat_confirmEdit);
        this.spinner = view.findViewById(R.id.dialog_cat_spinnerType);

        this.view_CategoryTitle.setText("New Category");
        this.deleteButton.setVisibility(View.GONE);

        this.edit_CategoryName.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.category_type ,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        this.spinner.setAdapter(adapter);

        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner.setSelection(0);
            }
        });

        this.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        this.confirmEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit_CategoryName.getText().toString();
                double budgetAmount = Double.valueOf(edit_CategoryBudgetAmount.getText().toString()); //TODO check if the amount is null

                category.setM_CAT_NAME(name);
                category.setM_CAT_BUDGET_AMOUNT(budgetAmount);
                category.setM_CAT_TYPE(Converters.convertToCatTypeFromString(spinner.getSelectedItem().toString()));
                category.setM_CAT_DATE(LocalDate.now());

                categoryViewModel.insert(category);
                dismiss();
            }
        });



        return view;
    }
}
