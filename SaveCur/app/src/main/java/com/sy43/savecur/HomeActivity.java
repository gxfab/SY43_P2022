package com.sy43.savecur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView categoriesGridView = (GridView) findViewById(R.id.categories_grid_view);
        categoriesGridView.setAdapter(new CategoriesGridAdapter(this, getCategories()));
    }

    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category("Food",150,0));
        categories.add(new Category("Party",80,15));
        categories.add(new Category("Other",180,70));
        categories.add(new Category("Other2",30,25));
        categories.add(new Category("Other3",90,16));
        categories.add(new Category("Other3",90,16));
        categories.add(new Category("Other3",90,16));
        categories.add(new Category("Other3",90,16));
        categories.add(new Category("Other3",90,16));
        categories.add(new Category("Other3",90,16));
        categories.add(new Category("Other3",90,16));

        return categories;
    }
}