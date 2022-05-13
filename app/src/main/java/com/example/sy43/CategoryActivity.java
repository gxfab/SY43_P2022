package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sy43.adapters.CategoryAdapter;
import com.example.sy43.models.Category;
import com.example.sy43.viewmodels.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;


public class CategoryActivity extends AppCompatActivity {
    private CategoryViewModel categoryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Category page");
        setContentView(R.layout.activity_category);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.init();
        categoryViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                ListView lv = (ListView) findViewById(R.id.categoryListView);
                // https://stackoverflow.com/questions/5070830/populating-a-listview-using-an-arraylist
/*
                ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(
                        CategoryActivity.this,
                        android.R.layout.simple_list_item_2,
                        categories);
 */

                CategoryAdapter arrayAdapter = new CategoryAdapter(
                        CategoryActivity.this,
                        R.layout.category_list_item,
                        categories);

                lv.setAdapter(arrayAdapter);
            }
        });
    }
}