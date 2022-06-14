package com.example.budgetzeroapp.fragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.budgetzeroapp.AppContext;
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

    public void message(String message) {
        Toast.makeText(AppContext.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void redirect(DataBaseFragment f) {MainActivity.getActivity().replaceFragment(f); }

    //public abstract int getToolBarID();
}