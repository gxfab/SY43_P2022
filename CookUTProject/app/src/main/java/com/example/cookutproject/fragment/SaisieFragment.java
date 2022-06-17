package com.example.cookutproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookutproject.R;

public class SaisieFragment extends Fragment {

    public SaisieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saisie, container, false);


        view.findViewById(R.id.button_saisieFacture).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieFragment_to_saisieFactureFragment);
        });

        view.findViewById(R.id.button_saisieEvenement).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieFragment_to_saisieEvenementFragment);
        });

        view.findViewById(R.id.button_saisieSemestre).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieFragment_to_saisieSemestreFragment);
        });

        return view;
    }
}