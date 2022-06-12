package com.example.budgetzeroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;


public class TemplateFragment extends Fragment {

    //TODO declare objects to get from XML file. exemple :
    private Button submit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        //TODO get xml file content. exemple :
        View view = inflater.inflate(R.layout.fragment_select_expense_type, parent, false);

        //TODO get xml elements from. ID exemple:
        submit = view.findViewById(R.id.submit);

        return view;
    }
}