package com.example.sy43.repositories;

import androidx.lifecycle.MutableLiveData;


import com.example.sy43.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private static CategoryRepository instance;
    private ArrayList<Category> dataSet = new ArrayList<>();

    public static CategoryRepository getInstance() {
        if (instance == null) instance = new CategoryRepository();
        return instance;
    }

    // For now we fake the query
    public MutableLiveData<List<Category>> getCategories() {
        this.setCategories();
        MutableLiveData<List<Category>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    public void addCategory(String name) {
        this.dataSet.add(new Category(name, 10, 20));
    }

    public void setCategories() {
        dataSet.add(new Category("Groceries", 10, 100));
        dataSet.add(new Category("Work", 10, 100));
        dataSet.add(new Category("School", 10, 100));
        dataSet.add(new Category("QSDQDSQ", 120, 140));
        dataSet.add(new Category("Buy a car", 0, 10000, true));
        dataSet.add(new Category("Buy a cqdqdqar", 0, 10000, true));

    }
}
