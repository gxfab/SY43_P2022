package com.example.sy43;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.models.Category;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.TransactionViewModel;

public class ObjectiveDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objective_details);
        this.setTitle("Objective details");
        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.init();
        TransactionViewModel transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.init();
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
        EditText editMontant = (EditText) findViewById(R.id.editMontant);

        final Button buttonPreview = findViewById(R.id.btnAddTransaction);
        buttonPreview.setOnClickListener(new View.OnClickListener() {
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

                Context context = getApplicationContext();
                CharSequence text = "Transaction created.";

                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Transaction transaction = new Transaction();
                transaction.setSubCategory(-1);
                transaction.setCategory(categoryId);
                transaction.setValue(montant);
                transaction.setDate(System.currentTimeMillis());
                transactionViewModel.createTransaction(transaction);

                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        final Button del = findViewById(R.id.obj_delete);
        del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                categoryViewModel.delCategories(categoryId);
                CharSequence text = "obj deleted";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(MainActivity.getAppContext(), text, duration);
                toast.show();
                Intent intent = new Intent(v.getContext(), CategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

}