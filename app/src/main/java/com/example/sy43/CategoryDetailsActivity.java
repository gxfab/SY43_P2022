package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sy43.adapters.CategoryAdapter;
import com.example.sy43.adapters.SubCategoryAdapter;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.SubCategoryViewModel;

import java.util.List;

public class CategoryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_category_details);
        this.setTitle("Category details");
        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.init();
        Bundle b = getIntent().getExtras();
        int categoryId = b.getInt("categoryId");
        categoryViewModel.getCategoryById(categoryId).observe(this, new Observer<Categorydb>() {
            @Override
            public void onChanged(Categorydb receivedCategory) {
                RelativeLayout card = findViewById(R.id.categoryCard);
                TextView price = card.findViewById(R.id.tvCurrentPrice);
                TextView name = card.findViewById(R.id.tvName);
                ProgressBar progressBar = card.findViewById(R.id.progressBar);
                name.setText(String.valueOf(categoryId));
                progressBar.setMax((int) receivedCategory.getMaxValue());
                progressBar.setProgress((int) receivedCategory.CurrentValue(), true);
                name.setText(receivedCategory.getCatName());
                price.setText("$" + receivedCategory.CurrentValue() + "/$" + receivedCategory.getMaxValue());
            }
        });

        SubCategoryViewModel subCategoryViewModel = new ViewModelProvider(this).get(SubCategoryViewModel.class);
        subCategoryViewModel.init();
        subCategoryViewModel.getSubCategoriesByCatId(categoryId).observe(this, new Observer<List<SubCategory>>() {
            @Override
            public void onChanged(List<SubCategory> subCategories) {
                SubCategoryAdapter objArrayAdapter = new SubCategoryAdapter(
                        CategoryDetailsActivity.this,
                        R.layout.category_list_item,
                        subCategories);
                ListView objLv = (ListView) findViewById(R.id.objectiveListView);
                objLv.setAdapter(objArrayAdapter);
            }
        });
    }
}