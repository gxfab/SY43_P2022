package com.example.zerobudbanker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zerobudbanker.models.TransactionModel;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    ListView listViewTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        listViewTransaction = findViewById(R.id.listViewTransaction);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(ProfileActivity.this);
        List<TransactionModel> eveyone = dataBaseHelper.getEveryone();

        //Toast.makeText(ProfileActivity.this, eveyone.toString(), Toast.LENGTH_LONG).show();
        ArrayAdapter transactionArrayAdapter = new ArrayAdapter<TransactionModel>(ProfileActivity.this, android.R.layout.simple_list_item_1, eveyone);
        listViewTransaction.setAdapter(transactionArrayAdapter);
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
}