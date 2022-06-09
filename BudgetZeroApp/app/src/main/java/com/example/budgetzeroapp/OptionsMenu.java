package com.example.budgetzeroapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class OptionsMenu extends AppCompatActivity
{
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.Home:
                Toast.makeText(this,"home", Toast.LENGTH_SHORT).show();
                this.launchHome();
                return true;
            case R.id.Budget:
                Toast.makeText(this,"budget", Toast.LENGTH_SHORT).show();
                this.launchBudget();
                return true;
            case R.id.CashFlow:
                Toast.makeText(this,"cashflow", Toast.LENGTH_SHORT).show();
                this.launchCashFlow();
                return true;
            case R.id.Savings:
                Toast.makeText(this,"savings", Toast.LENGTH_SHORT).show();
                this.launchSavings();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void launchHome(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void launchCashFlow(){
        Intent i = new Intent(this, CashFlow.class);
        startActivity(i);
    }

    public void launchBudget(){
        Intent i = new Intent(this, Budget.class);
        startActivity(i);
    }

    public void launchSavings(){
        Intent i = new Intent(this, Savings.class);
        startActivity(i);
    }


}
