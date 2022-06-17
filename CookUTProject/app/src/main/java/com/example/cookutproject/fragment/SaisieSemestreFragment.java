package com.example.cookutproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookutproject.R;

public class SaisieSemestreFragment extends Fragment {

    public SaisieSemestreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_saisie_semestre, container, false);
        view.findViewById(R.id.button_validerSemestre).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieSemestreFragment_to_saisieFragment);
        });

        view.findViewById(R.id.button_annulerSemestre).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieSemestreFragment_to_saisieFragment);
        });

        return view;
    }
}