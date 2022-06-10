package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sy43.adapters.CategoryAdapter;
import com.example.sy43.models.Category;
import com.example.sy43.viewmodels.CategoryViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
            public void onChanged(List<Category> receivedCategories) {
                 // https://stackoverflow.com/questions/5070830/populating-a-listview-using-an-arraylist
                /*
                ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(
                        CategoryActivity.this,
                        android.R.layout.simple_list_item_2,
                        categories);
                */
                // We have to do that because a category model can be an objective
                List<Category> categories = Utils.getTrueCategories(receivedCategories);
                List<Category> objectives = Utils.getObjectives(receivedCategories);

                CategoryAdapter catArrayAdapter = new CategoryAdapter(
                        CategoryActivity.this,
                        R.layout.category_list_item,
                        categories);
                ListView catLv = (ListView) findViewById(R.id.categoryListView);
                catLv.setAdapter(catArrayAdapter);

                CategoryAdapter objArrayAdapter = new CategoryAdapter(
                        CategoryActivity.this,
                        R.layout.category_list_item,
                        objectives);
                ListView objLv = (ListView) findViewById(R.id.objectiveListView);
                objLv.setAdapter(objArrayAdapter);
            }
        });
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CategoryActivity.this, "Added", Toast.LENGTH_LONG).show();
                //categoryViewModel.addNewCategory(new Category("Test1234", 10, 20));
                Intent intent = new Intent(v.getContext(), CreateActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
