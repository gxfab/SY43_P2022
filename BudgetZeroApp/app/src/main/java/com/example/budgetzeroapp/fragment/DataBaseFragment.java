package com.example.budgetzeroapp.fragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.budgetzeroapp.AppContext;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.MainActivity;

public abstract class DataBaseFragment extends Fragment {
    protected DBHelper database;
    protected int id;

    public DataBaseFragment() { this(0);}

    public DataBaseFragment(int id) {
        database = new DBHelper(AppContext.getContext());
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setId(int id) {this.id = id;}

    public static void message(String message) {
        Toast.makeText(AppContext.getContext(), message, Toast.LENGTH_SHORT).show();
    }

}