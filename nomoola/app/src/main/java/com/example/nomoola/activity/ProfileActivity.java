package com.example.nomoola.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nomoola.R;
import com.example.nomoola.fragment.CategoryFragment;

public class ProfileActivity extends AppCompatActivity {

    private String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Name = getNameFromDb();
        setProfileName(Name);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    private String getNameFromDb(){
        Name = "John Moola";
        return Name;
    }

    private void setProfileName(String Name){
        final TextView textViewToChange = (TextView) findViewById(R.id.profileName);
        textViewToChange.setText(Name);
    }
}