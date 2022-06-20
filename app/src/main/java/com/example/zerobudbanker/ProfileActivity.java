package com.example.zerobudbanker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zerobudbanker.models.TransactionModel;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    ListView listViewTransaction;
    TextView textViewListProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toast.makeText(this,"Hold an item to delete it",Toast.LENGTH_LONG).show();

        textViewListProfile = findViewById(R.id.textViewListProfile);
        Intent i2 = getIntent();
        String choiceList = i2.getStringExtra("CHOICE_LIST");
        if (choiceList != null) {
            textViewListProfile.setText(choiceList);
        }

            listViewTransaction = findViewById(R.id.listViewTransaction);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(ProfileActivity.this);
        List<TransactionModel> everyone = dataBaseHelper.getEveryone();
        ArrayAdapter transactionArrayAdapter = new ArrayAdapter<TransactionModel>(ProfileActivity.this, android.R.layout.simple_list_item_1, everyone);
        listViewTransaction.setAdapter(transactionArrayAdapter);

        listViewTransaction.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TransactionModel clickedTransaction = (TransactionModel) adapterView.getItemAtPosition(i);
                dataBaseHelper.deleteOne(clickedTransaction);
                List<TransactionModel> everyone = dataBaseHelper.getEveryone();
                ArrayAdapter transactionArrayAdapter = new ArrayAdapter<TransactionModel>(ProfileActivity.this, android.R.layout.simple_list_item_1, everyone);
                listViewTransaction.setAdapter(transactionArrayAdapter);

                return false;
            }
        });


        Intent i = getIntent();
        String choice = i.getStringExtra("CHOICE");
        if (choice != null) {
            ((Button)findViewById(R.id.buttonCategoryProfile)).setText(choice);
            List<TransactionModel> filtered = dataBaseHelper.getByCategory(choice);
            transactionArrayAdapter.clear();
            transactionArrayAdapter.addAll(filtered);
        } else {
            ((Button)findViewById(R.id.buttonCategoryProfile)).setText("Filter by category");
        }
    }

    public void resetCategory(View view) {
        ((Button)findViewById(R.id.buttonCategoryProfile)).setText("Filter by category");

        DataBaseHelper dataBaseHelper = new DataBaseHelper(ProfileActivity.this);
        List<TransactionModel> everyone = dataBaseHelper.getEveryone();

        ArrayAdapter transactionArrayAdapter = new ArrayAdapter<TransactionModel>(ProfileActivity.this, android.R.layout.simple_list_item_1, everyone);
        listViewTransaction.setAdapter(transactionArrayAdapter);
    }

    public void goToAdd(View view ) {
        Intent myIntent = new Intent(this, AddActivity.class);
        startActivity(myIntent);
    }

    public void goToProfile(View view ) {
        Intent myIntent = new Intent(this, ProfileChoiceActivity.class);
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
        myIntent.putExtra("MENU", 2);
        textViewListProfile = findViewById(R.id.textViewListProfile);
        myIntent.putExtra("LIST",textViewListProfile.getText());
        startActivity(myIntent);
    }
}