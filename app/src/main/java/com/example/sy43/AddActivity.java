package com.example.sy43;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sy43.adapters.SubCategoryAdapter;
import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.SubCategoryViewModel;
import com.example.sy43.viewmodels.TransactionViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Add page");
        Context context = this.getApplicationContext();
        setContentView(R.layout.activity_add_expense);
        EditText editMontant = (EditText) findViewById(R.id.editMontant);
        Spinner spinnerCategories = (Spinner) findViewById(R.id.spinnerChooseSubCategory);
        Button btnAddTransaction = (Button) findViewById(R.id.btnAddTransaction);

        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.init();

        TransactionViewModel transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.init();

        SubCategoryViewModel subCategoryViewModel = new ViewModelProvider(this).get(SubCategoryViewModel.class);
        subCategoryViewModel.init();

        LifecycleOwner owner = this;

        subCategoryViewModel.getSubCategories().observe(this, new Observer<List<SubCategory>>() {
            @Override
            public void onChanged(List<SubCategory> subCategories) {
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
                        if (editMontant.getText().length() == 0) {
                            Context context = getApplicationContext();
                            CharSequence text = "Please enter a correct amount";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            return;
                        }
                        float montant = Float.parseFloat(editMontant.getText().toString());
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

                        Intent intent = new Intent(v.getContext(), TransactionSummary.class);
                        v.getContext().startActivity(intent);
                    }

                });
            }
        });


    }

}
