package com.example.nomoola.viewHolder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nomoola.R;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.fragment.CategoryFragment;

import com.example.nomoola.fragment.SubcategoryFragment;
import com.example.nomoola.fragment.dialog.EditCategoryDialog;
import com.example.nomoola.viewModel.CategoryViewModel;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView categoryName;
    private TextView categoryBudgetAmount, categoryBudgetLeftAmount, percent;
    private ImageButton categoryEditButton;
    private CardView categoryCardView;
    private ProgressBar percentProgressBar;

    private CategoryViewModel categoryViewModel;
    private FragmentManager fragmentManager;
    private Category category;
    private View view;


    public CategoryViewHolder(View view, FragmentManager fragmentManager, CategoryViewModel categoryViewModel){
        super(view);
        Log.d("CREATION", "ViewHolder constructor from " + this.getClass().toString() + " started");
        this.view = view;
        this.fragmentManager = fragmentManager;
        this.categoryViewModel = categoryViewModel;

        this.categoryName = view.findViewById(R.id.text_view_category_name); //Name of the category
        this.categoryBudgetAmount = view.findViewById(R.id.budgetAmount); //Amount of the category
        this.categoryEditButton = view.findViewById(R.id.item_subCat_editSubCat); //Button "pen" to edit the category
        this.categoryCardView = view.findViewById(R.id.item_cat_cardView); //CardView of the category
        this.categoryBudgetLeftAmount = view.findViewById(R.id.budgetLeftAmount); //Text view which show the budge left of the category
        this.percent = view.findViewById(R.id.item_category_percentTextView); //Percent textView which show the percent of budget used
        this.percentProgressBar = view.findViewById(R.id.subcat_cat_progressBar); //ProgressBar which show the percent of budget used

        this.categoryEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditCategoryDialog catDialog = new EditCategoryDialog(category);
                catDialog.show(fragmentManager, "Category_dialog");
            }
        });

        this.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction trans = fragmentManager.beginTransaction();
                trans.replace(R.id.fragmentContainerView, new SubcategoryFragment(category));
                trans.setReorderingAllowed(true);
                //trans.addToBackStack(null);
                trans.commit();
            }
        });



        Log.d("CREATION", "ViewHolder constructor from " + this.getClass().toString() + " finished");
    }

    public void bind(Category cat){
        this.category = cat;
        this.categoryName.setText(this.category.getM_CAT_NAME());
        this.categoryBudgetAmount.setText(this.category.getM_CAT_BUDGET_AMOUNT() + "€");

        this.categoryViewModel.getBudgetLeftOf(this.category).observe((LifecycleOwner) this.view.getContext(), new Observer<Double>() {
            @Override
            public void onChanged(Double budgetLeft) {
                if(budgetLeft == null){
                    budgetLeft = 0.;
                }
                categoryBudgetLeftAmount.setText(String.format("%.2f", budgetLeft) + "€");
            }
        });

        this.categoryViewModel.getPercentUsedOfCategory(this.category).observe((LifecycleOwner) this.view.getContext(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer percentUsed) {
                if(percentUsed == null){
                    percentUsed = 0;
                }
                percent.setText(percentUsed + "%");
                percentProgressBar.setProgress(percentUsed);
            }
        });
    }

    public static CategoryViewHolder create(ViewGroup parent, FragmentManager fragmentManager, CategoryViewModel categoryViewModel){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view, fragmentManager, categoryViewModel);
    }
}