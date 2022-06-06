package com.example.nomoola.fragment.dialog;

import android.os.Bundle;
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
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.viewModel.CategoryViewModel;

public class AddCategoryDialog extends DialogFragment {

    private TextView view_CategoryTitle, view_CategoryName, view_CategoryBudgetAmount;
    private EditText edit_CategoryName, edit_CategoryBudgetAmount;
    private ImageButton exitButton, deleteButton, confirmEditButton;

    private CategoryViewModel categoryViewModel;
    private Category category;

    public AddCategoryDialog(){
        this.category = new Category();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.dialog_category, container, false);
        this.view_CategoryTitle = view.findViewById(R.id.textViewCategoryTitle);
        this.view_CategoryName = view.findViewById(R.id.text_view_category_name);
        this.view_CategoryBudgetAmount = view.findViewById(R.id.textViewCategoryBudget);
        this.edit_CategoryName = view.findViewById(R.id.editTextCategoryName);
        this.edit_CategoryBudgetAmount = view.findViewById(R.id.editTextCatBudgetAmount);
        this.exitButton = view.findViewById(R.id.exitButton);
        this.deleteButton = view.findViewById(R.id.deleteCategoryButton);
        this.confirmEditButton = view.findViewById(R.id.confirmEditButton);

        this.view_CategoryTitle.setText("New Category");


        this.categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        this.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        this.deleteButton.setOnClickListener(new View.OnClickListener() {
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

                categoryViewModel.insert(category);
                dismiss();
            }
        });

        return view;
    }
}
