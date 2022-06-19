package com.example.sy43;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sy43.adapters.CategoryAdapter;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.models.Category;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.SubCategoryViewModel;
import com.example.sy43.viewmodels.TransactionViewModel;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Affiche l'ensemble des catégories et objetifs créés par l'utilisateur
 * L'affichage inclu le montant des dépenses respectivement fixées et faites dans chaque catégorie
 * L'affichage inclu le montant des économies respectivement fixées et faites dans chaque objectif
 */

public class CategoryActivity extends AppCompatActivity {
    private CategoryViewModel categoryViewModel;
    private TransactionViewModel transactionViewModel;
    private SubCategoryViewModel subCategoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Category page");
        setContentView(R.layout.activity_category);
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

    @Override
    protected void onResume() {
        super.onResume();
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.init();

        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.init();

        subCategoryViewModel = new ViewModelProvider(this).get(SubCategoryViewModel.class);
        subCategoryViewModel.init();

        LifecycleOwner owner = this;
        categoryViewModel.getCategories().observe(this, new Observer<List<Categorydb>>() {

            /**
             *
             * @param receivedCategories les nouvelles catégories à ajouter
             */
            @Override
            public void onChanged(List<Categorydb> receivedCategories) {
                // https://stackoverflow.com/questions/5070830/populating-a-listview-using-an-arraylist
                CategoryAdapter catArrayAdapter = new CategoryAdapter(
                        CategoryActivity.this,
                        owner,
                        R.layout.category_list_item,
                        receivedCategories,
                        categoryViewModel,
                        transactionViewModel,
                        subCategoryViewModel
                );
                ListView catLv = (ListView) findViewById(R.id.categoryListView);
                catLv.setAdapter(catArrayAdapter);

            }
        });

        categoryViewModel.getObjectives().observe(this, new Observer<List<Categorydb>>() {

            /**
             *
             * @param receivedObjectives les nouveaux objectifs à ajouter
             */
            @Override
            public void onChanged(List<Categorydb> receivedObjectives) {
                // https://stackoverflow.com/questions/5070830/populating-a-listview-using-an-arraylist

                CategoryAdapter objArrayAdapter = new CategoryAdapter(
                        CategoryActivity.this,
                        owner,
                        R.layout.category_list_item,
                        receivedObjectives,
                        categoryViewModel, transactionViewModel, subCategoryViewModel);
                ListView objLv = (ListView) findViewById(R.id.objectiveListView);
                objLv.setAdapter(objArrayAdapter);
            }
        });

    }
}
