package com.example.cookutproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookutproject.R;

public class SaisieFactureFragment extends Fragment {

    public SaisieFactureFragment() {
        // Required empty public constructor
    }

    @Override
    /**
     *  Associe aux boutons les fonctions correspondantes
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saisie_facture, container, false);

        view.findViewById(R.id.button_valider).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieFactureFragment_to_saisieFragment);
        });
        view.findViewById(R.id.button_annuler).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieFactureFragment_to_saisieFragment);
        });

        return view;
    }
}