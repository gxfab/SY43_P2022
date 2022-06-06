package com.example.nomoola.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.database.entity.SubCategory;

public class SubcategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView subcatName;
    private ImageButton editSubcatButton;
    private ProgressBar percentBudgetLeft;

    private FragmentManager fragmentManager;
    private SubCategory subCategory;

    public SubcategoryViewHolder(@NonNull View view, FragmentManager fragmentManager) {
        super(view);
        this.fragmentManager = fragmentManager;

        this.subcatName = view.findViewById(R.id.item_subCat_name);
        this.editSubcatButton = view.findViewById(R.id.item_subCat_editSubCat);
        this.percentBudgetLeft = view.findViewById(R.id.item_subCat_progressBar);
    }

    public void bind(SubCategory subcategory){
        this.subCategory = subcategory;
        this.subcatName.setText(this.subCategory.getM_SUBCAT_NAME());
    }

    public static SubcategoryViewHolder create(ViewGroup parent, FragmentManager fragmentManager){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subcategory, parent, false);
        return new SubcategoryViewHolder(view, fragmentManager);
    }
}
