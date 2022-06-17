package com.example.cookutproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookutproject.R;

public class AffichageFragment extends Fragment {

    public AffichageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_affichage, container, false);
        view.findViewById(R.id.button_affichageEvenement).setOnClickListener(view1->{
            Navigation.findNavController(view).navigate(R.id.action_affichageFragment_to_affichageEvenementFragment);
        });
        view.findViewById(R.id.button_affichageSemestre).setOnClickListener(view1->{
            Navigation.findNavController(view).navigate(R.id.action_affichageFragment_to_affichageSemestreFragment);
        });

        return view;
    }
}