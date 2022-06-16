package com.example.nomoola.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.fragment.InOutComeFragment;
import com.example.nomoola.fragment.dialog.EditSubCategoryDialog;
import com.example.nomoola.viewModel.SubcategoryViewModel;

public class SubcategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView subcatName, subCatBudgetPercentUsedView, subCatBudgetAmountUsedView;
    private ImageButton editSubcatButton;
    private ProgressBar percentBudgetLeft;
    private CardView cardView;

    private SubcategoryViewModel subcategoryViewModel;
    private FragmentManager fragmentManager;
    private SubCategory subCategory;
    private View view;

    public SubcategoryViewHolder(@NonNull View view, FragmentManager fragmentManager, SubcategoryViewModel subcategoryViewModel) {
        super(view);
        this.view = view;
        this.fragmentManager = fragmentManager;
        this.subcategoryViewModel = subcategoryViewModel;

        this.subcatName = view.findViewById(R.id.item_subCat_name);
        this.editSubcatButton = view.findViewById(R.id.item_subCat_editSubCat);
        this.percentBudgetLeft = view.findViewById(R.id.item_subCat_progressBar);
        this.subCatBudgetPercentUsedView = view.findViewById(R.id.item_subcategory_budgetUsedTextView);
        this.subCatBudgetAmountUsedView = view.findViewById(R.id.item_subcategory_amountUsed);
        this.cardView = view.findViewById(R.id.item_subCat_cardView);

        this.editSubcatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditSubCategoryDialog editSubCategoryDialog = new EditSubCategoryDialog(subCategory);
                editSubCategoryDialog.show(fragmentManager, "Subcategory_dialog");
            }
        });

        this.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction trans = fragmentManager.beginTransaction();
                trans.replace(R.id.fragmentContainerView, new InOutComeFragment(subCategory));
                trans.setReorderingAllowed(true);
                //trans.addToBackStack(null);
                trans.commit();
            }
        });
    }

    public void bind(SubCategory subcategory){
        this.subCategory = subcategory;
        this.subcatName.setText(this.subCategory.getM_SUBCAT_NAME());

        this.subcategoryViewModel.getPercentUsedBySubcategory(this.subCategory).observe((LifecycleOwner) view.getContext(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer percentUsed) {
                if(percentUsed == null){
                    percentUsed = 0;
                }
                percentBudgetLeft.setProgress(percentUsed);
                subCatBudgetPercentUsedView.setText(percentUsed + "%");
            }
        });

        this.subcategoryViewModel.getAmountUsedBySubcategory(this.subCategory).observe((LifecycleOwner) view.getContext(), new Observer<Double>() {
            @Override
            public void onChanged(Double amountUsed) {
                subCatBudgetAmountUsedView.setText(amountUsed + "€");
            }
        });


    }

    public static SubcategoryViewHolder create(ViewGroup parent, FragmentManager fragmentManager, SubcategoryViewModel subcategoryViewModel){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subcategory, parent, false);
        return new SubcategoryViewHolder(view, fragmentManager, subcategoryViewModel);
    }
}
