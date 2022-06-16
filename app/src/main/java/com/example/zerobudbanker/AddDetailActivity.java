package com.example.zerobudbanker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zerobudbanker.models.TransactionModel;

public class AddDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detail);

        Intent i = getIntent();
        String choice = i.getStringExtra("CHOICE");
        if (choice == null) {
            ((Button)findViewById(R.id.buttonCategory)).setText("Choose a category");
        } else {
            ((Button)findViewById(R.id.buttonCategory)).setText(choice);
        }

    }

    public void goToAdd(View view ) {
        Intent myIntent = new Intent(this, AddActivity.class);
        startActivity(myIntent);
    }

    public void goToProfile(View view ) {
        Intent myIntent = new Intent(this, ProfileActivity.class);
        startActivity(myIntent);
    }

    public void goToStats(View view ) {
        Intent myIntent = new Intent(this, StatsActivity.class);
        startActivity(myIntent);
    }

    public void goToHome(View view ) {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void goToCategory(View view ) {
        Intent myIntent = new Intent(this, CategoryActivity.class);
        startActivity(myIntent);
    }

    public void postForm(View view) {

        TransactionModel transactionModel;
        try {
            EditText t1 = findViewById(R.id.editTextTransaction);
            String input1 = t1.getText().toString();
            Log.d("form", input1);

            EditText t2 = findViewById(R.id.editTextAmount);
            int input2 = Integer.parseInt(t2.getText().toString());
            //Log.d("form", input2);

            Button t3 = findViewById(R.id.buttonCategory);
            String input3 = t3.getText().toString();
            Log.d("form", input3);

            EditText t4 = findViewById(R.id.editTextDate);
            String input4 = t4.getText().toString();
            Log.d("form", input4);

            EditText t5 = findViewById(R.id.editTextTextDesc);
            String input5 = t5.getText().toString();
            Log.d("form", input5);

            transactionModel = new TransactionModel(-1, input1, input2, 6, input5,input3, 1, 1);
            Log.d("entry", transactionModel.toString());
            Toast.makeText(this,"Entry successfully added",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this,"Error, entry unsuccessful",Toast.LENGTH_LONG).show();
            transactionModel = new TransactionModel(-1, "error", 0, 0, "error","error", -1, -1);
        }

        DataBaseHelper dataBaseHelper = new DataBaseHelper(AddDetailActivity.this);

        boolean success = dataBaseHelper.addOne(transactionModel);
        Toast.makeText(this,"Success = " + success,Toast.LENGTH_LONG).show();
    }
}