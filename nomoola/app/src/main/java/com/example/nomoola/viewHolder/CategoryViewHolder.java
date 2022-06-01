package com.example.nomoola.viewHolder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nomoola.R;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.fragment.dialog.CategoryDialog;

import java.util.zip.Inflater;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView categoryName;
    private TextView categoryBudgetAmount;
    private ImageButton categoryEditButton;

    private FragmentManager fragmentManager;
    private Category category;

    public CategoryViewHolder(View view, FragmentManager fragmentManager){
        super(view);
        Log.d("CREATION", "ViewHolder constructor from " + this.getClass().toString() + " started");
        this.fragmentManager = fragmentManager;

        this.categoryName = view.findViewById(R.id.text_view_category_name); //Name of the category
        this.categoryBudgetAmount = view.findViewById(R.id.budgetAmount); //Amount of the category
        this.categoryEditButton = view.findViewById(R.id.editCategory); //Button "pen" to edit the category

        this.categoryEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryDialog catDialog = new CategoryDialog(category);
                catDialog.show(fragmentManager, "Category_dialog");
            }
        });

        Log.d("CREATION", "ViewHolder constructor from " + this.getClass().toString() + " finished");
    }

    public void bind(Category cat){
        this.category = cat;
        this.categoryName.setText(this.category.getM_CAT_NAME());
        this.categoryBudgetAmount.setText(this.category.getM_CAT_BUDGET_AMOUNT() + "€");
    }

    public static CategoryViewHolder create(ViewGroup parent, FragmentManager fragmentManager){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view, fragmentManager);
    }
}