package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sy43.adapters.CategoryAdapter;
import com.example.sy43.adapters.SubCategoryAdapter;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.SubCategoryViewModel;
import com.example.sy43.viewmodels.TransactionViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_category_details);
        this.setTitle("Category details");
        Context context = this.getApplicationContext();

        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.init();

        TransactionViewModel transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.init();

        SubCategoryViewModel subCategoryViewModel = new ViewModelProvider(this).get(SubCategoryViewModel.class);
        subCategoryViewModel.init();

        LifecycleOwner owner = this;

        Bundle b = getIntent().getExtras();
        int categoryId = b.getInt("categoryId");
        categoryViewModel.getCategoryById(categoryId).observe(this, new Observer<Categorydb>() {
            @Override
            public void onChanged(Categorydb receivedCategory) {
                /*
                RelativeLayout card = findViewById(R.id.categoryCard);
                TextView price = card.findViewById(R.id.tvCurrentPrice);
                TextView name = card.findViewById(R.id.tvName);
                ProgressBar progressBar = card.findViewById(R.id.progressBar);
                name.setText(String.valueOf(categoryId));
                progressBar.setMax((int) receivedCategory.getMaxValue());
                progressBar.setProgress((int) receivedCategory.CurrentValue(), true);
                name.setText(receivedCategory.getCatName());
                price.setText("$" + receivedCategory.CurrentValue() + "/$" + receivedCategory.getMaxValue());
                */
                List<Categorydb> categories = new ArrayList<Categorydb>();
                categories.add(receivedCategory);

                CategoryAdapter catArrayAdapter = new CategoryAdapter(
                        CategoryDetailsActivity.this,
                        owner,
                        R.layout.category_list_item,
                        categories,
                        categoryViewModel,
                        transactionViewModel,
                        subCategoryViewModel

                );
                ListView catLv = (ListView) findViewById(R.id.categoryListView);
                catLv.setAdapter(catArrayAdapter);

            }
        });
        EditText editTontant = (EditText) findViewById(R.id.editMontant);
        Spinner spinnerCategories = (Spinner) findViewById(R.id.spinnerChooseSubCategory);
        Button btnAddTransaction = (Button) findViewById(R.id.btnAddTransaction);

        subCategoryViewModel.getSubCategoriesByCatId(categoryId).observe(this, new Observer<List<SubCategory>>() {
            @Override
            public void onChanged(List<SubCategory> subCategories) {
                // Affichage en forme de card
                SubCategoryAdapter objArrayAdapter = new SubCategoryAdapter(
                        CategoryDetailsActivity.this,
                        owner,
                        R.layout.category_list_item,
                        subCategories,
                        subCategoryViewModel, transactionViewModel);
                ListView objLv = (ListView) findViewById(R.id.objectiveListView);
                objLv.setAdapter(objArrayAdapter);

                // Pour la liste déroulante
                final List<String> list = new ArrayList<String>();
                for (SubCategory cat : subCategories) {
                    list.add(cat.getSubCatName());
                }
                ArrayAdapter<String> adp1 = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1,
                        list);
                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategories.setAdapter(adp1);

                // On click
                btnAddTransaction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editTontant.getText().length() == 0) {
                            Context context = getApplicationContext();
                            CharSequence text = "Please enter a correct amount";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            return;
                        }
                        float montant = Float.parseFloat(editTontant.getText().toString());
                        SubCategory selectedSubCategory = null;
                        for (SubCategory _subCat : subCategories) {
                            if (_subCat.getSubCatName() == spinnerCategories.getSelectedItem().toString()) {
                                selectedSubCategory = _subCat;
                            }
                        }

                        Transaction transaction = new Transaction();
                        transaction.setSubCategory(selectedSubCategory.getSubCatID());
                        transaction.setCategory(selectedSubCategory.getCategory());
                        transaction.setValue(montant);
                        transaction.setDate(System.currentTimeMillis());
                        transactionViewModel.createTransaction(transaction);

                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                    }

                });
            }
        });


    }
}