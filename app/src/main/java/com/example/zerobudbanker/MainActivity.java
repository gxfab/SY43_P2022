package com.example.zerobudbanker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zerobudbanker.models.TransactionModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    ListView listViewRecapProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.textViewDate);
        textViewDate.setText(currentDate);

        listViewRecapProfile = findViewById(R.id.listViewRecapProfile);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        List<TransactionModel> filteredThree = dataBaseHelper.getOnlyThree();

        ArrayAdapter recapProfileArrayAdapter = new ArrayAdapter<TransactionModel>(MainActivity.this, android.R.layout.simple_list_item_1, filteredThree);
        listViewRecapProfile.setAdapter(recapProfileArrayAdapter);
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

    public void goToAddDetail(View view ) {
        Intent myIntent = new Intent(this, AddActivity.class);
        startActivity(myIntent);
    }

    public void goToProfileDetail(View view ) {
        Intent myIntent = new Intent(this, ProfileActivity.class);
        startActivity(myIntent);
    }
}