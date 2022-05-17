package com.sucelloztm.sucelloz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> toDoList;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //launch the nav bar
        Intent intent = new Intent(this, Nav.class);
        startActivity(intent);

        toDoList=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<>(this,R.layout.text_row_item,toDoList);
        listView=
    }
}